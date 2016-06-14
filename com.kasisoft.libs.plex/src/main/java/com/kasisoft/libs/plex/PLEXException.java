package com.kasisoft.libs.plex;

import lombok.experimental.*;

import lombok.*;

/**
 * This exception is used to indicate an import error.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PLEXException extends RuntimeException {

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   * @param ex    The exception that caused this one. Not <code>null</code>.
   */
  private PLEXException( String msg, Exception ex ) {
    super( msg, ex );
  }

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   */
  private PLEXException( String msg ) {
    super( msg );
  }
  
  private PLEXException() {
  }

  public static PLEXException wrap( Exception ex ) {
    return wrap( null, ex );
  }

  public static PLEXException wrap( String message ) {
    return wrap( message, null );
  }

  public static PLEXException wrap( String msg, Exception ex ) {
    if( ex instanceof PLEXException ) {
      return (PLEXException) ex;
    } else {
      if( (msg == null) && (ex != null) ) {
        msg = ex.getLocalizedMessage();
      }
      if( msg != null ) {
        return new PLEXException( msg, ex );
      } else {
        return new PLEXException();
      }
    }
  }

} /* ENDCLASS */
