package com.kasisoft.lgpl.libs.plex;

import java.io.*;

/**
 * This API may be used to monitor the import process. 
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public interface ImportMonitor {

  /**
   * Will be called before the workbook is being opened.
   * 
   * @param file   The location of the workbook.
   */
  void openingWorkbook( File file );

  /**
   * Will be called after the workbook has been successfully opened.
   * 
   * @param sheetcount   The number of sheets.
   */
  void openedWorkbook( int sheetcount );
  
  /**
   * Will be called when the sheet is being processed.
   * 
   * @param name         The sheet that is being processed. Neither <code>null</code> nor empty.
   * @param number       The current number of the sheet (starting with 1).
   * @param sheetcount   The number of available sheets.
   */
  void processingSheet( String name, int number, int sheetcount );

  /**
   * Will be called when the sheet has been processed.
   * 
   * @param name         The sheet that has been processed. Neither <code>null</code> nor empty.
   * @param number       The current number of the sheet (starting with 1).
   * @param sheetcount   The number of available sheets.
   */
  void sheetProcessed( String name, int number, int sheetcount );

  /**
   * Will be called when the sheet is being imported.
   * 
   * @param name         The sheet that is being imported. Neither <code>null</code> nor empty.
   * @param number       The current number of the sheet (starting with 1).
   * @param sheetcount   The number of available sheets.
   */
  void importingSheet( String name, int number, int sheetcount );
  
  /**
   * Will be called when the sheet has been imported.
   * 
   * @param name         The sheet that has been imported. Neither <code>null</code> nor empty.
   * @param number       The current number of the sheet (starting with 1).
   * @param sheetcount   The number of available sheets.
   */
  void sheetImported( String name, int number, int sheetcount );
  
  /**
   * Will be invoked whenever a row is being imported.
   * 
   * @param name       The sheet that is being imported. Neither <code>null</code> nor empty.
   * @param row        The row that is being imported (starting with 1).
   * @param rowcount   The number of available rows.
   */
  void importingRow( String name, int row, int rowcount );
  
  /**
   * Will be called when the import process has been finished.
   * 
   * @param imported   The number of imported sheets.
   * @param count      The number of total sheets.
   */
  void resumeImport( int imported, int count );
  
} /* ENDINTERFACE */
