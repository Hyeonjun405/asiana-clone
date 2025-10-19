package clone.asiana.asiana_clone;


import clone.asiana.asiana_clone.infra.mybatis.MybatisSupport;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
//@PropertySource("classpath:/dataSource.properties")
public class JdbcConfig {

    @Bean
    public SqlSessionFactory sqlsessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatisConfig.xml"));
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mappers/**/*.xml");
        sessionFactory.setMapperLocations(res);
        sessionFactory.setPlugins(new MybatisSupport());
        return sessionFactory.getObject();
    }



}
