/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2018 VTB Group. All rights reserved.
 */

package ru.vtb.microservices.carpark.report.config;

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
@EntityScan(basePackages = {"ru.vtb.microservices.carpark.dicts.model","ru.vtb.microservices.carpark.auth.model",
        "ru.vtb.microservices.carpark.report.model", "ru.vtb.microservices.carpark.employees.model"})
@EnableJpaRepositories(basePackages = "ru.vtb.microservices.carpark.report.repository")
public class JpaConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactory = new LocalSessionFactoryBean();
        localSessionFactory.setDataSource(dataSource);
        return localSessionFactory;
    }
}
