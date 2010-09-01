/**
 * Name........: ValueTransform
 * Description.: Each implementor is capable to alter the content of a value.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import com.kasisoft.lgpl.plex.*;

/**
 * Each implementor is capable to alter the content of a value.
 */
public interface ValueTransform extends ApiDefinition {

  /**
   * Transforms the content of a value. The provided type can be a Double, a String or a Boolean. 
   * If an error occurred the transformer is supposed to generate an ErrorValue instance.
   * 
   * @param id      The ID of the api instance. Neither <code>null</code> nor empty.
   * @param value   The value which has to be transformed. Maybe <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The transformed value.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  Object transformValue( String id, Object value, String ... args ) throws PLEXException;
  
} /* ENDINTERFACE */
