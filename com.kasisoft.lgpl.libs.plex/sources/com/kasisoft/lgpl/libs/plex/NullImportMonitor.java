/**
 * Name........: NullImportMonitor
 * Description.: Dummy implementation of an ImportMonitor which simply does nothing.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex;

import java.io.*;

/**
 * Dummy implementation of an ImportMonitor which simply does nothing.
 */
public class NullImportMonitor implements ImportMonitor {

  /**
   * {@inheritDoc}
   */
  public void openingWorkbook( File file ) {
  }

  /**
   * {@inheritDoc}
   */
  public void openedWorkbook( int sheetcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void processingSheet( String name, int number, int sheetcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void sheetProcessed( String name, int number, int sheetcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void importingSheet( String name, int number, int sheetcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void sheetImported( String name, int number, int sheetcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void importingRow( String name, int row, int rowcount ) {
  }

  /**
   * {@inheritDoc}
   */
  public void resumeImport( int imported, int count ) {
  }

} /* ENDCLASS */
