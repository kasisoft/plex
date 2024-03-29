package com.kasisoft.libs.plex.impl;

import com.kasisoft.libs.plex.api.*;

import javax.validation.constraints.*;

import java.util.*;

/**
 * Cleans up a string which means that it will be trimmed and useless values will be corrected.
 * All non-String types will remain untouched.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class CleanupTransform implements ValueTransform {

  private boolean        ignorecase = false;
  private List<String>   empties    = null;
  
  /**
   * Enables/disables the case sensitivity for the test.
   * 
   * @param enable   <code>true</code> <=> Disable the case sensitivity.
   */
  public void setIgnoreCase(boolean enable) {
    ignorecase = enable;
  }
  
  /**
   * Sets the values that are considered to be an empty (non existing) value.
   * 
   * @param newempties   The new values representing non existing values.
   */
  public void setEmpties(List<String> newempties) {
    empties = newempties;
  }
  
  @Override
  public Object transformValue(@NotBlank String id, @NotNull Object value, String ... args) {
    if (value instanceof String) {
      String str = ((String) value).trim();
      if (str.length() == 0) {
        return null;
      }
      if (empties != null) {
        for (String item : empties) {
          if (item.equals(str)) {
            return null;
          }
          if (ignorecase && item.equalsIgnoreCase(str)) {
            return null;
          }
        }
      }
      return str;
    }
    return value;
  }

  @Override
  public boolean canHandleArguments(@NotBlank String id, List<String> args) {
    return true;
  }

} /* ENDCLASS */
