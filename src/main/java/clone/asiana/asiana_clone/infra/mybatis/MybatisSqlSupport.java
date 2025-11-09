package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class})
})
@Slf4j
public class MybatisSqlSupport implements Interceptor {
    // 스레드별 마지막 실행 Mapper ID 저장
    private static final ThreadLocal<String> lastMapperId = new ThreadLocal<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {


        Object[] args = invocation.getArgs();

        // 1) MappedStatement 꺼냄
        MappedStatement ms = (MappedStatement) args[0];

        // 2) ParameterObject 꺼냄
        Object parameterObject = null;
        if (args.length > 1) {
            parameterObject = args[1];
        }

        // 3) 완성된 SQL 문자열 생성
        String sql = getCompleteSql(ms, parameterObject);

        // 4) 중복 로그 방지 (ThreadLocal 기반)
        String currentId = ms.getId();
        if (!currentId.equals(lastMapperId.get())) {
            System.out.println("실행 Mapper: " + currentId);
            System.out.println("실제 SQL: " + sql);
            lastMapperId.set(currentId);
        }

        // 5) 다음 체인 실행
        Object result = invocation.proceed();

        // ThreadLocal 초기화
        lastMapperId.remove();

        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    public static String getCompleteSql(MappedStatement ms, Object parameterObject) {
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String sql = boundSql.getSql().replaceAll("\\s+", " ").trim(); // 보기 좋게 정리

        List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
        TypeHandlerRegistry typeHandlerRegistry = ms.getConfiguration().getTypeHandlerRegistry();

        for (ParameterMapping paramMapping : paramMappings) {
            String propertyName = paramMapping.getProperty();

            Object value;

            // 파라미터가 단일 값인지 DTO/Map인지 구분
            if (boundSql.hasAdditionalParameter(propertyName)) {
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (parameterObject == null) {
                value = null;
            } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;
            } else {
                MetaObject metaObject = ms.getConfiguration().newMetaObject(parameterObject);
                value = metaObject.getValue(propertyName);
            }

            // 값이 문자열이면 작은따옴표 처리
            String valueStr = (value instanceof String) ? "'" + value + "'" : String.valueOf(value);

            // 첫 번째 ? 만 치환
            sql = sql.replaceFirst("\\?", valueStr);
        }

        return sql;
    }

}