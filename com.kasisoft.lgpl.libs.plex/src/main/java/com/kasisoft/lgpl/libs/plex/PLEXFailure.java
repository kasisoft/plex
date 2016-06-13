/**
 * Name........: PLEXFailure
 * Description.: Enumeration identifying a plex failure.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex;

/**
 * Enumeration identifying a plex failure.
 */
public enum PLEXFailure {

  ErrorInApiFunction  ( "An error occured within an API function ! Cause: %s" ),
  IO                  ( "The resource '%s' could not be loaded !"             ),
  InvalidExcel        ( "The excel file '%s' is not valid !"                  ),
  DeclarationError    ( "%s"                                                  ),
  MissingSchema       ( "The schema 'plex.xsd' could not be found !"          );
  
  private String   formatstr;
  
  PLEXFailure( String fmt ) {
    formatstr = fmt;
  }
  
  public String toString( Object ... args ) {
    return String.format( formatstr, args );
  }
  
} /* ENDENUM */
