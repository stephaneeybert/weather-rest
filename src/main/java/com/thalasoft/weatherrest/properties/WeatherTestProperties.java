package com.thalasoft.weatherrest.properties;

import com.thalasoft.weatherrest.condition.EnvTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvTest
@Component
@ConfigurationProperties(prefix = "weatherstack.api")
@PropertySource({ "classpath:weather-test.yml" })
public class WeatherTestProperties extends WeatherAbstractProperties {

  private static Logger logger = LoggerFactory.getLogger(WeatherTestProperties.class);

  public WeatherTestProperties() {
    logger.debug("Loading the weather test properties file");
  }

}
