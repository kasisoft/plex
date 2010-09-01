/**
 * Name........: DetectionTest
 * Description.: Tests to verify the detection strategies.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import org.testng.annotations.*;

/**
 * Tests to verify the detection strategies.
 */
@Test
public class DetectionTest extends AbstractTest {

  
  @DataProvider(name="createDetectionData")
  public Object[][] createDetectionData() {
    return new Object[][] {
      { "detection" , "textual-columns-placed-detect"   },
      { "detection" , "textual-columns-detect-offset"   },
    };
  }
  
  @Test(dataProvider="createDetectionData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
} /* ENDCLASS */
