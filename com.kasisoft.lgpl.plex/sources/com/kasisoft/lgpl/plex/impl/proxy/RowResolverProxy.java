/**
 * Name........: RowResolverProxy
 * Description.: A simple wrapper for the API RowResolver which makes sure that internal errors
 *               won't be passed besided the expected PLEXException .
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.impl.proxy;

import com.kasisoft.lgpl.tools.diagnostic.*;

import com.kasisoft.lgpl.plex.api.*;
import com.kasisoft.lgpl.plex.*;

import org.apache.poi.ss.usermodel.*;

/**
 * A simple wrapper for the API ColumnResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 */
public class RowResolverProxy implements RowResolver {

  private RowResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public RowResolverProxy( @KNotNull(name="impl") RowResolver impl ) {
    delegate  = impl;
  }
  
  /**
   * {@inheritDoc}
   */
  public int detectRow( String id, Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.detectRow( id, sheet, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

} /* ENDCLASS */
