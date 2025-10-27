package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query",  args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Slf4j
public class MybatisSqlSupport implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null;

        BoundSql boundSql = ms.getBoundSql(parameterObject);
        String sql = boundSql.getSql().replaceAll("\\s+", " "); // 공백 정리

        // 실제 파라미터 값 바인딩
        Configuration configuration = ms.getConfiguration();
        List<ParameterMapping> paramMappings = boundSql.getParameterMappings();
        if (paramMappings != null && !paramMappings.isEmpty()) {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);

            for (ParameterMapping mapping : paramMappings) {
                String propertyName = mapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object value = metaObject.getValue(propertyName);
                    String valueStr = (value == null) ? "NULL" : "'" + value.toString() + "'";
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(valueStr));
                }
            }
        }

        log.info("SQL : " + sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
