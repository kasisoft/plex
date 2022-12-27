package com.kasisoft.libs.plex.api;

import com.kasisoft.libs.plex.impl.proxy.*;
import com.kasisoft.libs.plex.instance.*;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;

import java.util.*;

/**
 * Helper class used to manage the apis provided with a declaration file.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class ApiManager {

  private Map<String, ColumnResolver>     columnresolvers;
  private Map<String, ValueTransform>     valuetransformers;
  private Map<String, RowResolver>        rowresolvers;
  private Map<String, MetadataProvider>   metadataproviders;
  private Map<String, ApiDefinition>      apidefinitions;
  
  /**
   * Initialises this management class using the supplied api definitions.
   * 
   * @param apidefs      A list of interface implementations provided by the ai declaration.
   *                     Not <code>null</code>.
   */
  public ApiManager(@NotNull Map<String, ApiDefinition> apidefs) {
    
    columnresolvers   = new Hashtable<>();
    valuetransformers = new Hashtable<>();
    rowresolvers      = new Hashtable<>();
    metadataproviders = new Hashtable<>();
    apidefinitions    = new Hashtable<>();

    for (Map.Entry<String, ApiDefinition> apidef : apidefs.entrySet()) {
      if (apidef.getValue() instanceof ColumnResolver) {
        columnresolvers.put(apidef.getKey(), new ColumnResolverProxy((ColumnResolver) apidef.getValue()));
      }
      if (apidef.getValue() instanceof ValueTransform) {
        valuetransformers.put(apidef.getKey(), new ValueTransformProxy((ValueTransform) apidef.getValue()));
      }
      if (apidef.getValue() instanceof RowResolver) {
        rowresolvers.put(apidef.getKey(), new RowResolverProxy((RowResolver) apidef.getValue()));
      }
      if (apidef.getValue() instanceof MetadataProvider) {
        metadataproviders.put(apidef.getKey(), new MetadataProviderProxy((MetadataProvider) apidef.getValue()));
      }
    }
    
    apidefinitions.putAll(columnresolvers  );
    apidefinitions.putAll(valuetransformers);
    apidefinitions.putAll(rowresolvers     );
    apidefinitions.putAll(metadataproviders);
    
  }
  
  /**
   * @see MetadataProvider#getMetadata(Sheet)
   * 
   * @param id   The id used to identify the resolver.
   */
  public Map<String, String> getMetadata( String id, List<String> args, Sheet sheet ) {
    MetadataProvider apifunction = metadataproviders.get( id );
    return apifunction.getMetadata( id, sheet, toArray( args ) );
  }
  
  /**
   * @see ColumnResolver#detectColumn(Sheet, List<String>)
   * 
   * @param id   The id used to identify the resolver.
   */
  public int detectColumn(String id, List<String> args, Sheet sheet) {
    ColumnResolver apifunction = columnresolvers.get(id);
    return apifunction.detectColumn(id, sheet, toArray(args));
  }

  /**
   * @see ValueTransform#transformValue(Object, int, int, List<String>)
   * 
   * @param id   The id used to identify the value transformer.
   */
  public Object transformValue(String id, List<String> args, Object value) {
    if (value instanceof ErrorValue) {
      // errors will not be transformed again
      return value;
    }
    ValueTransform apifunction = valuetransformers.get(id); 
    return apifunction.transformValue(id, value, toArray(args));
  }
  
  /**
   * @see RowResolver#detectRow(Sheet, int, List<String>)
   * 
   * @param id   The id used to identify the value transformer.
   */
  public int detectRow(String id, List<String> args, Sheet sheet) {
    RowResolver apifunction = rowresolvers.get(id);
    return apifunction.detectRow(id, sheet, toArray(args));
  }
  
  /**
   * Returns a typed array variety of the supplied list.
   * 
   * @param args   A list of arguments that has to be returned. Not <code>null</code>.
   * 
   * @return   A typed array variety of the supplied list.
   */
  private String[] toArray(List<String> args) {
    String[] result = new String[args.size()];
    args.toArray(result);
    return result;
  }
  
  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isColumnResolver(String id) {
    return columnresolvers.containsKey(id);
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isMetdataProvider(String id) {
    return metadataproviders.containsKey(id);
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isRowResolver(String id) {
    return rowresolvers.containsKey(id);
  }

  /**
   * Returns <code>true</code> if the supplied api id is available.
   * 
   * @param id   The ID used to access a specific api. Neither <code>null</code> nor empty.
   * 
   * @return   <code>true</code> <=> An api with the supplied id is available.
   */
  public boolean isValueTransform(String id) {
    return valuetransformers.containsKey(id);
  }

  public boolean canHandleArgs(String id, List<String> args) {
    ApiDefinition apidef = apidefinitions.get(id);
    if (apidef == null) {
      return false;
    } else {
      return apidef.canHandleArguments(id, args);
    }
  }
  
} /* ENDCLASS */
