package com.kasisoft.libs.plex;

import javax.validation.constraints.*;

/**
 * This exception is used to indicate an import error.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class PLEXException extends RuntimeException {

  private static final long serialVersionUID = 3117463233117096922L;

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   * @param ex    The exception that caused this one. Not <code>null</code>.
   */
  private PLEXException(String msg, @NotNull Exception ex) {
    super(msg, ex);
  }

  /**
   * Initialises this exception with the supplied cause.
   * 
   * @param msg   The error message. Not blank.
   */
  private PLEXException(String msg) {
    super(msg);
  }
  
  private PLEXException() {
  }

  public static PLEXException wrap(Exception ex) {
    return wrap(null, ex);
  }

  public static PLEXException wrap(String message) {
    return wrap(message, null);
  }

  public static PLEXException wrap(String msg, Exception ex) {
    if (ex instanceof PLEXException) {
      return (PLEXException) ex;
    } else {
      if ((msg == null) && (ex != null)) {
        msg = ex.getLocalizedMessage();
      }
      if (msg != null) {
        return new PLEXException(msg, ex);
      } else {
        return new PLEXException();
      }
    }
  }

} /* ENDCLASS */
