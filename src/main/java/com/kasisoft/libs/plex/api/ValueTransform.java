package com.kasisoft.libs.plex.api;

import javax.validation.constraints.*;

/**
 * Each implementor is capable to alter the content of a value.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
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
   */
  Object transformValue(@NotBlank String id, @NotNull Object value, String ... args);
  
} /* ENDINTERFACE */
