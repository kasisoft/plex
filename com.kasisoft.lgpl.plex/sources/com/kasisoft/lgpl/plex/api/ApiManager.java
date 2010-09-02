/**
 * Name........: ApiManager
 * Description.: Helper class used to manage the apis provided with a declaration file. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import com.kasisoft.lgpl.plex.*;
import com.kasisoft.lgpl.plex.impl.proxy.*;
import com.kasisoft.lgpl.plex.instance.*;
import com.kasisoft.lgpl.tools.diagnostic.*;

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
  private Map<String,ApiDefinition>      apidefinitions;
  
  /**
   * Initialises this management class using the supplied api definitions.
   * 
   * @param apidefs      A list of interface implementations provided by the ai declaration.
   *                     Not <code>null</code>.
   */
  public ApiManager( Map<String,ApiDefinition> apidefs ) {
    
    columnresolvers   = new Hashtable<String,ColumnResolver>();
    countresolvers    = new Hashtable<String,CountResolver>();
    valuetransformers = new Hashtable<String,ValueTransform>();
    rowresolvers      = new Hashtable<String,RowResolver>();
    metadataproviders = new Hashtable<String,MetadataProvider>();
    apidefinitions    = new Hashtable<String,ApiDefinition>();

    for( Map.Entry<String,ApiDefinition> apidef : apidefs.entrySet() ) {
      if( apidef.getValue() instanceof ColumnResolver ) {
        columnresolvers.put( apidef.getKey(), new ColumnResolverProxy( (ColumnResolver) apidef.getValue() ) );
      }
      if( apidef.getValue() instanceof CountResolver ) {
        countresolvers.put( apidef.getKey(), new CountResolverProxy( (CountResolver) apidef.getValue() ) );
      }
      if( apidef.getValue() instanceof ValueTransform ) {
        valuetransformers.put( apidef.getKey(), new ValueTransformProxy( (ValueTransform) apidef.getValue() ) );
      }
      if( apidef.getValue() instanceof RowResolver ) {
        rowresolvers.put( apidef.getKey(), new RowResolverProxy( (RowResolver) apidef.getValue() ) );
      }
      if( apidef.getValue() instanceof MetadataProvider ) {
        metadataproviders.put( apidef.getKey(), new MetadataProviderProxy( (MetadataProvider) apidef.getValue() ) );
      }
    }
    
    apidefinitions.putAll( columnresolvers    );
    apidefinitions.putAll( countresolvers     );
    apidefinitions.putAll( valuetransformers  );
    apidefinitions.putAll( rowresolvers       );
    apidefinitions.putAll( metadataproviders  );
    
  }
  
  /**
   * @see MetadataProvider#getMetadata(Sheet)
   * 
   * @param id   The id used to identify the resolver.
   */
  public Map<String,String> getMetadata( String id, List<String> args, Sheet sheet ) throws PLEXException {
    MetadataProvider apifunction = metadataproviders.get( id );
    return apifunction.getMetadata( id, sheet, toArray( args ) );
  }
  
  /**
   * @see ColumnResolver#detectColumn(Sheet, List<String>)
   * 
   * @param id   The id used to identify the resolver.
   */
  public int detectColumn( String id, List<String> args , Sheet sheet ) throws PLEXException {
    ColumnResolver apifunction = columnresolvers.get( id );
    return apifunction.detectColumn( id, sheet, toArray( args ) );
  }

  /**
   * @see CountResolver#detectCount(Sheet, int, List<String>)
   * 
   * @param id   The id used to identify the resolver.
   */
  public int detectCount( String id, List<String> args, Sheet sheet, int firstcolumn ) throws PLEXException {
    CountResolver apifunction = countresolvers.get( id );
    return apifunction.detectCount( id, sheet, firstcolumn, toArray( args ) );
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
    return apifunction.transformValue( id, value, toArray( args ) );
  }
  
  /**
   * @see RowResolver#detectRow(Sheet, int, List<String>)
   * 
   * @param id   The id used to identify the value transformer.
   */
  public int detectRow( String id, List<String> args , Sheet sheet ) throws PLEXException {
    RowResolver apifunction = rowresolvers.get( id );
    return apifunction.detectRow( id, sheet, toArray( args ) );
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
  
  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isColumnResolver( @KNotEmpty(name="id") String id ) {
    return columnresolvers.containsKey( id );
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isCountResolver( @KNotEmpty(name="id") String id ) {
    return countresolvers.containsKey( id );
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isMetdataProvider( @KNotEmpty(name="id") String id ) {
    return metadataproviders.containsKey( id );
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isRowResolver( @KNotEmpty(name="id") String id ) {
    return rowresolvers.containsKey( id );
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isValueTransform( @KNotEmpty(name="id") String id ) {
    return valuetransformers.containsKey( id );
  }

  public boolean canHandleArgs( @KNotEmpty(name="id") String id, List<String> args ) {
    ApiDefinition apidef = apidefinitions.get( id );
    if( apidef == null ) {
      return false;
    } else {
      return apidef.canHandleArguments( id, args );
    }
  }
  
} /* ENDCLASS */
