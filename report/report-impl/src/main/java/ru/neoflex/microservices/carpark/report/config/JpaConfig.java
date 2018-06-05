/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.neoflex.microservices.carpark.report.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;

/**
 * Default jpa config.
 *
 * @author Roman_Morenko
 */
@Configuration
@EntityScan(basePackages = {"ru.neoflex.microservices.carpark.dicts.model",
        "ru.neoflex.microservices.carpark.report.model, ru.neoflex.microservices.carpark.employees.model"})
@EnableJpaRepositories(basePackages = "ru.neoflex.microservices.carpark.report.repository")
public class JpaConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
        localSessionFactory.setDataSource(dataSource);
        localSessionFactory
                        .setPackagesToScan("ru.vtb.dbo.cancelreq.domain");
        return localSessionFactory;
    }
}
