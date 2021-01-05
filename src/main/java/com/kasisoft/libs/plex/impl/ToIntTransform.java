package com.kasisoft.libs.plex.impl;

import static com.kasisoft.libs.plex.internal.Messages.*;

import com.kasisoft.libs.plex.api.*;
import com.kasisoft.libs.plex.instance.*;

import javax.validation.constraints.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * Creates an Integer value from a String or Number.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ToIntTransform implements ValueTransform {
  
  boolean   createlong  = false;
  boolean   strict      = false;

  /**
   * Enable the creation of Long types rather than Integer types.
   * 
   * @param enable   <code>true</code> <=> Create Long types.
   */
  public void setLongType(boolean enable) {
    createlong = enable;
  }

  /**
   * Makes sure that only strict numbers are supported.
   * 
   * @param enable   <code>true</code> <=> Only support strict numbers.
   */
  public void setStrict(boolean enable) {
    strict = enable;
  }
  
  @Override
  public Object transformValue(@NotBlank String id, @NotNull Object value, String ... args) {
    
    String str = null;
    if (value instanceof String) {
      str = (String) value;
    } else if (value instanceof Number) {
      str = String.valueOf(value);
    }
    
    if (str != null) {
      
      // it's a supported input type, so try the conversion
      try {
        
        double doubleval = Double.parseDouble(str.replace( ',', '.' ));  // just make sure locales don't confuse us
        
        if (strict) {
          double rest = doubleval - (long) doubleval;
          if (rest != 0) {
            return new ErrorValue(String.valueOf(value), strict_error.format(id, rest));
          }
        }
        
        if (createlong) {
          return Long.valueOf((long) doubleval);
        } else {
          return Integer.valueOf((int) doubleval);
        }
      
      } catch (NumberFormatException ex) {
        return new ErrorValue(String.valueOf(value), invalid_number.format(id));
      }
      
    }
    return value;
  }

  @Override
  public boolean canHandleArguments(@NotBlank String id, @NotNull List<String> args) {
    return true;
  }

} /* ENDCLASS */
