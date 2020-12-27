package com.thalasoft.weatherrest.service;

import com.thalasoft.weatherrest.resource.LocationWeather;

public interface WeatherService {

  public LocationWeather getCurrentCityWeather(String city, String unit);

}
