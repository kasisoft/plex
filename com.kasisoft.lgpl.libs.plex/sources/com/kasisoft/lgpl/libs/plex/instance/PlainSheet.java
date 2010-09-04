/**
 * Name........: PlainSheet
 * Description.: Extension of a TableModel which provides additional information.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.instance;

import com.kasisoft.lgpl.tools.diagnostic.*;

import javax.swing.table.*;

import java.util.*;

/**
 * Extension of a TableModel which provides additional information.
 */
public class PlainSheet extends DefaultTableModel implements Comparable<PlainSheet> {

  private String               sheetname;
  private Class<?>[]           classes;
  private Map<String,String>   metadata;
  private List<String>         titles;
  
  /**
   * Initialises this model using the supplied sheet name.
   * 
   * @param name   The name of the corresponding sheet. Neither <code>null</code> nor empty.
   */
  public PlainSheet( @KNotEmpty(name="name") String name ) {
    super();
    metadata  = new Hashtable<String,String>();
    titles    = new ArrayList<String>();
    sheetname = name;
    classes   = null;
  }
  
  /**
   * @see #addColumn(Object)
   */
  public void addColumn( String title ) {
    titles.add( title );
    super.addColumn( title );
  }
  
  /**
   * {@inheritDoc}
   */
  public void addColumn( Object title ) {
    throw new RuntimeException();
  }
  
  /**
   * @see #getValueAt(int, int)
   * 
   * @param column   The name of the column which value has to be returned. 
   *                 Neither <code>null</code> nor empty.
   */
  public <T> T getValueAt( int row, @KNotEmpty(name="column") String column ) {
    int idx = titles.indexOf( column );
    if( idx != -1 ) {
      return (T) super.getValueAt( row, idx );
    } else {
      return null;
    }
  }
  
  /**
   * Returns a list of metadata keys.
   *
   * @return   A list of metadata keys. Not <code>null</code>.
   */
  public String[] getMetadataKeys() {
    return metadata.keySet().toArray( new String[ metadata.size() ] );
  }
  
  /**
   * Returns a metadata information for a specific key.
   * 
   * @param key   The key used to access the metadata information.
   * 
   * @return   The metadata information or <code>null</code> if none has been set.
   */
  public String getMetadata( String key ) {
    return metadata.get( key );
  }

  /**
   * Changes some metadata information for this sheet.
   * 
   * @param key     The key used to access the metadata information. Neither <code>null</code> nor empty.
   * @param value   The value of the metadata information. Neither <code>null</code> nor empty.
   */
  public void setMetadata( @KNotEmpty(name="key") String key, @KNotEmpty(name="value") String value ) {
    metadata.put( key, value );
  }

  /**
   * {@inheritDoc}
   */
  public Class<?> getColumnClass( int index ) {
    if( classes != null ) {
      return classes[ index ];
    } else {
      return super.getColumnClass( index );
    }
  }

  /**
   * Calculates the datatypes for each column based on the content.
   */
  public void purify() {
    classes                  = new Class<?>[ getColumnCount() ];
    Arrays.fill( classes, Object.class );
    Set<Class<?>> classtypes = new HashSet<Class<?>>();
    for( int col = 0; col < getColumnCount(); col++ ) {
      for( int row = 0; row < getRowCount(); row++ ) {
        Object cellvalue = getValueAt( row, col );
        if( (cellvalue != null) && (! (cellvalue instanceof ErrorValue)) ) {
          classtypes.add( cellvalue.getClass() );
        }
      }
      if( classtypes.size() == 1 ) {
        // there was only one type so use that one
        classes[ col ] = classtypes.iterator().next();
      } else {
        /** @todo [15-Aug-2010:KASI]   We could calculate the type depending on the type hierarchies. */
      }
      classtypes.clear();
    }
  }
  
  /**
   * Returns the sheetname used for this model.
   * 
   * @return   The sheetname used for this model. Neither <code>null</code> nor null. 
   */
  public String getSheetName() {
    return sheetname;
  }

  /**
   * {@inheritDoc}
   */
  public String toString() {
    return getSheetName();
  }
  
  /**
   * {@inheritDoc}
   */
  public boolean equals( Object obj ) {
    if( obj instanceof PlainSheet ) {
      return getSheetName().equals( ((PlainSheet) obj).getSheetName() );
    } else {
      return false;
    }
  }

  /**
   * {@inheritDoc}
   */
  public int compareTo( PlainSheet other ) {
    if( other == null ) {
      return 1;
    } else {
      return getSheetName().compareTo( other.getSheetName() );
    }
  }
  
  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return getSheetName().hashCode();
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
    int columns = getColumnCount();
    int rows    = getRowCount();
    serializer.open( "sheet", "name", getSheetName(), "columns", String.valueOf( columns ), "rows", String.valueOf( rows ) );
    for( int col = 0; col < columns; col++ ) {
      serializer.write( "column", null, "name", getColumnName( col ), "type", getColumnClass( col ).getName() );
    }
    for( int row = 0; row < rows; row++ ) {
      serializer.open( "row", "row", String.valueOf( row ) );
      for( int col = 0; col < columns; col++ ) {
        Object value = getValueAt( row, col );
        if( value == null ) {
          serializer.write( getColumnName( col ), null );
        } else {
          serializer.write( getColumnName( col ), String.valueOf( value ) );
        }
      }
      serializer.close();
    }
    serializer.close();
    return serializer.toString();
  }
  
} /* ENDCLASS */
