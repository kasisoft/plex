package com.kasisoft.libs.plex.impl;

import com.kasisoft.libs.plex.api.*;
import com.kasisoft.libs.plex.instance.*;

import java.util.*;

/**
 * Creates an Integer value from a String or Number.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ToIntTransform implements ValueTransform {
  
  private static final String MSG_INVALID_NUMBER  = 
    "%s: The value isn't a proper number.";
  
  private static final String MSG_STRICT_ERROR    = 
    "%s: The value is not supposed to contain a rest (=%s).";
  
  private boolean   createlong  = false;
  private boolean   strict      = false;

  /**
   * Enable the creation of Long types rather than Integer types.
   * 
   * @param enable   <code>true</code> <=> Create Long types.
   */
  public void setLongType( boolean enable ) {
    createlong = enable;
  }

  /**
   * Makes sure that only strict numbers are supported.
   * 
   * @param enable   <code>true</code> <=> Only support strict numbers.
   */
  public void setStrict( boolean enable ) {
    strict = enable;
  }
  
  @Override
  public Object transformValue( String id, Object value, String ... args ) {
    
    String str = null;
    
    if( value instanceof String ) {
      str = (String) value;
    } else if( value instanceof Number ) {
      str = String.valueOf( value );
    }
    
    if( str != null ) {
      
      // it's a supported input type, so try the conversion
      try {
        
        double doubleval = Double.parseDouble( str.replace( ',', '.' ) );  // just make sure locales don't confuse us
        
        if( strict ) {
          double rest = doubleval - (long) doubleval;
          if( rest != 0 ) {
            return new ErrorValue( String.valueOf( value ), MSG_STRICT_ERROR, id, String.valueOf( rest ) );
          }
        }
        
        if( createlong ) {
          return Long.valueOf( (long) doubleval );
        } else {
          return Integer.valueOf( (int) doubleval );
        }
      
      } catch( NumberFormatException ex ) {
        return new ErrorValue( String.valueOf( value ), MSG_INVALID_NUMBER, id );
      }
      
    }
    return value;
  }

  @Override
  public boolean canHandleArguments( String id, List<String> args ) {
    return true;
  }

} /* ENDCLASS */
