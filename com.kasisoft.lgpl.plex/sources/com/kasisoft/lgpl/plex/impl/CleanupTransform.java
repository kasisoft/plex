/**
 * Name........: CleanupTransform
 * Description.: Cleans up a string which means that it will be trimmed and useless values will be
 *               corrected.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.impl;

import com.kasisoft.lgpl.plex.api.*;

/**
 * Cleans up a string which means that it will be trimmed and useless values will be corrected.
 * All non-String types will remain untouched.
 */
public class CleanupTransform implements ValueTransform {

  /**
   * {@inheritDoc}
   */
  public Object transformValue( String id, Object value, String ... args ) {
    if( value instanceof String ) {
      return ((String) value).trim();
    } else {
      return value;
    }
  }

} /* ENDCLASS */
