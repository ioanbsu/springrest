package com.fitbit.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ivanbahdanau
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.fitbit")
public class SpringRestApp extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApp.class);
    }

}
