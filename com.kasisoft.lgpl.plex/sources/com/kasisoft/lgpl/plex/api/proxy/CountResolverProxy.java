/**
 * Name........: CountResolverProxy
 * Description.: A simple wrapper for the API CountResolver which makes sure that internal errors
 *               won't be passed besided the expected PLEXException .
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api.proxy;

import com.kasisoft.lgpl.tools.diagnostic.*;

import com.kasisoft.lgpl.plex.api.*;
import com.kasisoft.lgpl.plex.*;

import org.apache.poi.ss.usermodel.*;

/**
 * A simple wrapper for the API CountResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 */
public class CountResolverProxy implements CountResolver {

  private CountResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public CountResolverProxy( @KNotNull(name="impl") CountResolver impl ) {
    delegate  = impl;
  }
  
  /**
   * {@inheritDoc}
   */
  public int detectCount( String id, Sheet sheet, int firstcolumn, String... args ) throws PLEXException {
    try {
      return delegate.detectCount( id, sheet, firstcolumn, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

} /* ENDCLASS */
