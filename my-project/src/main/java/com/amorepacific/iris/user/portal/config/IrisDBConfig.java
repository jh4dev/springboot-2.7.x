package com.amorepacific.iris.user.portal.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class IrisDBConfig {

	private static final String _DB_NAME = "iris";

    @Bean(name = _DB_NAME + "DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource." + _DB_NAME)
    DataSource db1DataSource() {
		return DataSourceBuilder.create().build();
	}

    @Bean(name = _DB_NAME + "SqlSessionFactory")
    @Primary
    SqlSessionFactory db1SqlSessionFactory(@Qualifier(_DB_NAME + "DataSource") DataSource db1DataSource) throws Exception {
	
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(db1DataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/"+ _DB_NAME + "/*.xml"));
        return sessionFactory.getObject();
	}

    @Bean(name = _DB_NAME + "SqlSessionTemplate")
    @Primary
    SqlSessionTemplate db1SqlSessionTemplate(@Qualifier(_DB_NAME + "SqlSessionFactory") SqlSessionFactory db1SqlSessionFactory) {
        return new SqlSessionTemplate(db1SqlSessionFactory);
    }

    @Bean(name = _DB_NAME + "TransactionManager")
    @Primary
    DataSourceTransactionManager db1TransactionManager(@Qualifier(_DB_NAME + "DataSource") DataSource db1DataSource) {
        return new DataSourceTransactionManager(db1DataSource);
    }
}
