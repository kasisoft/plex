/**
 * Name........: SimpleColumnResolver
 * Description.: This ColumnResolver implementation simply checks for the occurrence of the first
 *               column which contains content.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.impl;

import com.kasisoft.lgpl.plex.*;
import com.kasisoft.lgpl.plex.api.*;

import org.apache.poi.ss.usermodel.*;

/**
 * This ColumnResolver implementation simply checks for the occurrence of the first column which 
 * contains content.
 */
public class SimpleColumnResolver implements ColumnResolver {

  private static final String SYNTAX  = "SimpleColumnResolver( offset : int {0} )";

  /**
   * {@inheritDoc}
   */
  public int detectColumn( String id, Sheet sheet, String... args ) throws PLEXException {
    int result = detectColumn( sheet );
    if( result == -1 ) {
      
    }
    int offset = 0;
    if( args.length > 0 ) {
      try {
        offset = Integer.parseInt( args[0] );
      } catch( NumberFormatException ex ) {
        throw new PLEXException( PLEXFailure.InvalidApiCall, id, SYNTAX );
      }
    }
    return result + offset;
  }

  private int detectColumn( Sheet sheet ) {
    Row row = sheet.getRow( sheet.getFirstRowNum() );
    for( int i = 0; i < 256; i++ ) {
      Cell cell = row.getCell(i);
      if( (cell != null) && (cell.getCellType() != Cell.CELL_TYPE_BLANK) ) {
        return i;
      }
    }
    return -1;
  }
  
  
} /* ENDCLASS */
