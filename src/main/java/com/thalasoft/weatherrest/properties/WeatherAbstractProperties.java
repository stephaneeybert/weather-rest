package com.thalasoft.weatherrest.properties;

import org.springframework.beans.factory.annotation.Value;

public class WeatherAbstractProperties implements WeatherProperties {

  @Value("${" + PropertyNames.ROOT_URL + "}")
  private String rootUrl;
  @Value("${" + PropertyNames.ACCESS_KEY + "}")
  private String accessKey;

  @Override
  public String getRootUrl() {
    return rootUrl;
  }

  @Override
  public String getAccessKey() {
    return accessKey;
  }

}
