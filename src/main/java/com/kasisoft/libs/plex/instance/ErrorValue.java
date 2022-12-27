package com.kasisoft.libs.plex.instance;

/**
 * Just a wrapper which contains a value which has been identified as erraneous.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ErrorValue {

  private String   textual;
  private String   cause;
  
  public ErrorValue(String value, String message) {
    textual = value;
    cause   = message;
  }
  
  @Override
  public String toString() {
    return String.format("%s [%s]", cause, textual);
  }

  public String getTextual() {
    return textual;
  }
  
  public String getCause() {
    return cause;
  }

} /* ENDCLASS */
