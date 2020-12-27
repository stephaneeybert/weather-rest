package com.thalasoft.weatherrest.properties;

import com.thalasoft.weatherrest.condition.EnvProd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@EnvProd
@Component
@ConfigurationProperties(prefix = "weatherstack.api")
@PropertySource({ "classpath:weather-prod.yml" })
public class WeatherProdProperties extends WeatherAbstractProperties {

  private static Logger logger = LoggerFactory.getLogger(WeatherProdProperties.class);

  public WeatherProdProperties() {
    logger.debug("Loading the weather prod properties file");
  }

}
