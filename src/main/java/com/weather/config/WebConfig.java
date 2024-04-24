package com.weather.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan({ "com.weather.controller" })
public class WebConfig extends WebMvcConfigurationSupport {
}
