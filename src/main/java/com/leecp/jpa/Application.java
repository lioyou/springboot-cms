package com.leecp.jpa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    public static void main(String [] args){
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

}
