package com.thalasoft.weatherrest;

import static org.assertj.core.api.Assertions.assertThat;

import com.thalasoft.weatherrest.controller.WeatherController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration
class ApplicationTest {

  @Autowired
  private WeatherController weatherController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(weatherController).isNotNull();
  }

}
