package com.kasisoft.libs.plex.instance;

import lombok.experimental.*;

import lombok.*;

/**
 * Just a wrapper which contains a value which has been identified as erraneous.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ErrorValue {

  String   textual;
  String   cause;
  
  public ErrorValue( String value, String message ) {
    textual = value;
    cause   = message;
  }
  
  @Override
  public String toString() {
    return String.format( "%s [%s]", cause, textual );
  }
  
} /* ENDCLASS */
