/**
 * Name........: PLEXException
 * Description.: This exception is used to indicate an import error.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex;

/**
 * This exception is used to indicate an import error.
 */
public class PLEXException extends Exception {

  private PLEXFailure   failurecode;
  
  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param code   The failuecode providing a more specific error cause. Not <code>null</code>.
   * @param ex     The exception that caused this one. Not <code>null</code>.
   */
  public PLEXException( PLEXFailure code, Exception ex ) {
    super( ex );
    failurecode = code;
  }

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param code   The failuecode providing a more specific error cause. Not <code>null</code>.
   * @param args   The arguments used to format the message.
   */
  public PLEXException( PLEXFailure code, Object ... args ) {
    super( args != null ? code.toString( args ) : code.toString() );
    failurecode = code;
  }

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param code   The failuecode providing a more specific error cause. Not <code>null</code>.
   * @param ex     The exception that caused this one. Not <code>null</code>.
   * @param args   The arguments used to format the message.
   */
  public PLEXException( PLEXFailure code, Exception ex, Object ... args ) {
    super( args != null ? code.toString( args ) : code.toString(), ex );
    failurecode = code;
  }

  /**
   * Returns the failure code associated with this exception.
   * 
   * @return   The failure code associated with this exception. Not <code>null</code>.
   */
  public PLEXFailure getFailureCode() {
    return failurecode;
  }
  
} /* ENDCLASS */
