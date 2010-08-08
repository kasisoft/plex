/**
 * Name........: DefaultImportTracker
 * Description.: Default implementation for the ImportTracker.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

import org.apache.poi.ss.usermodel.*;
  
/**
 * Default implementation for the ImportTracker.
 */
public class DefaultImportTracker implements ImportTracker {

  /**
   * {@inheritDoc}
   */
  public void sheetWithoutName( Workbook wb, Sheet sheet, int index ) {
  }
  
  /**
   * {@inheritDoc}
   */
  public void unhandledSheet( Workbook wb, Sheet sheet, int index ) {
  }

  /**
   * {@inheritDoc}
   */
  public void handlingSheet( Workbook wb, Sheet sheet, int index ) {
  }
  
} /* ENDCLASS */
