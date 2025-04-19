package com.company.common.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

/*@Configuration
@EnableJpaRepositories(
    basePackages = {"com.company.security.repo","com.company.ticket.repo"},
    entityManagerFactoryRef = "jpaEntityManagerFactory",
    transactionManagerRef = "jpaTransactionManager"
)*/
public class JpaConfig {


	 @Primary
	    @Bean(name = "jpaDataSource")
	    @ConfigurationProperties(prefix = "spring.datasource")
	    public DataSource dataSource() {
	        return DataSourceBuilder.create().build();
	    }

	    @Primary
	    @Bean(name = "jpaEntityManagerFactory")
	    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(
	            @Qualifier("jpaDataSource") DataSource dataSource,
	            EntityManagerFactoryBuilder builder) {

	        return builder
	                .dataSource(dataSource)
	                .packages("com.company.security.model","com.company.ticket.model") // your JPA entity packages
	                .persistenceUnit("jpa")
	                .build();
	    }

	    @Primary
	    @Bean(name = "jpaTransactionManager")
	    public PlatformTransactionManager jpaTransactionManager(
	            @Qualifier("jpaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
	        return new JpaTransactionManager(entityManagerFactory);
	    }
}