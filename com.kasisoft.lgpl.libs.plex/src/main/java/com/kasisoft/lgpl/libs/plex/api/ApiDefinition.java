/**
 * Name........: ApiDefinition
 * Description.: Common interface for all api definitions.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.api;

import java.util.*;

/**
 * Common interface for all api definitions.
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
  boolean canHandleArguments( String id, List<String> args ); 
  
} /* ENDINTERFACE */
