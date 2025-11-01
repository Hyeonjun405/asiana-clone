package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;


@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class}
        )
})
@Slf4j
public class MybatisSqlSupport implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null;
        Object resultHandler = invocation.getArgs().length == 4 ? invocation.getArgs()[3] : null;

        // ResultHandler != null이면 캐시 조회/준비 단계 → 실제 실행 직전이 아님
        if (resultHandler != null) {
            return invocation.proceed();
        }

        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = formatSql(boundSql, parameter, ms.getConfiguration());

        // 로그 출력
        log.info("========== MYBATIS SQL ==========");
        log.info("ID        : {}", ms.getId());
        log.info("Type      : {}", ms.getSqlCommandType());
        log.info("SQL       : {}", sql);
        log.info("Parameter : {}", parameter);
        log.info("=================================");

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) { }

    /**
     * BoundSql과 parameterObject를 이용해 '?'를 실제 값으로 치환
     */
    private String formatSql(BoundSql boundSql, Object parameterObject, Configuration configuration) {
        String sql = boundSql.getSql().replaceAll("\\s+", " ").trim();

        if (parameterObject == null || boundSql.getParameterMappings().isEmpty()) {
            return sql;
        }

        MetaObject metaObject = configuration.newMetaObject(parameterObject);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        for (ParameterMapping mapping : parameterMappings) {
            String propName = mapping.getProperty();
            Object value;
            try {
                value = metaObject.getValue(propName);
            } catch (Exception e) {
                value = null;
            }
            sql = sql.replaceFirst("\\?", value != null ? Matcher.quoteReplacement("'" + value.toString() + "'") : "NULL");
        }

        return sql;
    }
}