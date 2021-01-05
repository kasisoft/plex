package com.kasisoft.libs.plex.impl;

import com.kasisoft.libs.plex.api.*;

import javax.validation.constraints.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * Transforms a value into a Boolean instance.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BooleanTransform implements ValueTransform {

  Set<String>   truevalues;
  
  /**
   * Initializes this transform instance.
   */
  public BooleanTransform() {
    truevalues  = new HashSet<>();
    truevalues.add("x"   );
    truevalues.add("yes" );
    truevalues.add("1"   );
    truevalues.add("true");
  }
  
  /**
   * Sets the values that are considered to represent <code>true</code> values.
   * 
   * @param newtrues   The new values representing <code>true</code> values.
   */
  public void setTrue(List<String> newtrues) {
    truevalues.clear();
    for (String newtrue : newtrues) {
      truevalues.add(newtrue);
    }
  }
  
  @Override
  public Object transformValue(@NotBlank String id, @NotNull Object value, String ... args) {
    if (value instanceof String) {
      String str = (String) value;
      return Boolean.valueOf(truevalues.contains(str.toLowerCase()));
    }
    return value;
  }

  @Override
  public boolean canHandleArguments(@NotBlank String id, List<String> args) {
    return true;
  }

} /* ENDCLASS */
