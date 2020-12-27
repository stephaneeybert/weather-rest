package com.thalasoft.weatherrest.resource;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
public class LocationWeather extends AbstractModel {

  @Valid
  private Request request;
  @Valid
  private Location location;
  @Valid
  private Current current;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @EqualsAndHashCode(callSuper=false)
  public class Request extends AbstractModel {
    @NotBlank
    private String type;
    @NotBlank
    private String query;
    private String language;
    private String unit;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @EqualsAndHashCode(callSuper=false)
  public class Location extends AbstractModel {
    @NotBlank
    private String name;
    @NotBlank
    private String country;
    @NotBlank
    private String region;
    @NotBlank
    private String lat;
    @NotBlank
    private String lon;
    private LocalDateTime observationTime;
    private String utcOffset;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  @EqualsAndHashCode(callSuper=false)
  public class Current extends AbstractModel {
    private Integer temperature;
    private Integer weatherCode;
    private List<String> weatherIcons;
    private Integer windSpeed;
    private Integer windDegree;
    private String windDir;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    private Integer cloudcover;
    private Integer feelslike;
    private Integer uvIndex;
    private Integer visibility;
    private String isDay;
  }
}
