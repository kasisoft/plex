package com.kasisoft.libs.plex.impl.proxy;

import static com.kasisoft.libs.plex.internal.Messages.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * A simple wrapper for the API ColumnResolver which makes sure that internal errors won't be 
 * passed besided the expected PLEXException .
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ColumnResolverProxy implements ColumnResolver {

  ColumnResolver   delegate;
  
  /**
   * Initializes this proxy with the supplied implementation.
   * 
   * @param impl   The original implementation providing the functionality.
   */
  public ColumnResolverProxy(ColumnResolver impl) {
    delegate  = impl;
  }
  
  @Override
  public int detectColumn(@NotBlank String id, @NotNull Sheet sheet, String... args){
    try {
      return delegate.detectColumn(id, sheet, args);
    } catch (Exception ex) {
      throw PLEXException.wrap(error_in_api_function.format(ex.getMessage()), ex);
    }
  }

  @Override
  public boolean canHandleArguments(@NotBlank String id, @NotNull List<String> args) {
    try {
      return delegate.canHandleArguments(id, args);
    } catch (Exception ex) {
      return false;
    }
  }

} /* ENDCLASS */
