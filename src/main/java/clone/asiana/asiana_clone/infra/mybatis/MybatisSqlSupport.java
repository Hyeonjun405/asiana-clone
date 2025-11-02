package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

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
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        // BoundSql 가져오기
        org.apache.ibatis.mapping.BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql().replaceAll("\\s+", " "); // 공백 정리

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
}