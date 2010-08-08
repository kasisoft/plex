/**
 * Name........: ColumnResolver
 * Description.: Resolver used to identify column numbers.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.tools.plex.api;

import org.apache.poi.ss.usermodel.*;

/**
 * Resolver used to identify column numbers.
 */
public interface ColumnResolver {

  /**
   * Detects a column for a sheet.
   * 
   * @param sheet   The sheet which column has to be detected. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return    The index of the column that could be detected.
   */
  int detectColumn( Sheet sheet, String ... args );
  
} /* ENDINTERFACE */
