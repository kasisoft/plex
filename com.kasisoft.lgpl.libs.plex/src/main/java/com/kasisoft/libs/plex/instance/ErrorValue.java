package com.kasisoft.libs.plex.instance;

/**
 * Just a wrapper which contains a value which has been identified as erraneous.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ErrorValue {

  private String   textual;
  private String   cause;
  
  public ErrorValue( String value, String fmt, Object ... args ) {
    textual = value;
    cause   = String.format( fmt, args );
  }
  
  /**
   * Returns the erraneous value.
   * 
   * @return   The erraneous value.
   */
  public String getValue() {
    return textual;
  }
  
  /**
   * Delivers an error message.
   * 
   * @return   An error message. Neither <code>null</code> nor empty.
   */
  public String getCause() {
    return cause;
  }

  @Override
  public String toString() {
    return String.format( "%s [%s]", cause, textual );
  }
  
} /* ENDCLASS */
