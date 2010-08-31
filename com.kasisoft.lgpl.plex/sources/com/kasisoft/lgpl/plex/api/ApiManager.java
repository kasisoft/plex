/**
 * Name........: ApiManager
 * Description.: Helper class used to manage the apis provided with a declaration file. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import com.kasisoft.lgpl.libs.common.util.*;

import com.kasisoft.lgpl.plex.*;
import com.kasisoft.lgpl.plex.api.proxy.*;
import com.kasisoft.lgpl.plex.instance.*;
import com.kasisoft.lgpl.plex.model.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

import java.lang.reflect.*;

/**
 * Helper class used to manage the apis provided with a declaration file.
 */
public class ApiManager {

  private static final String MSG_INVALID_APITYPE = 
    "The class '%s' does not support the api type %s !";

  private static final String MSG_SETTER_FAILURE = 
    "The setter '%s' caused an error when being used.";

  private static final String MSG_MISSING_SETTER = 
    "The setter '%s' (name=%s) for type '%s' doesn't exist !";
  
  private Map<String,ColumnResolver>     columnresolvers;
  private Map<String,CountResolver>      countresolvers;
  private Map<String,ValueTransform>     valuetransformers;
  private Map<String,RowResolver>        rowresolvers;
  private Map<String,MetadataProvider>   metadataproviders;

  /**
   * Initialises this management class using the supplied list of interface declarations.
   * 
   * @param interfaces   A list of interface implementations provided by the ai declaration.
   *                     Not <code>null</code>.
   *                     
   * @throws PLEXException   The initialisation of an api instance failed for some reason.
   */
  public ApiManager( List<PLEXInterface> interfaces ) throws PLEXException {
    
    columnresolvers   = new Hashtable<String,ColumnResolver>();
    countresolvers    = new Hashtable<String,CountResolver>();
    valuetransformers = new Hashtable<String,ValueTransform>();
    rowresolvers      = new Hashtable<String,RowResolver>();
    metadataproviders = new Hashtable<String,MetadataProvider>();
    
    for( PLEXInterface plexinterface : interfaces ) {
      PLEXApiType apitype   = plexinterface.getApi();
      String      classname = plexinterface.getClassname();
      Object      instance  = MiscFunctions.newInstance( classname );
      if( ! plexinterface.getInjectors().isEmpty() ) {
        configureInstance( instance, plexinterface.getInjectors() );
      }
      try {
        switch( apitype ) {
        case COLUMN     : columnresolvers   . put( plexinterface.getId(), new ColumnResolverProxy   ( (ColumnResolver  ) instance ) ); break;
        case COUNT      : countresolvers    . put( plexinterface.getId(), new CountResolverProxy    ( (CountResolver   ) instance ) ); break;
        case TRANSFORM  : valuetransformers . put( plexinterface.getId(), new ValueTransformProxy   ( (ValueTransform  ) instance ) ); break;
        case ROW        : rowresolvers      . put( plexinterface.getId(), new RowResolverProxy      ( (RowResolver     ) instance ) ); break;
        case METADATA   : metadataproviders . put( plexinterface.getId(), new MetadataProviderProxy ( (MetadataProvider) instance ) ); break;
        }
      } catch( ClassCastException ex ) {
        // easier than checking it before setting the map value
        throw new PLEXException( PLEXFailure.DeclarationError, MSG_INVALID_APITYPE, classname, apitype );
      }
    }

  }
  
  /**
   * Configures a specified instance according to the injections provided by the declaration file.
   * 
   * @param instance    The instance which will be altered. Not <code>null</code>.
   * @param injectors   The list of injections that have to be performed. Not <code>null</code>.
   * 
   * @throws PLEXException   The injection failed for some reason.
   */
  private void configureInstance( Object instance, List<PLEXInjector> injectors ) throws PLEXException {
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
  private void configureInstance( Object instance, PLEXInjector injector ) throws PLEXException {
    Object value  = getValue( injector );
    String setter = getSetter( injector );
    try {
      Method method = instance.getClass().getMethod( setter, value.getClass() );
      method.invoke( instance, value );
    } catch( NoSuchMethodException ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, MSG_MISSING_SETTER, setter, injector.getName(), instance.getClass().getName() );
    } catch( IllegalArgumentException  ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, MSG_SETTER_FAILURE, setter );
    } catch( IllegalAccessException    ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, MSG_SETTER_FAILURE, setter );
    } catch( InvocationTargetException ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex, MSG_SETTER_FAILURE, setter );
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
  private String getSetter( PLEXInjector injector ) {
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
  private Object getValue( PLEXInjector injector ) {
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
  
  /**
   * @see MetadataProvider#getMetadata(Sheet)
   * 
   * @param id   The id used to identify the resolver.
   */
  public Map<String,String> getMetadata( String id, List<String> args, Sheet sheet ) throws PLEXException {
    MetadataProvider apifunction = metadataproviders.get( id );
    if( apifunction == null ) {
      throw new PLEXException( PLEXFailure.MissingApiFunction, id );
    }
    try {
      return apifunction.getMetadata( sheet, toArray( args ) );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex );
    }
  }
  
  /**
   * @see ColumnResolver#detectColumn(Sheet, List<String>)
   * 
   * @param id   The id used to identify the resolver.
   */
  public int detectColumn( String id, List<String> args , Sheet sheet ) throws PLEXException {
    ColumnResolver apifunction = columnresolvers.get( id );
    if( apifunction == null ) {
      throw new PLEXException( PLEXFailure.MissingApiFunction, id );
    }
    try {
      return apifunction.detectColumn( sheet, toArray( args ) );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex );
    }
  }

  /**
   * @see CountResolver#detectCount(Sheet, int, List<String>)
   * 
   * @param id   The id used to identify the resolver.
   */
  public int detectCount( String id, List<String> args, Sheet sheet, int firstcolumn ) throws PLEXException {
    CountResolver apifunction = countresolvers.get( id );
    if( apifunction == null ) {
      throw new PLEXException( PLEXFailure.MissingApiFunction, id );
    }
    try {
      return apifunction.detectCount( sheet, firstcolumn, toArray( args ) );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex );
    }
  }

  /**
   * @see ValueTransform#transformValue(Object, int, int, List<String>)
   * 
   * @param id   The id used to identify the value transformer.
   */
  public Object transformValue( String id, List<String> args, Object value ) throws PLEXException {
    if( value instanceof ErrorValue ) {
      // errors will not be transformed again
      return value;
    }
    ValueTransform apifunction = valuetransformers.get( id ); 
    if( apifunction == null ) {
      throw new PLEXException( PLEXFailure.MissingApiFunction, id );
    }
    try {
      return apifunction.transformValue( value, toArray( args ) );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex );
    }
  }
  
  /**
   * @see RowResolver#detectRow(Sheet, int, List<String>)
   * 
   * @param id   The id used to identify the value transformer.
   */
  public int detectRow( String id, List<String> args , Sheet sheet ) throws PLEXException {
    RowResolver apifunction = rowresolvers.get( id );
    if( apifunction == null ) {
      throw new PLEXException( PLEXFailure.MissingApiFunction, id );
    }
    try {
      return apifunction.detectRow( sheet, toArray( args ) );
    } catch( RuntimeException ex ) {
      throw new PLEXException( PLEXFailure.ErrorInApiFunction, ex );
    }
  }
  
  /**
   * Returns a typed array variety of the supplied list.
   * 
   * @param args   A list of arguments that has to be returned. Not <code>null</code>.
   * 
   * @return   A typed array variety of the supplied list.
   */
  private String[] toArray( List<String> args ) {
    String[] result = new String[ args.size() ];
    args.toArray( result );
    return result;
  }
  
} /* ENDCLASS */
