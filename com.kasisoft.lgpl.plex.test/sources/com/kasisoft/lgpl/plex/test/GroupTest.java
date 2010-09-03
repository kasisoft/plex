/**
 * Name........: GroupTest
 * Description.: Tests to verify the column group import.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import org.testng.annotations.*;

/**
 * Tests to verify the column group import.
 */
@Test(groups="all")
public class GroupTest extends AbstractTest {

  
  @DataProvider(name="createGroupData")
  public Object[][] createGroupData() {
    return new Object[][] {
      { "group" , "column-group"          },
      { "group" , "column-group-detect"   },
      { "group" , "count-detect"          },
    };
  }
  
  @Test(dataProvider="createGroupData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
  @BeforeClass
  public void prepare() {
    super.prepare();
  }
  
} /* ENDCLASS */
