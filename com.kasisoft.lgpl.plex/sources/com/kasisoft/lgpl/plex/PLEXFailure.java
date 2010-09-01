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

  MissingApiFunction  ( "Failed to identify API function '%s' !"              ),
  ErrorInApiFunction  ( "An error occured within an API function ! Cause: %s" ),  //
  InvalidApiCall      ( "ID: %s accepts the following args: %s !"             ),  //
  IO                  ( "The resource '%s' could not be loaded !"             ),  // 
  InvalidExcel        ( "The excel file '%s' is not valid !"                  ),  //
//  DeclarationError    ( "%s"                                                  ),
  MissingSchema       ( "" );
  
  private String   formatstr;
  
  PLEXFailure( String fmt ) {
    formatstr = fmt;
  }
  
  public String toString( Object ... args ) {
    return String.format( formatstr, args );
  }
  
} /* ENDENUM */
