package com.hcl.demoproject.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(entityManagerFactoryRef = "clientEntityManagerFactory",
        basePackages = {"com.hcl.demoproject.clientRepository"},
        transactionManagerRef = "clientTransactionManager")

public class ClientDbConfig {

    @Primary
    @Bean(name = "ClientDataSource")
    @ConfigurationProperties(prefix = "spring.client.datasource")
    public DataSource mysqlDataSource(){

        return DataSourceBuilder.create()
                .build();
    }

    @Primary
    @Bean(name = "clientEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("ClientDataSource") DataSource dataSource){

        return builder.dataSource(dataSource)
                .packages("com.hcl.demoproject.clientEntity")
                .persistenceUnit("ClientPU")
                .build();
    }

    @Primary
    @Bean(name = "clientTransactionManager")
    PlatformTransactionManager transactionManager(@Qualifier("clientEntityManagerFactory") EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
