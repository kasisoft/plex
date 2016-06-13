package com.kasisoft.libs.plex;

import java.io.*;

/**
 * Dummy implementation of an ImportMonitor which simply does nothing.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class NullImportMonitor implements ImportMonitor {

  @Override
  public void openingWorkbook( File file ) {
  }

  @Override
  public void openedWorkbook( int sheetcount ) {
  }

  @Override
  public void processingSheet( String name, int number, int sheetcount ) {
  }

  @Override
  public void sheetProcessed( String name, int number, int sheetcount ) {
  }

  @Override
  public void importingSheet( String name, int number, int sheetcount ) {
  }

  @Override
  public void sheetImported( String name, int number, int sheetcount ) {
  }

  @Override
  public void importingRow( String name, int row, int rowcount ) {
  }

  @Override
  public void resumeImport( int imported, int count ) {
  }

} /* ENDCLASS */
