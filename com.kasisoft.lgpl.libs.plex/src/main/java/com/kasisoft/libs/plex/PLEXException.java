package com.kasisoft.libs.plex;

import lombok.experimental.*;

import lombok.*;

/**
 * This exception is used to indicate an import error.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PLEXException extends Exception {

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   * @param ex    The exception that caused this one. Not <code>null</code>.
   */
  public PLEXException( String msg, Exception ex ) {
    super( msg, ex );
  }

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   */
  public PLEXException( String msg ) {
    super( msg );
  }

} /* ENDCLASS */
