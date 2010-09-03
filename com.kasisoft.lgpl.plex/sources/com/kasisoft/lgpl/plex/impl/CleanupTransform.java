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

import java.util.*;

/**
 * Cleans up a string which means that it will be trimmed and useless values will be corrected.
 * All non-String types will remain untouched.
 */
public class CleanupTransform implements ValueTransform {

  private boolean        ignorecase = false;
  private List<String>   empties    = null;
  
  /**
   * Enables/disables the case sensitivity for the test.
   * 
   * @param enable   <code>true</code> <=> Disable the case sensitivity.
   */
  public void setIgnoreCase( boolean enable ) {
    ignorecase = enable;
  }
  
  public void setEmpties( List<String> newempties ) {
    empties = newempties;
  }
  
  /**
   * {@inheritDoc}
   */
  public Object transformValue( String id, Object value, String ... args ) {
    if( value instanceof String ) {
      String str = ((String) value).trim();
      if( str.length() == 0 ) {
        return null;
      }
      if( empties != null ) {
        for( String item : empties ) {
          if( item.equals( str ) ) {
            return null;
          }
          if( ignorecase && item.equalsIgnoreCase( str ) ) {
            return null;
          }
        }
      }
      return str;
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  public boolean canHandleArguments( String id, List<String> args ) {
    return true;
  }

} /* ENDCLASS */
