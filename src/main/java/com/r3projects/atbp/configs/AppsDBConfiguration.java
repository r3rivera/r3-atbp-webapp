package com.r3projects.atbp.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class AppsDBConfiguration {

    @Primary
    @Bean
    public DataSource hikariDataSource(@Qualifier("DBPropConfig") final AppsDBProps appCfg){
        log.info("Start Creating JDBC Hikari DataSource");
        final HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(appCfg.getUrl());
        cfg.setUsername(appCfg.getUsername());
        cfg.setPassword(appCfg.getPassword());
        cfg.setDriverClassName(appCfg.getDriverClassName());

        cfg.setSchema(appCfg.getSchema());

        cfg.setMaximumPoolSize(appCfg.getMaximumPoolSize());
        cfg.setMinimumIdle(appCfg.getMinimumIdle());
        cfg.setIdleTimeout(appCfg.getIdleTimeout());
        cfg.setConnectionTimeout(appCfg.getConnectionTimeout());
        cfg.setMaxLifetime(appCfg.getMaxLifetime());

        log.info("Completed creating the JDBC Hikari DataSource.");
        log.info("JDBC URL :: {} ::",cfg.getJdbcUrl());
        return new HikariDataSource(cfg);
    }


    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(final DataSource dataSource){
        log.info("Creating JDBC Template object.");
        return new JdbcTemplate(dataSource);
    }


}
