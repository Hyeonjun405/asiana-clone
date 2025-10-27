package clone.asiana.asiana_clone;

import clone.asiana.asiana_clone.infra.mybatis.MybatisSqlSupport;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    @Bean
    public MybatisSqlSupport MybatisSqlSupport() {
        return new MybatisSqlSupport();
    }

    @Bean
    public ConfigurationCustomizer mybatisCustomizer(MybatisSqlSupport interceptor) {
        return configuration -> configuration.addInterceptor(interceptor);
    }

}
