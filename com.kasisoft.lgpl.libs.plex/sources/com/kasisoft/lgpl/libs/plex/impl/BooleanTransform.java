/**
 * Name........: BooleanTransform
 * Description.: Transforms a value into a Boolean instance.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.impl;

import com.kasisoft.lgpl.libs.plex.api.*;

import java.util.*;

/**
 * Transforms a value into a Boolean instance.
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
  
  /**
   * {@inheritDoc}
   */
  public Object transformValue( String id, Object value, String ... args ) {
    if( value instanceof String ) {
      String str = (String) value;
      return Boolean.valueOf( truevalues.contains( str.toLowerCase() ) );
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
