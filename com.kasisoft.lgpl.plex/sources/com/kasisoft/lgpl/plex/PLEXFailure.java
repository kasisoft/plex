/**
 * Name........: PLEXFailure
 * Description.: Enumeration identifying a plex failure.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

/**
 * Enumeration identifying a plex failure.
 */
public enum PLEXFailure {

  MissingApiFunction  ( "Failed to identify API function '%s' !"    ),
  ErrorInApiFunction  ( "An error occured within an API function !" ),
  InvalidApiCall      ( "ID: %s accepts the following args: %s !"   ),
  IO                  ( "" ),
  InvalidExcel        ( "" ),
  DeclarationError    ( "" ),
  MissingSchema       ( "" );
  
  private String   formatstr;
  
  PLEXFailure( String fmt ) {
    formatstr = fmt;
  }
  
  public String toString( Object ... args ) {
    return String.format( formatstr, args );
  }
  
} /* ENDENUM */
