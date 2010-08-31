/**
 * Name........: MetadataProviderProxy
 * Description.: A simple wrapper for the API MetadataProvider which makes sure that internal errors
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

import java.util.*;

/**
 * A simple wrapper for the API MetadataProvider which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 */
public class MetadataProviderProxy implements MetadataProvider {

  private MetadataProvider   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public MetadataProviderProxy( @KNotNull(name="impl") MetadataProvider impl ) {
    delegate  = impl;
  }
  
  /**
   * {@inheritDoc}
   */
  public Map<String, String> getMetadata( Sheet sheet, String... args ) throws PLEXException {
    try {
      return delegate.getMetadata( sheet, args );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex, ex.getMessage() );
    }
  }

} /* ENDCLASS */
