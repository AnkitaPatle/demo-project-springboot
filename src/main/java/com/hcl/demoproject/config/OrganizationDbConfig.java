package com.hcl.demoproject.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "organizationEntityManagerFactory",
        basePackages = {"com.hcl.demoproject.repository"},
        transactionManagerRef = "organizationTransaction")
public class OrganizationDbConfig {

    @Bean(name = "organizationDataSource")
    @ConfigurationProperties(prefix = "spring.organization.datasource")
    public DataSource mysqlDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "organizationEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("organizationDataSource") DataSource dataSource){

        return builder.dataSource(dataSource)
                .packages("com.hcl.demoproject.entiry")
                .persistenceUnit("OrganizationPU")
                .build();
    }

    @Bean(name = "organizationTransaction")
    PlatformTransactionManager transactionManager(@Qualifier("organizationEntityManagerFactory") EntityManagerFactory entityManagerFactory){

        return new JpaTransactionManager(entityManagerFactory);
    }
}
