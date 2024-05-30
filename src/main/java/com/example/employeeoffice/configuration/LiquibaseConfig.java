package com.example.employeeoffice.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

import javax.sql.DataSource;

/**
 * Configuration class for Liquibase integration.
 */
@Profile("!test")
@Configuration
public class LiquibaseConfig {

    @Value("${spring.liquibase.change-log}")
    private String changeLogPath;

    /**
     * Configures the Liquibase bean.
     *
     * @param dataSource the data source used by Liquibase
     * @return the configured Liquibase bean
     */
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changeLogPath);
        liquibase.setDropFirst(true);
        return liquibase;
    }
}

