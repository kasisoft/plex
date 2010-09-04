/**
 * Name........: BooleanTransform
 * Description.: Transforms a value into a Boolean instance.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.impl;

import com.kasisoft.lgpl.plex.api.*;

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
    truevalues = new HashSet<String>();
    truevalues.add( "x"     );
    truevalues.add( "yes"   );
    truevalues.add( "1"     );
    truevalues.add( "true"  );
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
