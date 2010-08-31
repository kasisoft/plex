/**
 * Name........: AbstractTest
 * Description.: Basic implementation for the tests. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import com.kasisoft.lgpl.libs.common.constants.*;

import com.kasisoft.lgpl.libs.common.io.*;

import com.kasisoft.lgpl.plex.*;
import com.kasisoft.lgpl.plex.instance.*;

import org.testng.annotations.*;

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
  
  @BeforeClass
  public void prepare() {
    File testdata = new File( "testdata" );
    inputdir      = new File( testdata, "input"     );
    outputdir     = new File( testdata, "output"    );
    expecteddir   = new File( testdata, "expected"  );
  }
  
  protected void runTest( String groupname, String testcase ) {
    File excelfile        = new File( inputdir    , String.format( "%s/%s.xls"  , groupname , testcase  ) );
    File declarationfile  = new File( inputdir    , String.format( "%s/%s.plex" , groupname , testcase  ) );
    File expectedfile     = new File( expecteddir , String.format( "%s/%s.txt"  , groupname , testcase  ) );
    File outputfile       = new File( outputdir   , String.format( "%s/%s.txt"  , groupname , testcase  ) );
    Assert.assertTrue( excelfile       . isFile() );
    Assert.assertTrue( declarationfile . isFile() );
    Assert.assertTrue( expectedfile    . isFile() );
    try {
      Importer   importer = new Importer( declarationfile.toURI().toURL(), null );
      PlainExcel plex     = importer.runImport( excelfile );
      Assert.assertNotNull( plex );
      String     current  = plex.toString();
      IoFunctions.writeText( outputfile, current, Encoding.UTF8 );
      String     expected  = new String( IoFunctions.loadChars( expectedfile, null, Encoding.UTF8 ) );
      Assert.assertEquals( current, expected );
    } catch( MalformedURLException ex ) {
      Assert.fail( ex.getMessage() );
    } catch( PLEXException ex ) {
      Assert.fail( ex.getMessage() );
    }
  }
  
} /* ENDCLASS */
