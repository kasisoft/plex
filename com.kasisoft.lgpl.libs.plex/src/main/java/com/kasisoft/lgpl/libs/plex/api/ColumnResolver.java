package com.kasisoft.lgpl.libs.plex.api;

import com.kasisoft.lgpl.libs.plex.*;

import org.apache.poi.ss.usermodel.*;

/**
 * Resolver used to identify column numbers.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public interface ColumnResolver extends ApiDefinition {

  /**
   * Detects a column for a sheet.
   * 
   * @param id      The ID of the api instance. Neither <code>null</code> nor empty.
   * @param sheet   The sheet which column has to be detected. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return    The index of the column that could be detected.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  int detectColumn( String id, Sheet sheet, String ... args ) throws PLEXException;
  
} /* ENDINTERFACE */
