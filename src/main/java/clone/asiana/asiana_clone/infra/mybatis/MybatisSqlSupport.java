package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

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
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        BoundSql boundSql = ms.getBoundSql(parameter);
        Configuration configuration = ms.getConfiguration();

        // 실제 파라미터가 치환된 SQL 생성
        String sql = getCompleteSql(boundSql, parameter, configuration);

        // ThreadLocal 기반 중복 필터
        String currentId = ms.getId();
        if (!currentId.equals(lastMapperId.get())) {
            System.out.println("실행 Mapper: " + currentId);
            System.out.println("실제 SQL: " + sql);
            lastMapperId.set(currentId);
        }

        Object result = invocation.proceed();
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

    // BoundSql + Parameter 치환 함수
    private String getCompleteSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
        String sql = boundSql.getSql().replaceAll("\\s+", " ");
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (parameterMappings != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            for (ParameterMapping mapping : parameterMappings) {
                String propertyName = mapping.getProperty();
                Object value;

                if (boundSql.hasAdditionalParameter(propertyName)) { // 동적 파라미터
                    value = boundSql.getAdditionalParameter(propertyName);
                } else if (parameterObject == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }

                sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(formatParameter(value)));
            }
        }
        return sql;
    }

    private String formatParameter(Object obj) {
        if (obj == null) return "NULL";
        if (obj instanceof String || obj instanceof Date) return "'" + obj.toString() + "'";
        return obj.toString();
    }
}