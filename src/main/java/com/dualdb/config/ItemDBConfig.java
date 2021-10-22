package com.dualdb.config;

import org.springframework.beans.factory.annotation.Qualifier;
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
@EnableJpaRepositories(entityManagerFactoryRef = "itemEntityManagerFactory",
    transactionManagerRef = "itemTransactionManager", basePackages = {"com.dualdb.item"}
)
public class ItemDBConfig {

    @Bean(name = "itemDataSource")
    @ConfigurationProperties(prefix = "item.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "itemEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("itemDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("com.dualdb.item.domain")
                .persistenceUnit("item")
                .build();
    }

    @Bean(name = "itemTransactionManager")
    public PlatformTransactionManager itemTransactionManager(
            @Qualifier("itemEntityManagerFactory") EntityManagerFactory itemEntityManagerFactory) {
        return new JpaTransactionManager(itemEntityManagerFactory);
    }
}
