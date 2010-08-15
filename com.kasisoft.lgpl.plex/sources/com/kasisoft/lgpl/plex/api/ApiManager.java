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

import com.kasisoft.lgpl.plex.instance.*;
import com.kasisoft.lgpl.plex.model.*;
import com.kasisoft.lgpl.plex.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Helper class used to manage the apis provided with a declaration file.
 */
public class ApiManager {

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
   */
  public ApiManager( List<PLEXInterface> interfaces ) {
    
    columnresolvers   = new Hashtable<String,ColumnResolver>();
    countresolvers    = new Hashtable<String,CountResolver>();
    valuetransformers = new Hashtable<String,ValueTransform>();
    rowresolvers      = new Hashtable<String,RowResolver>();
    metadataproviders = new Hashtable<String,MetadataProvider>();
    
    for( PLEXInterface plexinterface : interfaces ) {
      PLEXApiType apitype   = plexinterface.getApi();
      String      classname = plexinterface.getClassname();
      Object      instance  = MiscFunctions.newInstance( classname );
      switch( apitype ) {
      case COLUMN     : columnresolvers   . put( plexinterface.getId(), (ColumnResolver  ) instance ); break;
      case COUNT      : countresolvers    . put( plexinterface.getId(), (CountResolver   ) instance ); break;
      case TRANSFORM  : valuetransformers . put( plexinterface.getId(), (ValueTransform  ) instance ); break;
      case ROW        : rowresolvers      . put( plexinterface.getId(), (RowResolver     ) instance ); break;
      case METADATA   : metadataproviders . put( plexinterface.getId(), (MetadataProvider) instance ); break;
      }
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
