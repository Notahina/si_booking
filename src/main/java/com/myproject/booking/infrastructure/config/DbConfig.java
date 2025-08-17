package com.myproject.booking.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Value("${db.booking.driver.mysql}")
    private String driver;
     @Value("${db.booking.url}")
     private String dbBookingUrl;
     @Value("${db.booking.username}")
     private String usernameBooking;
     @Value("${db.booking.password}")
     private String passwordBooking;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(dbBookingUrl);
        dataSourceBuilder.username(usernameBooking);
        dataSourceBuilder.password(passwordBooking);
        return dataSourceBuilder.build();
    }
}
