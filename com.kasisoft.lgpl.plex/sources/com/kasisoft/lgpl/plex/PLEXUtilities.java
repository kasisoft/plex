/**
 * Name........: PLEXUtilities
 * Description.: 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

import com.kasisoft.lgpl.libs.common.util.*;

import com.kasisoft.lgpl.plex.api.*;
import com.kasisoft.lgpl.plex.model.*;

import java.util.*;

import java.lang.reflect.*;

public class PLEXUtilities {

  private static final String MSG_MISSING_COLUMNRESOLVER      = 
    "A 'ColumnResolver' with the id '%s' doesn't exist !";

  private static final String MSG_MISSING_ROWRESOLVER         = 
    "A 'RowResolver' with the id '%s' doesn't exist !";

  private static final String MSG_MISSING_METADATAPROVIDER         = 
    "A 'MetadataProvider' with the id '%s' doesn't exist !";
  
  private static final String MSG_FAILED_CONFIGURATION        = 
    "Failed to configure the api type '%s' !";

  private static final String MSG_INVALID_TYPE                = 
    "The type '%s' doesn't implement '%s' !";

  private static final String MSG_MISSING_CLASSNAME           = 
    "The class '%s' is not available on the classpath !";

  private static final String MSG_MISSING_FIRSTROW            = 
    "A sheet must declare either the 'firstrow' or 'firstrowdetect' setting!";
  
  private static final String MSG_MISSING_COLUMN_INFORMATION  = 
    "A first column must declare either the 'column' or 'columndetect' !";
  

  public static final ApiManager createApiManager( PLEXModel model ) throws PLEXException {
    Map<String,ApiDefinition> result = new Hashtable<String,ApiDefinition>();
    if( model.getGeneral() != null ) {
      for( PLEXInterface plexinterface : model.getGeneral().getInterface() ) {
        String      classname = plexinterface.getClassname();
        Object      instance  = MiscFunctions.newInstance( classname );
        if( ! plexinterface.getInjectors().isEmpty() ) {
          configureInstance( instance, plexinterface.getInjectors() );
        }
        result.put( plexinterface.getId(), (ApiDefinition) instance );
      }
    }
    return new ApiManager( result );
  }
  
  /**
   * Configures a specified instance according to the injections provided by the declaration file.
   * 
   * @param instance    The instance which will be altered. Not <code>null</code>.
   * @param injectors   The list of injections that have to be performed. Not <code>null</code>.
   * 
   * @throws PLEXException   The injection failed for some reason.
   */
  private static final void configureInstance( Object instance, List<PLEXInjector> injectors ) throws PLEXException {
    for( PLEXInjector injector : injectors ) {
      configureInstance( instance, injector );
    }
  }
  
  /**
   * Configures a specified instance according to a injection provided by the declaration file.
   * 
   * @todo [15-Aug-2010:KASI]   Maybe the methods should be cached but for now there's no
   *                            performance related constraint.
   * 
   * @param instance    The instance which will be altered. Not <code>null</code>.
   * @param injector    The injection that has to be performed. Not <code>null</code>.
   * 
   * @throws PLEXException   The injection failed for some reason.
   */
  private static final void configureInstance( Object instance, PLEXInjector injector ) throws PLEXException {
    Object value  = getValue  ( injector );
    String setter = getSetter ( injector );
    try {
      Method method = instance.getClass().getMethod( setter, value.getClass() );
      method.invoke( instance, value );
    } catch( NoSuchMethodException ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, String.format( MSG_FAILED_CONFIGURATION, instance.getClass().getName() ) );
    } catch( IllegalArgumentException  ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, String.format( MSG_FAILED_CONFIGURATION, instance.getClass().getName() ) );
    } catch( IllegalAccessException    ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, String.format( MSG_FAILED_CONFIGURATION, instance.getClass().getName() ) );
    } catch( InvocationTargetException ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, String.format( MSG_FAILED_CONFIGURATION, instance.getClass().getName() ) );
    }
  }
  
  /**
   * Returns the name of the setter method identified through the supplied injector.
   * 
   * @param injector   The injector instance used to identify the apropriate setter.
   *                   Not <code>null</code>.
   *                   
   * @return   The setter name for the injection process. Neither <code>null</code> nor empty.
   */
  private static final String getSetter( PLEXInjector injector ) {
    String name = injector.getName();
    if( name.length() == 1 ) {
      return String.format( "set%s", name.toUpperCase() );
    } else {
      return 
        String.format( "set%s%s", 
          String.valueOf( Character.toUpperCase( injector.getName().charAt(0) ) ), 
          injector.getName().substring(1) 
        );
    }
  }

  /**
   * Returns the value which has to be set for the injection.
   * 
   * @param injector   The injector which provides the value. Not <code>null</code>.
   * 
   * @return   The value associated with the supplied injector. Not <code>null</code>.
   */
  private static final Object getValue( PLEXInjector injector ) {
    if( injector instanceof PLEXBoolean ) {
      return Boolean.valueOf( ((PLEXBoolean) injector).isValue() );
    } else if( injector instanceof PLEXInteger ) {
      return Integer.valueOf( ((PLEXInteger) injector).getValue() );
    } else if( injector instanceof PLEXString ) {
      return ((PLEXString) injector).getValue();
    } else if( injector instanceof PLEXDouble ) {
      return Double.valueOf( ((PLEXDouble) injector).getValue() );
    } else /* if( injector instanceof PLEXStringList ) */ {
      PLEXStringList list   = (PLEXStringList) injector;
      List<String>   result = new ArrayList<String>();
      for( int i = 0; i < list.getItem().size(); i++ ) {
        result.add( list.getItem().get(i) );
      }
      return result;
    }
  }
  
  public static final void checkConsistency( ApiManager apiman, PLEXModel model ) throws PLEXException {
    checkConsistency( apiman, model.getGeneral() );
    for( PLEXSheetDescription sheet : model.getSheet() ) {
      checkConsistency( apiman, sheet );
    }
  }
  
  private static final void checkConsistency( ApiManager apiman, PLEXGeneral general ) throws PLEXException {
    for( PLEXInterface apitype : general.getInterface() ) {
      checkConsistency( apiman, apitype );
    }
  }
  
  private static final void checkConsistency( ApiManager apiman, PLEXInterface apidecl ) throws PLEXException {
    String classname = apidecl.getClassname();
    try {
      Class<?> clazz = Class.forName( classname );
      switch( apidecl.getApi() ) {
      case COLUMN     : checkType ( ColumnResolver    . class, clazz ); break;
      case COUNT      : checkType ( CountResolver     . class, clazz ); break;
      case METADATA   : checkType ( MetadataProvider  . class, clazz ); break;
      case ROW        : checkType ( RowResolver       . class, clazz ); break;
      case TRANSFORM  : checkType ( ValueTransform    . class, clazz ); break;
      }
    } catch( ClassNotFoundException ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, String.format( MSG_MISSING_CLASSNAME, classname ) );
    }
  }
  
  private static final void checkType( Class<?> requiredapi, Class<?> currenttype ) throws PLEXException {
    if( ! requiredapi.isAssignableFrom( currenttype ) ) {
      throw new PLEXException( PLEXFailure.DeclarationError, String.format( MSG_INVALID_TYPE, currenttype.getName(), requiredapi.getName() ) );
    }
  }
  
  private static final void checkConsistency( ApiManager apiman, PLEXSheetDescription sheet ) throws PLEXException {
    if( (sheet.getFirstrow() == null) && (sheet.getFirstrowdetect() == null) ) {
      throw new PLEXException( PLEXFailure.DeclarationError, MSG_MISSING_FIRSTROW );
    }
    PLEXApiCall apicall = sheet.getFirstrowdetect();
    if( apicall != null ) {
      if( ! apiman.isRowResolver( apicall.getRefid() ) ) {
        throw new PLEXException( PLEXFailure.DeclarationError, String.format( MSG_MISSING_ROWRESOLVER, apicall.getRefid() ) );
      }
    }
    for( int i = 0; i < sheet.getColumn().size(); i++ ) {
      checkConsistency( apiman, sheet.getColumn().get(i), i == 0 );
    }
    if( sheet.getMetadata() != null ) {
      checkConsistency( apiman, sheet.getMetadata() );
    }
  }
  
  private static final void checkConsistency( ApiManager apiman, PLEXMetadata metadata ) throws PLEXException {
    PLEXApiCall apicall = metadata.getMetadetect();
    if( apicall != null ) {
      if( ! apiman.isMetdataProvider( apicall.getRefid() ) ) {
        throw new PLEXException( PLEXFailure.DeclarationError, String.format( MSG_MISSING_METADATAPROVIDER, apicall.getRefid() ) );
      }
    }
  }
  
  private static final void checkConsistency( ApiManager apiman, PLEXColumnDescription column, boolean first ) throws PLEXException {
    if( (column.getColumn() == null) && (column.getColumndetect() == null) ) {
      if( ! first ) {
        throw new PLEXException( PLEXFailure.DeclarationError, MSG_MISSING_COLUMN_INFORMATION );
      }
    }
    PLEXApiCall apicall = column.getColumndetect();
    if( apicall != null ) {
      if( ! apiman.isColumnResolver( apicall.getRefid() ) ) {
        throw new PLEXException( PLEXFailure.DeclarationError, String.format( MSG_MISSING_COLUMNRESOLVER, apicall.getRefid() ) );
      }
    }
  }
  
} /* ENDCLASS */