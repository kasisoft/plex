/**
 * Name........: SimpleTest
 * Description.: Collection of simple tests.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.test;

import org.testng.annotations.*;

/**
 * Collection of simple tests.
 */
@Test(groups="all")
public class SimpleTest extends AbstractTest {

  
  @DataProvider(name="createSimpleData")
  public Object[][] createSimpleData() {
    return new Object[][] {
      { "simple"  , "none"                            },
      { "simple"  , "numerical-columns"               },
      { "simple"  , "textual-columns"                 },
      { "simple"  , "textual-high-columns"            },
      { "simple"  , "textual-columns-placed"          },
    };
  }
  
  @Test(dataProvider="createSimpleData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
  @BeforeClass
  public void prepare() {
    super.prepare();
  }
  
} /* ENDCLASS */
