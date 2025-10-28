package clone.asiana.asiana_clone.infra.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

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
        Object parameter = invocation.getArgs().length > 1 ? invocation.getArgs()[1] : null;
        ResultHandler<?> resultHandler = (ResultHandler<?>) invocation.getArgs()[3];

        // 캐시 확인
        Cache cache = ms.getCache();

        // BoundSql 추출
        BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = boundSql.getSql().replaceAll("\\s+", " ").trim();

        // ResultHandler가 null인 경우에만 로그 출력 (실제 쿼리 실행 타이밍)
//        if (resultHandler == null) {
//            log.info("========== MYBATIS SQL ==========");
//            log.info("ID        : {}", ms.getId());
//            log.info("SQL       : {}", sql);
//            log.info("Parameter : {}", parameter);
//            if (cache != null) log.debug("Cache ID  : {}", cache.getId());
//            log.info("=================================");
//        }

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
