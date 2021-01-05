package com.kasisoft.libs.plex.test;

import org.testng.annotations.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import java.util.*;

/**
 * Collection of transformation tests.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@Test(groups="all")
public class TransformTest extends AbstractTest {

  @DataProvider(name="createTransformData")
  public Object[][] createTransformData() {
    return new Object[][] {
      {"transform", "enum-transform"     },
      {"transform", "int-transform"      },
      {"transform", "error-transform"    },
      {"transform", "cleanup-transform"  },
      {"transform", "cleanup-transform-2"},
    };
  }
  
  @Test(dataProvider="createTransformData")
  @Override
  public void runTest(String groupname, String testcase) {
    super.runTest(groupname, testcase);
  }
  
  @BeforeClass
  @Override
  public void prepare() throws Exception {
    super.prepare();
  }
  
  public static final class EnumTransform implements ValueTransform {

    @Override
    public boolean canHandleArguments(String id, List<String> args) {
      return false;
    }

    @Override
    public Object transformValue(String id, Object value, String... args) {
      if (value instanceof String) {
        return StateEnum.byLiteral((String) value);
      }
      return value;
    }
    
  } /* ENDCLASS */

  public static enum StateEnum {
    
    Resolved  ("resolved"),
    Fixed     ("fixed"   ),
    Closed    ("closed"  ),
    Invalid   ("invalid" );

    private String   literal;
    
    StateEnum(String lit) {
      literal = lit;
    }

    public static final StateEnum byLiteral(String literal) {
      for (StateEnum value : StateEnum.values()) {
        if (value.literal.equalsIgnoreCase(literal)) {
          return value;
        }
      }
      return null;
    }
    
  } /* ENDENUM */
  
} /* ENDCLASS */
