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
@Test
public class GroupTest extends AbstractTest {

  
  @DataProvider(name="createGroupData")
  public Object[][] createGroupData() {
    return new Object[][] {
      { "group" , "column-group"      },
    };
  }
  
  @Test(dataProvider="createGroupData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
} /* ENDCLASS */
