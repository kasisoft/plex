/**
 * Name........: RowResolver
 * Description.: Resolver used to identify row indices.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import com.kasisoft.lgpl.plex.*;

import org.apache.poi.ss.usermodel.*;

/**
 * Resolver used to identify row indices.
 */
public interface RowResolver extends ApiDefinition {

  /**
   * Detects a row index.
   * 
   * @param id      The ID of the api instance. Neither <code>null</code> nor empty.
   * @param sheet   The sheet which column has to be detected. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The detected row index.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  int detectRow( String id, Sheet sheet, String ... args ) throws PLEXException;
  
} /* ENDINTERFACE */