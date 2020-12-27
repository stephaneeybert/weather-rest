package com.thalasoft.weatherrest.it;

import static com.thalasoft.weatherrest.assertion.LocationWeatherAssert.assertThatLocationWeather;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.thalasoft.weatherrest.controller.RESTConstants;
import com.thalasoft.weatherrest.resource.LocationWeather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class MockHttpRequestTest extends BaseTest {

  public static final String WEATHER_API_URL_PARAM_UNIT = "unit";
  public static final String WEATHER_API_URL_PARAM_QUERY = "city";
  public static final String WEATHER_UNIT = "m";
  public static final String WEATHER_CITY = "Stockholm";

  @Test
  public void shouldReturnOkay() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(
      get(RESTConstants.SLASH + RESTConstants.API + RESTConstants.SLASH + RESTConstants.CURRENT_CITY_WEATHER)
      .contentType("application/json")
      .param(WEATHER_API_URL_PARAM_UNIT, WEATHER_UNIT)
      .param(WEATHER_API_URL_PARAM_QUERY, WEATHER_CITY)
    )
    .andDo(print())
    .andExpect(status().isOk())
    .andExpect(content().string(containsString(WEATHER_CITY)))
    .andReturn();
    LocationWeather retrievedLocationWeather = deserializeModel(mvcResult, LocationWeather.class);
    assertThatLocationWeather(retrievedLocationWeather).hasCity(WEATHER_CITY);
  }

}
