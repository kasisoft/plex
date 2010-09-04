/**
 * Name........: TransformTest
 * Description.: Collection of transformation tests.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.test;

import com.kasisoft.lgpl.libs.plex.*;
import com.kasisoft.lgpl.libs.plex.api.*;

import org.testng.annotations.*;

import java.util.*;

/**
 * Collection of transformation tests.
 */
@Test(groups="all")
public class TransformTest extends AbstractTest {

  
  @DataProvider(name="createTransformData")
  public Object[][] createTransformData() {
    return new Object[][] {
      { "transform"   , "enum-transform"      },
      { "transform"   , "int-transform"       },
      { "transform"   , "error-transform"     },
      { "transform"   , "cleanup-transform"   },
      { "transform"   , "cleanup-transform-2" },
    };
  }
  
  @Test(dataProvider="createTransformData")
  public void runTest( String groupname, String testcase ) {
    super.runTest( groupname, testcase );
  }
  
  @BeforeClass
  public void prepare() {
    super.prepare();
  }
  
  public static final class EnumTransform implements ValueTransform {

    /**
     * {@inheritDoc}
     */
    public boolean canHandleArguments( String id, List<String> args ) {
      return false;
    }

    public Object transformValue( String id, Object value, String... args ) throws PLEXException {
      if( value instanceof String ) {
        return StateEnum.byLiteral( (String) value );
      }
      return value;
    }
    
  } /* ENDCLASS */

  public static enum StateEnum {
    
    Resolved  ( "resolved"  ),
    Fixed     ( "fixed"     ),
    Closed    ( "closed"    ),
    Invalid   ( "invalid"   );

    private String   literal;
    
    StateEnum( String lit ) {
      literal = lit;
    }

    public static final StateEnum byLiteral( String literal ) {
      for( StateEnum value : StateEnum.values() ) {
        if( value.literal.equalsIgnoreCase( literal ) ) {
          return value;
        }
      }
      return null;
    }
    
  } /* ENDENUM */
  
} /* ENDCLASS */
