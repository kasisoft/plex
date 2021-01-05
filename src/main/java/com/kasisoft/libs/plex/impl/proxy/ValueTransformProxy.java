package com.kasisoft.libs.plex.impl.proxy;

import static com.kasisoft.libs.plex.internal.Messages.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import javax.validation.constraints.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * A simple wrapper for the API ValueTransform which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValueTransformProxy implements ValueTransform {

  ValueTransform   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ValueTransformProxy(@NotNull ValueTransform impl) {
    delegate  = impl;
  }
  
  @Override
  public Object transformValue(@NotBlank String id, @NotNull Object value, String... args) throws PLEXException {
    try {
      return delegate.transformValue(id, value, args);
    } catch (RuntimeException ex) {
      throw PLEXException.wrap(error_in_api_function.format( ex.getMessage() ), ex);
    }
  }

  @Override
  public boolean canHandleArguments(@NotBlank String id, @NotNull List<String> args) {
    try {
      return delegate.canHandleArguments(id, args);
    } catch (RuntimeException ex) {
      return false;
    }
  }

} /* ENDCLASS */
