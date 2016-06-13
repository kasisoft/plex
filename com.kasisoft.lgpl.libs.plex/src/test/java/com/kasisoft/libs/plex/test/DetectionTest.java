package com.kasisoft.libs.plex.test;

import org.testng.annotations.*;

/**
 * Tests to verify the detection strategies.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
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
  @Override
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
  @BeforeClass
  @Override
  public void prepare() throws Exception {
    super.prepare();
  }
  
} /* ENDCLASS */
