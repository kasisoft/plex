package com.kasisoft.libs.plex.impl;

import com.kasisoft.libs.plex.api.*;

import java.util.*;

/**
 * Transforms a value into a Boolean instance.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class BooleanTransform implements ValueTransform {

  private Set<String>   truevalues;
  
  /**
   * Initializes this transform instance.
   */
  public BooleanTransform() {
    truevalues  = new HashSet<String>();
    truevalues.add( "x"     );
    truevalues.add( "yes"   );
    truevalues.add( "1"     );
    truevalues.add( "true"  );
  }
  
  /**
   * Sets the values that are considered to represent <code>true</code> values.
   * 
   * @param newtrues   The new values representing <code>true</code> values.
   */
  public void setTrue( List<String> newtrues ) {
    truevalues.clear();
    for( String newtrue : newtrues ) {
      truevalues.add( newtrue );
    }
  }
  
  @Override
  public Object transformValue( String id, Object value, String ... args ) {
    if( value instanceof String ) {
      String str = (String) value;
      return Boolean.valueOf( truevalues.contains( str.toLowerCase() ) );
    }
    return value;
  }

  @Override
  public boolean canHandleArguments( String id, List<String> args ) {
    return true;
  }

} /* ENDCLASS */
