/**
 * Name........: SimpleCountResolver
 * Description.: This CountResolver implementation simply counts the number of columns. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.impl;

import com.kasisoft.lgpl.plex.*;
import com.kasisoft.lgpl.plex.api.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * This CountResolver implementation simply counts the number of columns.
 */
public class SimpleCountResolver implements CountResolver {

  /**
   * {@inheritDoc}
   */
  public int detectCount( String id, Sheet sheet, int firstcolumn, String... args ) throws PLEXException {
    int count = 0;
    Row row   = sheet.getRow( sheet.getFirstRowNum() );
    for( int i = firstcolumn; i < 256; i++ ) {
      Cell cell = row.getCell(i);
      if( (cell == null) || (cell.getCellType() == Cell.CELL_TYPE_BLANK) ) {
        break;
      }
      count++;
    }
    int div = 1;
    if( args.length > 0 ) {
      div = Integer.parseInt( args[0] );
    }
    return count / div;
  }

  /**
   * {@inheritDoc}
   */
  public boolean canHandleArguments( String id, List<String> args ) {
    if( args.size() > 0 ) {
      try {
        int value = Integer.parseInt( args.get(0) );
        return value > 0;
      } catch( NumberFormatException ex ) {
        return false;
      }
    }
    return true;
  }

} /* ENDCLASS */
