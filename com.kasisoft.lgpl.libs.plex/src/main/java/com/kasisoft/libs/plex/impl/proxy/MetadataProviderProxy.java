package com.kasisoft.libs.plex.impl.proxy;

import org.apache.poi.ss.usermodel.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import java.util.*;

/**
 * A simple wrapper for the API MetadataProvider which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class MetadataProviderProxy implements MetadataProvider {

  private MetadataProvider   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public MetadataProviderProxy( MetadataProvider impl ) {
    delegate  = impl;
  }
  
  @Override
  public Map<String, String> getMetadata( String id, Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.getMetadata( id, sheet, args );
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
