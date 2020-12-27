package com.thalasoft.weatherrest.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class EnrichableException extends RuntimeException {

  public static final long serialVersionUID = -1;

  private final List<InfoItem> infoItems = new ArrayList<>();

  protected class InfoItem {

    public static final long serialVersionUID = -1;
    private String errorMessage = null;
    private String errorCode = null;
    private String errorContext = null;

    public InfoItem(String errorMessage, String errorCode, String contextCode) {
      this.errorMessage = errorMessage;
      this.errorCode = errorCode;
      this.errorContext = contextCode;
    }

    public InfoItem(String errorMessage, String errorCode) {
      this.errorMessage = errorMessage;
      this.errorCode = errorCode;
    }

    public InfoItem(String errorMessage) {
      this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
      return errorMessage;
    }

    public String getErrorCode() {
      return errorCode;
    }

    public String getErrorContext() {
      return errorContext;
    }

  }

  protected EnrichableException() {
  }

  protected EnrichableException(String errorMessage) {
    super(errorMessage);
    this.infoItems.add(new InfoItem(errorMessage));
  }

  protected EnrichableException(String errorMessage, String errorCode) {
    super(errorMessage);
    this.infoItems.add(new InfoItem(errorMessage, errorCode, errorCode));
  }

  protected EnrichableException(String errorMessage, String errorCode, String errorContext) {
    super(errorMessage);
    this.infoItems.add(new InfoItem(errorMessage, errorCode, errorContext));
  }

  protected EnrichableException(String errorMessage, String errorCode, String errorContext, Throwable cause) {
    super(errorMessage, cause);
    this.infoItems.add(new InfoItem(errorMessage, errorCode, errorContext));
  }

  public String getCode() {
    StringBuilder builder = new StringBuilder();

    for (int i = this.infoItems.size() - 1; i >= 0; i--) {
      InfoItem info = this.infoItems.get(i);
      builder.append('[');
      builder.append(info.getErrorContext());
      builder.append(':');
      builder.append(info.getErrorCode());
      builder.append(']');
    }

    return builder.toString();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(getCode());
    builder.append('\n');

    for (int i = this.infoItems.size() - 1; i >= 0; i--) {
      InfoItem info = this.infoItems.get(i);
      builder.append('[');
      builder.append(info.getErrorContext());
      builder.append(':');
      builder.append(info.getErrorCode());
      builder.append(']');
      builder.append(info.getErrorMessage());
      if (i > 0)
        builder.append('\n');
    }

    if (getMessage() != null) {
      builder.append('\n');
      builder.append(getMessage());
    }
    appendException(builder, getCause());

    return builder.toString();
  }

  private void appendException(StringBuilder builder, Throwable throwable) {
    if (throwable == null) {
      return;
    }
    appendException(builder, throwable.getCause());
    builder.append(throwable.toString());
    builder.append('\n');
  }

}
