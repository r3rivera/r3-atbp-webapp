package com.r3projects.atbp.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component("DBPropConfig")
@ConfigurationProperties(prefix = "r3app.datasource") //Requires @EnableConfigurationProperties
public class AppsDBProps {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String schema;

    private int maximumPoolSize;
    private int minimumIdle;
    private long idleTimeout;
    private long connectionTimeout;
    private long maxLifetime;

}
