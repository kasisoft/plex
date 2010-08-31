/**
 * Name........: ColumnResolverProxy
 * Description.: A simple wrapper for the API ColumnResolver which makes sure that internal errors
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
 * A simple wrapper for the API ColumnResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 */
public class ColumnResolverProxy implements ColumnResolver {

  private ColumnResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ColumnResolverProxy( @KNotNull(name="impl") ColumnResolver impl ) {
    delegate  = impl;
  }
  
  /**
   * {@inheritDoc}
   */
  public int detectColumn( Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.detectColumn( sheet, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

} /* ENDCLASS */
