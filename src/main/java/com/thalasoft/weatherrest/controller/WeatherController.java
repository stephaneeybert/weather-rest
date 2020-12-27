package com.thalasoft.weatherrest.controller;

import java.net.URI;

import com.thalasoft.weatherrest.exception.ResourceNotFoundException;
import com.thalasoft.weatherrest.resource.LocationWeather;
import com.thalasoft.weatherrest.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@Controller
@RequestMapping(RESTConstants.SLASH + RESTConstants.API)
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  @GetMapping(RESTConstants.SLASH + RESTConstants.CURRENT_CITY_WEATHER)
  @ResponseBody
  public ResponseEntity<LocationWeather> currentCityWeather(@RequestParam(value = "unit") String unit, @RequestParam(value = "city") String city,
      UriComponentsBuilder builder) {
    try {
      LocationWeather locationWeather = weatherService.getCurrentCityWeather(city, unit);
      URI location = builder.path(RESTConstants.SLASH + RESTConstants.API + RESTConstants.SLASH + "{city}")
          .buildAndExpand(city).toUri();
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.setLocation(location);
      return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body(locationWeather);
    } catch (ResourceNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  @ResponseBody
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("ping");
  }

}
