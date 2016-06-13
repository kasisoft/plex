package com.kasisoft.lgpl.libs.plex.test;

import org.testng.annotations.*;

/**
 * Collection of simple tests.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
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
