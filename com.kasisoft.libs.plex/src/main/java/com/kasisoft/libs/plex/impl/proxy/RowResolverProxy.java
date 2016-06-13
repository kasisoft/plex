package com.kasisoft.libs.plex.impl.proxy;

import static com.kasisoft.libs.plex.internal.Messages.*;

import org.apache.poi.ss.usermodel.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import lombok.experimental.*;

import lombok.*;

import java.util.*;

/**
 * A simple wrapper for the API ColumnResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RowResolverProxy implements RowResolver {

  RowResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public RowResolverProxy( RowResolver impl ) {
    delegate  = impl;
  }
  
  @Override
  public int detectRow( String id, Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.detectRow( id, sheet, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( error_in_api_function.format( ex.getMessage() ), ex );
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
