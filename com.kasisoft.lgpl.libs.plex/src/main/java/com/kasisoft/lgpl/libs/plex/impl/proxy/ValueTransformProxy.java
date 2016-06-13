package com.kasisoft.lgpl.libs.plex.impl.proxy;

import com.kasisoft.lgpl.libs.plex.*;
import com.kasisoft.lgpl.libs.plex.api.*;

import java.util.*;

/**
 * A simple wrapper for the API ValueTransform which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ValueTransformProxy implements ValueTransform {

  private ValueTransform   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ValueTransformProxy( ValueTransform impl ) {
    delegate  = impl;
  }
  
  @Override
  public Object transformValue( String id, Object value, String... args ) throws PLEXException {
    try {
      return delegate.transformValue( id, value, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

  @Override
  public boolean canHandleArguments( String id, List<String> args ) {
    try {
      return delegate.canHandleArguments( id, args );
    } catch( RuntimeException ex ) {
      return false;
    }
  }

} /* ENDCLASS */
