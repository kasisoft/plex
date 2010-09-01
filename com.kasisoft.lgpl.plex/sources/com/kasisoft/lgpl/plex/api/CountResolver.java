/**
 * Name........: CountResolver
 * Description.: Resolver used for the count for a columngroup.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import com.kasisoft.lgpl.plex.*;

import org.apache.poi.ss.usermodel.*;

/**
 * Resolver used for the count for a columngroup.
 */
public interface CountResolver extends ApiDefinition {

  /**
   * Detects the count for a columngroup within a sheet.
   * 
   * @param id            The ID of the api instance. Neither <code>null</code> nor empty.
   * @param sheet         The sheet which column has to be detected. Not <code>null</code>.
   * @param firstcolumn   The first column to start to count from.
   * @param args          The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The count of the columngroups that could be detected.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  int detectCount( String id, Sheet sheet, int firstcolumn, String ... args ) throws PLEXException;
  
} /* ENDINTERFACE */
