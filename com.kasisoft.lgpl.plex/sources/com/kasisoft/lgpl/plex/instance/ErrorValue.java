/**
 * Name........: ErrorValue
 * Description.: Just a wrapper which contains a value which has been identified as erraneous.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.instance;

/**
 * Just a wrapper which contains a value which has been identified as erraneous.
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

  /**
   * {@inheritDoc}
   */
  public String toString() {
    return String.format( "%s [%s]", cause, textual );
  }
  
} /* ENDCLASS */
