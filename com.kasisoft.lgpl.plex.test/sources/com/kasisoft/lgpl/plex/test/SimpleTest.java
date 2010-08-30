/**
 * Name........: SimpleTest
 * Description.: 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import org.testng.annotations.*;

@Test
public class SimpleTest extends AbstractTest {

  
  @DataProvider(name="createSimpleData")
  public Object[][] createSimpleData() {
    return new Object[][] {
      { "simple", "simple-numerical-columns" },
    };
  }
  
  @Test(dataProvider="createSimpleData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
} /* ENDCLASS */
