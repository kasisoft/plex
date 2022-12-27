package com.kasisoft.libs.plex.test;

import com.kasisoft.libs.common.constants.*;

import com.kasisoft.libs.common.io.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.instance.*;

import org.testng.*;

import java.net.*;

import java.io.*;

/**
 * Basic implementation for the tests.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public abstract class AbstractTest {

  private File      inputdir;
  private File      outputdir;
  private File      expecteddir;
  
  public void prepare() throws Exception {
    URL  url      = getClass().getClassLoader().getResource("testdata");
    File testdata = new File(url.toURI());
    try {
      testdata      = testdata.getCanonicalFile();
    } catch (Exception ex) {
      Assert.fail( ex.getMessage() );
    }
    inputdir      = new File(testdata, "input"   );
    outputdir     = new File(testdata, "output"  );
    expecteddir   = new File(testdata, "expected");
  }
  
  protected void runTest(String groupname, String testcase) {
    
    File excelfile        = new File(inputdir   , String.format("%s/%s.xls"       , groupname, testcase));
    File modernexcelfile  = new File(inputdir   , String.format("%s/%s.xlsx"      , groupname, testcase));
    File declarationfile  = new File(inputdir   , String.format("%s/%s.plex"      , groupname, testcase));
    File expectedfile     = new File(expecteddir, String.format("%s/%s.txt"       , groupname, testcase));
    File outputfile       = new File(outputdir  , String.format("%s/%s.txt"       , groupname, testcase));
    File modernoutputfile = new File(outputdir  , String.format("%s/%s-modern.txt", groupname, testcase));
    
    outputfile.getParentFile().mkdirs();
    
    Assert.assertTrue(excelfile      .isFile());
    Assert.assertTrue(modernexcelfile.isFile());
    Assert.assertTrue(declarationfile.isFile());
    Assert.assertTrue(expectedfile   .isFile());

    Importer importer = null;
    try {
      importer = new Importer(declarationfile.toURI().toURL());
    } catch (Exception ex) {
      ex.printStackTrace();
      Assert.fail(ex.getMessage());
      return;
    }
    
    try {
      PlainExcel plex     = importer.runImport(excelfile);
      Assert.assertNotNull(plex);
      String     current  = plex.toString();
      IoFunctions.forOutputStreamDo(outputfile, $ -> IoFunctions.writeText($, current, Encoding.UTF8));
      String     expected  = new String(IoFunctions.loadChars(expectedfile, null, Encoding.UTF8));
      Assert.assertEquals(current, expected);
    } catch (Exception ex) {
      Assert.fail(ex.getMessage());
    }

    try {
      PlainExcel plex     = importer.runImport(modernexcelfile);
      Assert.assertNotNull(plex);
      String     current  = plex.toString();
      IoFunctions.forOutputStreamDo(modernoutputfile, $ -> IoFunctions.writeText($, current, Encoding.UTF8));
      String     expected  = new String( IoFunctions.loadChars(expectedfile, null, Encoding.UTF8));
      Assert.assertEquals(current, expected);
    } catch (Exception ex) {
      Assert.fail(ex.getMessage());
    }

  }
  
} /* ENDCLASS */
