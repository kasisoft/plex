/**
 * Name........: SimpleRowResolver
 * Description.: This RowResolver implementation simply checks for the occurrence of the first
 *               row which contains content.
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
 * This RowResolver implementation simply checks for the occurrence of the first row which contains 
 * content.
 */
public class SimpleRowResolver implements RowResolver {

  private static final String SYNTAX  = "SimpleRowResolver( offset : int {0} )";
  
  /**
   * {@inheritDoc}
   */
  public int detectRow( String id, Sheet sheet, String... args ) throws PLEXException {
    int offset = 0;
    if( args.length > 0 ) {
      try {
        offset = Integer.parseInt( args[0] );
      } catch( NumberFormatException ex ) {
        throw new PLEXException( PLEXFailure.InvalidApiCall, id, SYNTAX );
      }
    }
    return sheet.getFirstRowNum() + offset;
  }

} /* ENDCLASS */
