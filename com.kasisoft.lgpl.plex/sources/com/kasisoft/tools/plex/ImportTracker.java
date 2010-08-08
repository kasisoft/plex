/**
 * Name........: ImportTracker
 * Description.: Simple interface allowing to track the import process.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.tools.plex;

import org.apache.poi.ss.usermodel.*;

/**
 * Simple interface allowing to track the import process.
 */
public interface ImportTracker {

  /**
   * Handles a sheet without a name.
   * 
   * @param wb      The actual workbook. Not <code>null</code>.
   * @param sheet   The actual sheet without a name. Not <code>null</code>.
   * @param index   The index of the sheet within the workbook.
   */
  void sheetWithoutName( Workbook wb, Sheet sheet, int index );
  
  /**
   * Handles a sheet which isn't supported due to the lack of an import declaration.
   * 
   * @param wb      The actual workbook. Not <code>null</code>.
   * @param sheet   The actual sheet. Not <code>null</code>.
   * @param index   The index of the sheet within the workbook.
   */
  void unhandledSheet( Workbook wb, Sheet sheet, int index );

  /**
   * Informs about a sheet that could be identified as importable.
   * 
   * @param wb      The actual workbook. Not <code>null</code>.
   * @param sheet   The actual sheet. Not <code>null</code>.
   * @param index   The index of the sheet within the workbook.
   */
  void handlingSheet( Workbook wb, Sheet sheet, int index );

} /* ENDINTERFACE */
