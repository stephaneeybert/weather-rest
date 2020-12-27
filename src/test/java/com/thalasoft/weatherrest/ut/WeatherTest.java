package com.thalasoft.weatherrest.ut;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.thalasoft.weatherrest.resource.LocationWeather;
import com.thalasoft.weatherrest.resource.LocationWeather.Current;
import com.thalasoft.weatherrest.resource.LocationWeather.Location;
import com.thalasoft.weatherrest.resource.LocationWeather.Request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeatherTest {

  private LocationWeather locationWeather;

  private Validator validator;

  private Set<ConstraintViolation<LocationWeather>> constraintViolations;

  @BeforeEach
  public void beforeEachTest() throws Exception {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    locationWeather = this.getLocationWeather();
  }

  @Test
  public void testNoValidationViolation() {
    constraintViolations = validator.validate(locationWeather);
    assertThat(constraintViolations.size()).isZero();
  }

  @Test
  public void testEmptyTypeViolation() {
    locationWeather.getRequest().setType("");
    locationWeather.getRequest().setQuery(null);
    constraintViolations = validator.validate(locationWeather);
    assertThat(constraintViolations.size()).isEqualTo(2);
  }

  private LocationWeather getLocationWeather() {
    LocationWeather locationWeather = new LocationWeather();
    Request request = locationWeather.new Request();
    Location location = locationWeather.new Location();
    Current current = locationWeather.new Current();
    locationWeather.setRequest(request);
    locationWeather.setLocation(location);
    locationWeather.setCurrent(current);
    request.setType("City");
    request.setQuery("Stockholm");
    location.setName("Stockholm");
    location.setCountry("Sweden");
    location.setRegion("Stockholms Lan");
    location.setLat("59.333");
    location.setLon("18.050");
    return locationWeather;
  }

}
