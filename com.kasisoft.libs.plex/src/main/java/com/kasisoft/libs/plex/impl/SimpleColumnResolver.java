package com.kasisoft.libs.plex.impl;

import static com.kasisoft.libs.plex.internal.Messages.*;

import org.apache.poi.ss.usermodel.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import lombok.experimental.*;

import lombok.*;

import java.util.*;

/**
 * This ColumnResolver implementation simply checks for the occurrence of the first column which 
 * contains content.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleColumnResolver implements ColumnResolver {

  @Override
  public int detectColumn( String id, Sheet sheet, String... args ) throws PLEXException {
    int result = detectColumn( sheet );
    if( result == -1 ) {
      throw new PLEXException( error_in_api_function.format( missing_column.format( sheet.getSheetName() ) ) );
    }
    int offset = 0;
    if( args.length > 0 ) {
      offset = Integer.parseInt( args[0] );
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
  
  @Override
  public boolean canHandleArguments( String id, List<String> args ) {
    if( args.size() > 0 ) {
      try {
        Integer.parseInt( args.get(0) );
      } catch( NumberFormatException ex ) {
        return false;
      }
    }
    return true;
  }

} /* ENDCLASS */
