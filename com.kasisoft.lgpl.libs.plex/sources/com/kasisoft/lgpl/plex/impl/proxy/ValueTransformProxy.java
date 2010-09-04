/**
 * Name........: ValueTransformProxy
 * Description.: A simple wrapper for the API ValueTransform which makes sure that internal errors
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

import java.util.*;

/**
 * A simple wrapper for the API ValueTransform which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 */
public class ValueTransformProxy implements ValueTransform {

  private ValueTransform   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ValueTransformProxy( @KNotNull(name="impl") ValueTransform impl ) {
    delegate  = impl;
  }
  
  /**
   * {@inheritDoc}
   */
  public Object transformValue( String id, Object value, String... args ) throws PLEXException {
    try {
      return delegate.transformValue( id, value, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

  /**
   * {@inheritDoc}
   */
  public boolean canHandleArguments( String id, List<String> args ) {
    try {
      return delegate.canHandleArguments( id, args );
    } catch( RuntimeException ex ) {
      return false;
    }
  }

} /* ENDCLASS */
