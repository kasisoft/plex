/**
 * Name........: SimpleRowResolver
 * Description.: This RowResolver implementation simply checks for the occurrence of the first
 *               row which contains content.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.impl;

import com.kasisoft.lgpl.libs.plex.*;
import com.kasisoft.lgpl.libs.plex.api.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * This RowResolver implementation simply checks for the occurrence of the first row which contains 
 * content.
 */
public class SimpleRowResolver implements RowResolver {
  
  /**
   * {@inheritDoc}
   */
  public int detectRow( String id, Sheet sheet, String... args ) throws PLEXException {
    int offset = 0;
    if( args.length > 0 ) {
      offset = Integer.parseInt( args[0] );
    }
    return sheet.getFirstRowNum() + offset;
  }
  
  /**
   * {@inheritDoc}
   */
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
