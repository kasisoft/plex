package com.kasisoft.libs.plex.instance;

import lombok.experimental.*;

import lombok.*;

import javax.swing.table.*;

import java.util.*;

/**
 * Extension of a TableModel which provides additional information.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "sheetName", callSuper = false)
public class PlainSheet extends DefaultTableModel implements Comparable<PlainSheet> {

  private static final long serialVersionUID = 9032042124476020208L;

  @Getter
  String                sheetName;
  
  Class<?>[]            classes;
  Map<String, String>   metadata;
  List<String>          titles;
  
  /**
   * Initialises this model using the supplied sheet name.
   * 
   * @param name   The name of the corresponding sheet. Neither <code>null</code> nor empty.
   */
  public PlainSheet(String name) {
    metadata  = new Hashtable<>();
    titles    = new ArrayList<>();
    sheetName = name;
    classes   = null;
  }
  
  /**
   * @see #addColumn(Object)
   */
  public void addColumn( String title ) {
    titles.add( title );
    super.addColumn( title );
  }
  
  @Override
  public void addColumn(Object title) {
    throw new RuntimeException();
  }
  
  /**
   * @see #getValueAt(int, int)
   * 
   * @param column   The name of the column which value has to be returned. 
   *                 Neither <code>null</code> nor empty.
   */
  public <T> T getValueAt(int row, String column) {
    int idx = titles.indexOf(column);
    if (idx != -1) {
      return (T) super.getValueAt(row, idx);
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
    return metadata.keySet().toArray(new String[metadata.size()]);
  }
  
  /**
   * Returns a metadata information for a specific key.
   * 
   * @param key   The key used to access the metadata information.
   * 
   * @return   The metadata information or <code>null</code> if none has been set.
   */
  public String getMetadata(String key) {
    return metadata.get(key);
  }

  /**
   * Changes some metadata information for this sheet.
   * 
   * @param key     The key used to access the metadata information. Neither <code>null</code> nor empty.
   * @param value   The value of the metadata information. Neither <code>null</code> nor empty.
   */
  public void setMetadata(String key, String value) {
    metadata.put(key, value);
  }

  @Override
  public Class<?> getColumnClass(int index) {
    if (classes != null) {
      return classes[index];
    } else {
      return super.getColumnClass(index);
    }
  }

  /**
   * Calculates the datatypes for each column based on the content.
   */
  public void purify() {
    classes = new Class<?>[getColumnCount()];
    Arrays.fill(classes, Object.class);
    Set<Class<?>> classtypes = new HashSet<>();
    for (int col = 0; col < getColumnCount(); col++) {
      for (int row = 0; row < getRowCount(); row++) {
        Object cellvalue = getValueAt(row, col);
        if ((cellvalue != null) && (! (cellvalue instanceof ErrorValue))) {
          classtypes.add(cellvalue.getClass());
        }
      }
      if (classtypes.size() == 1) {
        // there was only one type so use that one
        classes[col] = classtypes.iterator().next();
      } else {
        /** @todo [15-Aug-2010:KASI]   We could calculate the type depending on the type hierarchies. */
      }
      classtypes.clear();
    }
  }
  
  /**
   * Returns the data of a single row.
   * 
   * @param row   The row which data is desired.
   * 
   * @return   The data of the row. Not <code>null</code>.
   */
  public Object[] getRow(int row) {
    Object[] result = new Object[getColumnCount()];
    for (int col = 0; col < result.length; col++) {
      result[col] = getValueAt(row, col);
    }
    return result;
  }
  
  /**
   * Returns <code>true</code> if the supplied row contains an error.
   * 
   * @param row   The row which has to be tested.
   * 
   * @return   <code>true</code> <=> The row contains an error.
   */
  public boolean containsError(int row) {
    for (int col = 0; col < getColumnCount(); col++) {
      Object cellvalue = getValueAt(row, col);
      if (cellvalue instanceof ErrorValue) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public String toString() {
    return getSheetName();
  }
  
  @Override
  public int compareTo(PlainSheet other) {
    if (other == null) {
      return 1;
    } else {
      return getSheetName().compareTo(other.getSheetName());
    }
  }
  
  /**
   * Provides a textual representation.
   * 
   * @param serializer   The simple serializer used to generate a textual representation from.
   *                     Maybe <code>null</code>.
   * 
   * @return   The textual representation.
   */
  public String serialize(SimpleSerializer serializer) {
    if (serializer == null) {
      serializer = new SimpleSerializer();
    }
    int columns = getColumnCount();
    int rows    = getRowCount();
    serializer.open("sheet", "name", getSheetName(), "columns", String.valueOf( columns ), "rows", String.valueOf(rows));
    for (int col = 0; col < columns; col++) {
      serializer.write("column", null, "name", getColumnName(col), "type", getColumnClass(col).getName());
    }
    for (int row = 0; row < rows; row++) {
      serializer.open("row", "row", String.valueOf(row));
      for (int col = 0; col < columns; col++) {
        Object value = getValueAt(row, col);
        if (value == null) {
          serializer.write(getColumnName(col), null);
        } else {
          serializer.write(getColumnName(col), String.valueOf(value));
        }
      }
      serializer.close();
    }
    serializer.close();
    return serializer.toString();
  }
  
} /* ENDCLASS */
