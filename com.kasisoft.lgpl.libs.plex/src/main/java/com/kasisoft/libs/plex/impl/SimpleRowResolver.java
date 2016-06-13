package com.kasisoft.libs.plex.impl;

import org.apache.poi.ss.usermodel.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import java.util.*;

/**
 * This RowResolver implementation simply checks for the occurrence of the first row which contains 
 * content.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class SimpleRowResolver implements RowResolver {
  
  @Override
  public int detectRow( String id, Sheet sheet, String... args ) throws PLEXException {
    int offset = 0;
    if( args.length > 0 ) {
      offset = Integer.parseInt( args[0] );
    }
    return sheet.getFirstRowNum() + offset;
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
