/**
 * Name........: ValueTransform
 * Description.: Each implementor is capable to alter the content of a value.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.tools.plex.api;

/**
 * Each implementor is capable to alter the content of a value.
 */
public interface ValueTransform {

  /**
   * Transforms the content of a value. The provided type can be a Double, a String or a Boolean. 
   * If an error occurred the transformer is supposed to generate an ErrorValue instance.
   * 
   * @param value   The value which has to be transformed. Maybe <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The transformed value.
   */
  Object transformValue( Object value, String ... args );
  
} /* ENDINTERFACE */
