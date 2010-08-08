/**
 * Name........: RowResolver
 * Description.: Resolver used to identify row indices.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.tools.plex.api;

import org.apache.poi.ss.usermodel.*;

/**
 * Resolver used to identify row indices.
 */
public interface RowResolver {

  /**
   * Detects a row index.
   * 
   * @param sheet   The sheet which column has to be detected. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The detected row index.
   */
  int detectRow( Sheet sheet, String ... args );
  
} /* ENDINTERFACE */
