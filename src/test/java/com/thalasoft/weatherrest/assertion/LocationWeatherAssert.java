package com.thalasoft.weatherrest.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import com.thalasoft.weatherrest.resource.LocationWeather;

import org.assertj.core.api.AbstractAssert;

public class LocationWeatherAssert extends AbstractAssert<LocationWeatherAssert, LocationWeather> {

  private LocationWeatherAssert(LocationWeather actual) {
    super(actual, LocationWeatherAssert.class);
  }

  public static LocationWeatherAssert assertThatLocationWeather(LocationWeather actual) {
    return new LocationWeatherAssert(actual);
  }

  public LocationWeatherAssert hasCity(String city) {
    isNotNull();
    assertThat(actual.getLocation().getName())
        .overridingErrorMessage("Expected the city to be <%s> but was <%s>.", city, actual.getLocation().getName())
        .isEqualTo(city.toString());
    return this;
  }

  public LocationWeatherAssert exists() {
    isNotNull();
    assertThat(actual).overridingErrorMessage("Expected the location weather to exist but it didn't.").isNotNull();
    return this;
  }

  public LocationWeatherAssert doesNotExist() {
    isNull();
    assertThat(actual).overridingErrorMessage("Expected the location weather not to exist but it did.").isNull();
    return this;
  }

}
