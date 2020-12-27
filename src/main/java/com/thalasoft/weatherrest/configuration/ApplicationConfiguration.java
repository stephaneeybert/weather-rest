package com.thalasoft.weatherrest.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = { "com.thalasoft.weatherrest.properties",
    "com.thalasoft.weatherrest.configuration" })
public class ApplicationConfiguration {
}
