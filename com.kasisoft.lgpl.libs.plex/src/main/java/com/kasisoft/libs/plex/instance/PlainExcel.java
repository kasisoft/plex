package com.kasisoft.libs.plex.instance;

import java.util.*;

/**
 * Plain and especially simplified representation of an excel workbook.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class PlainExcel {

  private Map<String,PlainSheet>   sheets;
  
  /**
   * Initialises this collecting instance for imported excel data.
   */
  public PlainExcel() {
    sheets = new Hashtable<String,PlainSheet>();
  }
  
  /**
   * Returns all tables marked with a specified metadata key-value pair.
   * 
   * @param key     The key of the selection metadata key-value pair.
   * @param value   The value of the selection metadata key-value pair.
   * 
   * @return   The list of tables owning such a pair. Not <code>null</code>.
   */
  public PlainSheet[] getTables( String key, String value ) {
    List<PlainSheet> list = new ArrayList<PlainSheet>();
    for( PlainSheet sheet : sheets.values() ) {
      String metavalue = sheet.getMetadata( key );
      if( value.equals( metavalue ) ) {
        list.add( sheet );
      }
    }
    PlainSheet[] result = new PlainSheet[ list.size() ];
    list.toArray( result );
    return result;
  }
  
  /**
   * Registers the supplied model with this data instance.
   * 
   * @param model   The model which has to be registered. Not <code>null</code>.
   */
  public void addTable( PlainSheet model ) {
    sheets.put( model.getSheetName(), model );
  }
  
  /**
   * Returns the annotated table model for the supplied name if it exists.
   * 
   * @param name   The name to identify the table model. Neither <code>null</code> nor empty.
   * 
   * @return   The annotated table model or <code>null</code> in case there's no such model.
   */
  public PlainSheet getTable( String name ) {
    return sheets.get( name );
  }
  
  /**
   * Returns a list of all table names.
   * 
   * @return   A list of all table names. Not <code>null</code>.
   */
  public String[] getTableNames() {
    String[] result = new String[ sheets.size() ];
    sheets.keySet().toArray( result );
    Arrays.sort( result );
    return result;
  }
  
  /**
   * Provides a textual representation.
   * 
   * @param serializer   The simple serializer used to generate a textual representation from.
   *                     Maybe <code>null</code>.
   * 
   * @return   The textual representation.
   */
  public String serialize( SimpleSerializer serializer ) {
    if( serializer == null ) {
      serializer = new SimpleSerializer();
    }
    String[] names = getTableNames();
    serializer.open( "workbook" );
    for( String name : names ) {
      getTable( name ).serialize( serializer );
    }
    serializer.close();
    return serializer.toString();
  }
  
  @Override
  public String toString() {
    return serialize( new SimpleSerializer() );
  }
  
} /* ENDCLASS */
