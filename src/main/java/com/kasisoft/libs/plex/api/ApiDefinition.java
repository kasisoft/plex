package com.kasisoft.libs.plex.api;

import javax.validation.constraints.*;

import java.util.*;

/**
 * Common interface for all api definitions.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public interface ApiDefinition {
  
  /**
   * Verifies that the supplied list of arguments can be handled within a call.
   * 
   * @param id     The id used for the api function. Neither <code>null</code> nor empty.
   * @param args   The arguments that have to be tested. Maybe <code>null</code>.
   * 
   * @return   <code>true</code> <=> The arguments can be handled by this api implementation.
   */
  boolean canHandleArguments(@NotBlank String id, List<String> args); 
  
} /* ENDINTERFACE */
