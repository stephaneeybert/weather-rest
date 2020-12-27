package com.thalasoft.weatherrest.configuration;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Component
@ComponentScan(basePackages = { "com.thalasoft.weatherrest.exception", "com.thalasoft.weatherrest.controller" })
public class WebConfiguration implements WebMvcConfigurer {

  private static final String LOCALE_LANGUAGE_PARAM = "lang";

  @Bean
  public RestTemplate restTesmplate() {
    return new RestTemplate();
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasenames("classpath:messages/messages");
    // If true, the key of the message will be displayed in case the key is not
    // found, instead of throwing an exception
    messageSource.setUseCodeAsDefaultMessage(true);
    messageSource.setDefaultEncoding(UTF_8.name());
    // The value 0 means always reload the messages to be developer friendly
    messageSource.setCacheSeconds(0);
    return messageSource;
  }

  // The locale interceptor provides a way to switch the language
  // just by passing the lang=’en’, lang=’fr’, and so on to the url
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName(LOCALE_LANGUAGE_PARAM);
    return localeChangeInterceptor;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver localeResolver = new SessionLocaleResolver();
    localeResolver.setDefaultLocale(Locale.ENGLISH);
    return localeResolver;
  }

  // Do not notify the front-end that it should cache the response
  @Bean
  public WebContentInterceptor webContentInterceptor() {
    WebContentInterceptor interceptor = new WebContentInterceptor();
    interceptor.setCacheSeconds(0);
    return interceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(webContentInterceptor());
  }

}
