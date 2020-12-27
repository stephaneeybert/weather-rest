package com.thalasoft.weatherrest.service;

import java.util.Locale;

import com.thalasoft.weatherrest.exception.InvalidAccessException;
import com.thalasoft.weatherrest.exception.ResourceNotFoundException;
import com.thalasoft.weatherrest.exception.ServiceUnavailableException;
import com.thalasoft.weatherrest.properties.WeatherProperties;
import com.thalasoft.weatherrest.resource.LocationWeather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

  private static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private WeatherProperties weatherProperties;

  @Autowired
  private MessageSource messageSource;

  public static final String WEATHER_API_URL_PARAM_ACCESS_KEY = "access_key";
  public static final String WEATHER_API_URL_PARAM_QUERY = "query";
  public static final String WEATHER_API_URL_PARAM_UNIT = "unit";
  public static final String WEATHER_API_URL_PARAM_LANGUAGE = "language";

  // The free version of the API supports only the english language
  // Note that the english language code is not part of the API list of languages
  // and as such it cannot be explicitly specified
  // so the parameter language=en is an invalid language code
  // Any other language code is restricted to non free subscription API usage
  public static final String WEATHER_API_DEFAULT_LANGUAGE = "";

  @Override
  public LocationWeather getCurrentCityWeather(String city, String unit) {
    Locale locale = LocaleContextHolder.getLocale();
    String languageCode;
    logger.debug(localizeMessage("language.requested", new Object[] { locale.getLanguage() }));
    languageCode = WEATHER_API_DEFAULT_LANGUAGE;

    String url = weatherProperties.getRootUrl() + "?" + WEATHER_API_URL_PARAM_ACCESS_KEY + "="
        + weatherProperties.getAccessKey() + "&" + WEATHER_API_URL_PARAM_QUERY + "=" + city + "&"
        + WEATHER_API_URL_PARAM_UNIT + "=" + unit + "&" + WEATHER_API_URL_PARAM_LANGUAGE + "=" + languageCode;

    ResponseEntity<LocationWeather> response = this.restTemplate.getForEntity(url, LocationWeather.class, 1);

    if (response.getStatusCode() == HttpStatus.OK) {
      return response.getBody();
    } else if (response.getStatusCode().value() == 101) {
      throw new InvalidAccessException(weatherProperties.getAccessKey());
    } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
      throw new ResourceNotFoundException(city);
    } else {
      throw new ServiceUnavailableException();
    }
  }

  private String localizeMessage(String errorCode, Object args[]) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(errorCode, args, locale);
  }

}
