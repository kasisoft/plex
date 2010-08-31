/**
 * Name........: SimpleTest
 * Description.: Collection of simple tests.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import org.testng.annotations.*;

/**
 * Collection of simple tests.
 */
@Test
public class SimpleTest extends AbstractTest {

  
  @DataProvider(name="createSimpleData")
  public Object[][] createSimpleData() {
    return new Object[][] {
      { "simple"  , "none"                            },
      { "simple"  , "numerical-columns"               },
      { "simple"  , "textual-columns"                 },
      { "simple"  , "textual-high-columns"            },
      { "simple"  , "textual-columns-placed"          },
      { "simple"  , "textual-columns-placed-detect"   },
      { "simple"  , "textual-columns-detect-offset"   },
    };
  }
  
  @Test(dataProvider="createSimpleData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
} /* ENDCLASS */