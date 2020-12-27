package com.thalasoft.weatherrest.it;

import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.thalasoft.weatherrest.resource.AbstractModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

public abstract class BaseTest {

  @Autowired
  protected ObjectMapper jacksonObjectMapper;

  @Autowired
  protected MessageSource messageSource;

  @Autowired
  protected MockMvc mockMvc;

  protected String localizeErrorMessage(String errorCode, Object args[], Locale locale) {
    return messageSource.getMessage(errorCode, args, locale);
  }

  protected String localizeErrorMessage(String errorCode, Locale locale) {
    return messageSource.getMessage(errorCode, null, locale);
  }

  protected Locale getLocale() {
    return LocaleContextHolder.getLocale();
  }

  protected String localizeErrorMessage(String errorCode, Object args[]) {
    Locale locale = getLocale();
    return messageSource.getMessage(errorCode, args, locale);
  }

  protected String localizeErrorMessage(String errorCode) {
    Locale locale = getLocale();
    return messageSource.getMessage(errorCode, null, locale);
  }

  protected <T extends Object> T deserialize(final MvcResult mvcResult, Class<T> clazz) throws Exception {
    return jacksonObjectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
  }

  protected <T extends AbstractModel> T deserializeModel(final MvcResult mvcResult, Class<T> clazz) throws Exception {
    return jacksonObjectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
  }

  protected <T extends AbstractModel> List<T> deserializeModels(final MvcResult mvcResult, Class<T> clazz)
      throws Exception {
    final CollectionType javaType = jacksonObjectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
    return jacksonObjectMapper.readValue(mvcResult.getResponse().getContentAsString(), javaType);
  }

}
