package com.weather.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Log
@Configuration
@EnableJpaRepositories(basePackages = "com.weather")
@PropertySource("classpath:application.properties")
public class SpringDataConfig {
    @Value("${db.url}")
    private String PG_DB_URL;
    @Value("${db.username}")
    private String PG_DB_USERNAME;
    @Value("${db.password}")
    private String PG_DB_PASSWORD;
    @Bean
    public DataSource dataSource() {
        log.info("Init dataSource");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(Driver.class.getName());
        hikariConfig.setJdbcUrl(PG_DB_URL);
        hikariConfig.setUsername(PG_DB_USERNAME);
        hikariConfig.setPassword(PG_DB_PASSWORD);

        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        return new HikariDataSource(hikariConfig);
    }
    @Bean
    public LocalSessionFactoryBean entityManagerFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.weather");
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(sessionFactory);
        return transactionManager;
    }
}
