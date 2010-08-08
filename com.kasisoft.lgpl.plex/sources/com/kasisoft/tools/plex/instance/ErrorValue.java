/**
 * Name........: ErrorValue
 * Description.: Just a wrapper which contains a value which has been identified as erraneous.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.tools.plex.instance;

/**
 * Just a wrapper which contains a value which has been identified as erraneous.
 */
public class ErrorValue {

  private String   textual;
  private String   cause;
  
  public ErrorValue() {
    textual = null;
    cause   = null;
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
   * Changes the type for the value.
   * 
   * @param newvalue   The new value.
   */
  public void setValue( String newvalue ) {
    textual = newvalue;
  }
  
  /**
   * Delivers a literal representing the error cause.
   * 
   * @return   A literal representing the error cause. Neither <code>null</code> nor empty.
   */
  public String getCause() {
    return cause;
  }

  /**
   * Changes the literal used to identify the error cause.
   * 
   * @param newcause   The literal used to identify the error cause. Neither <code>null</code> nor empty.
   */
  public void setCause( String newcause ) {
    cause = newcause;
  }
  
} /* ENDCLASS */
