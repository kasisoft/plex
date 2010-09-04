/**
 * Name........: AbstractTest
 * Description.: Basic implementation for the tests. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.test;

import com.kasisoft.lgpl.libs.common.constants.*;
import com.kasisoft.lgpl.libs.common.io.*;

import com.kasisoft.lgpl.libs.plex.*;
import com.kasisoft.lgpl.libs.plex.instance.*;

import org.testng.*;

import java.net.*;
import java.io.*;

/**
 * Basic implementation for the tests.
 */
public abstract class AbstractTest {

  private File      inputdir;
  private File      outputdir;
  private File      expecteddir;
  
  public void prepare() {
    File testdata = new File( "testdata" );
    try {
      testdata      = testdata.getCanonicalFile();
    } catch( IOException ex ) {
      Assert.fail( ex.getMessage() );
    }
    inputdir      = new File( testdata, "input"     );
    outputdir     = new File( testdata, "output"    );
    expecteddir   = new File( testdata, "expected"  );
  }
  
  protected void runTest( String groupname, String testcase ) {
    
    File excelfile        = new File( inputdir    , String.format( "%s/%s.xls"        , groupname , testcase  ) );
    File modernexcelfile  = new File( inputdir    , String.format( "%s/%s.xlsx"       , groupname , testcase  ) );
    File declarationfile  = new File( inputdir    , String.format( "%s/%s.plex"       , groupname , testcase  ) );
    File expectedfile     = new File( expecteddir , String.format( "%s/%s.txt"        , groupname , testcase  ) );
    File outputfile       = new File( outputdir   , String.format( "%s/%s.txt"        , groupname , testcase  ) );
    File modernoutputfile = new File( outputdir   , String.format( "%s/%s-modern.txt" , groupname , testcase  ) );
    
    outputfile.getParentFile().mkdirs();
    
    Assert.assertTrue( excelfile       . isFile() );
    Assert.assertTrue( modernexcelfile . isFile() );
    Assert.assertTrue( declarationfile . isFile() );
    Assert.assertTrue( expectedfile    . isFile() );

    Importer importer = null;
    try {
      importer = new Importer( declarationfile.toURI().toURL() );
    } catch( MalformedURLException ex ) {
      Assert.fail( ex.getMessage() );
      return;
    } catch( PLEXException ex ) {
      ex.printStackTrace();
      Assert.fail( ex.getMessage() );
      return;
    }
    
    try {
      PlainExcel plex     = importer.runImport( excelfile );
      Assert.assertNotNull( plex );
      String     current  = plex.toString();
      IoFunctions.writeText( outputfile, current, Encoding.UTF8 );
      String     expected  = new String( IoFunctions.loadChars( expectedfile, null, Encoding.UTF8 ) );
      Assert.assertEquals( current, expected );
    } catch( PLEXException ex ) {
      ex.printStackTrace();
      Assert.fail( ex.getMessage() );
    }

    try {
      PlainExcel plex     = importer.runImport( modernexcelfile );
      Assert.assertNotNull( plex );
      String     current  = plex.toString();
      IoFunctions.writeText( modernoutputfile, current, Encoding.UTF8 );
      String     expected  = new String( IoFunctions.loadChars( expectedfile, null, Encoding.UTF8 ) );
      Assert.assertEquals( current, expected );
    } catch( PLEXException ex ) {
      Assert.fail( ex.getMessage() );
    }

  }
  
} /* ENDCLASS */