/**
 * Name........: DetectionTest
 * Description.: Tests to verify the detection strategies.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.test;

import org.testng.annotations.*;

/**
 * Tests to verify the detection strategies.
 */
@Test(groups="all")
public class DetectionTest extends AbstractTest {

  
  @DataProvider(name="createDetectionData")
  public Object[][] createDetectionData() {
    return new Object[][] {
      { "detection" , "detect-row"                },
      { "detection" , "detect-row-with-offset"    },
      { "detection" , "detect-column"             },
      { "detection" , "detect-column-with-offset" },
    };
  }
  
  @Test(dataProvider="createDetectionData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
  @BeforeClass
  public void prepare() {
    super.prepare();
  }
  
} /* ENDCLASS */
