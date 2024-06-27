package com.hitansh.hangman.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://root:ddcSLZsCHJntCrPmWTtUOMLmwXEoMqoN@roundhouse.proxy.rlwy.net:32284/railway")
            .username("root")
            .password("ddcSLZsCHJntCrPmWTtUOMLmwXEoMqoN")
            .build();
    }
}
