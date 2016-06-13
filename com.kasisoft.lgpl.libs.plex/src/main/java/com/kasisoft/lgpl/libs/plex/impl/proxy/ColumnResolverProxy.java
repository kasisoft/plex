package com.kasisoft.lgpl.libs.plex.impl.proxy;

import com.kasisoft.lgpl.libs.plex.*;
import com.kasisoft.lgpl.libs.plex.api.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * A simple wrapper for the API ColumnResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ColumnResolverProxy implements ColumnResolver {

  private ColumnResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ColumnResolverProxy( ColumnResolver impl ) {
    delegate  = impl;
  }
  
  @Override
  public int detectColumn( String id, Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.detectColumn( id, sheet, args );
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
