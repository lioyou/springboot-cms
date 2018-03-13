package com.leecp.jpa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DruidConfig {
    @Value("${spring.dataSource.url}")
    private String dbUrl;
    @Value("${spring.dataSource.username}")
    private String username;
    @Value("${spring.dataSource.password}")
    private String password;
    @Value("${spring.dataSource.driver-class-name}")
    private String driverClassName;

    //创建DruidDataSources
    @Bean
    @Primary
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
