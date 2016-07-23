package com.kasisoft.libs.plex;

import org.apache.poi.ss.usermodel.*;

import com.kasisoft.libs.plex.api.*;
import com.kasisoft.libs.plex.instance.*;
import com.kasisoft.libs.plex.model.*;

import lombok.experimental.*;

import lombok.*;

import java.util.regex.*;

import java.util.*;

/**
 * Main logic used to run the import process.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
class ImportDriver {

  PLEXModel                            descriptor;
  Map<Pattern, PLEXSheetDescription>   importspecs;
  ApiManager                           apimanager;
  
  /**
   * Initialises this driver for the import process.
   * 
   * @param desc      The description model used for the import process. Not <code>null</code>.
   * @param manager   The managing class for the apis. Not <code>null</code>.
   * @param tracker   The tracker used to monitor the import process. Not <code>null</code>.
   * 
   * @throws PLEXException   The declaration seems to be invalid.
   */
  public ImportDriver( PLEXModel desc, ApiManager manager ) throws PLEXException {
    
    descriptor    = desc;
    apimanager    = manager;
    importspecs   = new Hashtable<>();
    for( PLEXSheetDescription description : descriptor.getSheet() ) {
      if( description.getNamepattern() != null ) {
        importspecs.put( Pattern.compile( description.getNamepattern() ), description );
      }
      if( description.getName() != null ) {
        importspecs.put( Pattern.compile( Pattern.quote( description.getName() ) ), description );
      }
    }
    
  }
  
  /**
   * Runs the import process for the supplied workbook.
   * 
   * @param workbook   The workbook which has to be imported. Not <code>null</code>.
   * @param monitor    The monitor which is used to keep track of the progress. Not <code>null</code>.
   * 
   * @return   The imported data. Not <code>null</code>.
   * 
   * @throws PLEXException   The import failed for some reason.
   */
  public PlainExcel importData( Workbook workbook, ImportMonitor monitor ) throws PLEXException  {
    PlainExcel  result    = new PlainExcel();
    int         count     = workbook.getNumberOfSheets();
    int         imported  = 0;
    for( int i = 0; i < count; i++ ) {
      Sheet   sheet = workbook.getSheetAt(i);
      String  name  = sheet.getSheetName();
      monitor.processingSheet( name, i + 1, count );
      if( (name != null) && (name.trim().length() > 0) ) {
        PLEXSheetDescription description = identifySheet( name );
        if( description != null ) {
          monitor.importingSheet( name, i + 1, count );
          importSheet( result, sheet, description, monitor );
          monitor.sheetImported( name, i + 1, count );
          imported++;
        }
      }
      monitor.sheetProcessed( name, i + 1, count );
    }
    monitor.resumeImport( imported, count );
    return result;
  }

  /**
   * Identifies the description which is apropriate for a specific sheet.
   * 
   * @param sheetname   The name of the sheet. Neither <code>null</code> nor empty.
   * 
   * @return   The description or <code>null</code> if there wasn't one.
   */
  private PLEXSheetDescription identifySheet( String sheetname ) {
    for( Map.Entry<Pattern,PLEXSheetDescription> importspec : importspecs.entrySet() ) {
      if( importspec.getKey().matcher( sheetname ).matches() ) {
        return importspec.getValue();
      }
    }
    return null;
  }
  
  private void importMetadata( PlainSheet tablemodel, PLEXMetadata metadata, Sheet sheet ) throws PLEXException{
    if( metadata != null ) {
      for( PLEXProperty property : metadata.getProperty() ) {
        tablemodel.setMetadata( property.getKey(), property.getValue() );
      }
      PLEXApiCall apicall = metadata.getMetadetect();
      if( apicall != null ) {
        Map<String,String> properties = apimanager.getMetadata( apicall.getRefid(), apicall.getArg(), sheet );
        if( properties != null ) {
          for( Map.Entry<String,String> entry : properties.entrySet() ) {
            tablemodel.setMetadata( entry.getKey(), entry.getValue() );
          }
        }
      }
    }
  }
  
  /**
   * Imports the content of the supplied sheet.
   * 
   * @param receiver      The data instance to collect the imported data. Not <code>null</code>.
   * @param sheet         The sheet which has to be imported. Not <code>null</code>.
   * @param description   The description used to drive the import process. 
   * @param monitor       The monitor which is used to keep track of the progress. Not <code>null</code>.
   */
  private void importSheet( PlainExcel receiver, Sheet sheet, PLEXSheetDescription description, ImportMonitor monitor ) throws PLEXException {
    
    PlainSheet    tablemodel  = new PlainSheet( sheet.getSheetName() );
    receiver.addTable( tablemodel );
    
    importMetadata( tablemodel, description.getMetadata(), sheet );
    
    // get the columns sorted according to the input order
    Column[]      columns     = calculateColumns( sheet, description );
    
    // create titles for the table
    for( Column column : columns ) {
      tablemodel.addColumn( column.title );
    }
    
    int rownum   = 0;
    int firstrow = getFirstRow( sheet, description );
    int rowcount = sheet.getLastRowNum() - firstrow;
    for( int row = firstrow; row <= sheet.getLastRowNum(); row++ ) {
      
      rownum++;
      monitor.importingRow( sheet.getSheetName(), rownum, rowcount );
      
      Row      rowobj  = sheet.getRow( row );
      if( rowobj == null ) {
        // there might be rows by the count but if there's no content, there's no data
        continue;
      }
      
      // setup the row data for this recorc
      Object[] rowdata = new Object[ columns.length ];
      Arrays.fill( rowdata, null );
      
      boolean  gotdata = false;
      for( int col = 0; col < columns.length; col++ ) {
        
        Column column       = columns[ col ];
        Object cellcontent  = getCellContent( rowobj, column.column );
        if( cellcontent != null ) {
          // there was some usable content
          rowdata[ col ]  = cellcontent;
          gotdata         = true;
        }
        
        // apply transformers if there were some (note: a transformer can change a null
        // value into a non-null value)
        if( column.transformer != null ) {
          for( int i = 0; i < column.transformer.size(); i++ ) {
            PLEXApiCall apicall = column.transformer.get(i);
            rowdata[ col ]      = apimanager.transformValue( apicall.getRefid(), apicall.getArg(), rowdata[ col ] );
          }
          if( rowdata[ col ] != null ) {
            gotdata = true;
          }
        }
      }
      if( gotdata ) {
        // only add rows which do provide some content
        tablemodel.addRow( rowdata );
      }
    }

    // fix the used types
    tablemodel.purify();
    
  }

  /**
   * Returns the content of a specific cell.
   * 
   * @param row   The row providing the data. Not <code>null</code>.
   * @param col   The column which cell content shall be returned.
   * 
   * @return   The cell content or <code>null</code> if the cell didn't contain anything.
   */
  private Object getCellContent( Row row, int col ) {
    Cell cell = row.getCell( col );
    if( cell != null ) {
      switch( cell.getCellType() ) {
      case Cell.CELL_TYPE_BLANK   : break;
      case Cell.CELL_TYPE_ERROR   : break;
      case Cell.CELL_TYPE_FORMULA : break; 
      case Cell.CELL_TYPE_BOOLEAN : return Boolean.valueOf( cell.getBooleanCellValue() );
      case Cell.CELL_TYPE_NUMERIC : return Double.valueOf( cell.getNumericCellValue() );
      case Cell.CELL_TYPE_STRING  : return cell.getStringCellValue();
      }
    }
    return null;
  }
  
  /**
   * Creates a list of column descriptions in sorted order.
   * 
   * @param sheet         The sheet providing the data. Not <code>null</code>.
   * @param description   Descriptional information for the sheet. Not <code>null</code>.
   * 
   * @return   A list of column descriptions. Not <code>null</code>.
   */
  private Column[] calculateColumns( Sheet sheet, PLEXSheetDescription description ) throws PLEXException {
    
    List<Column> columns = new ArrayList<>();
    
    // collect simple columns
    int          lastcol = -1;
    for( int i = 0; i < description.getColumn().size(); i++ ) {
      PLEXColumnDescription columndesc  = description.getColumn().get(i);
      Column column                     = new Column();
      if( (columndesc.getColumn() != null) || (columndesc.getColumndetect() != null) ) {
        // there is a column specification
        column.column                   = getColumn( sheet, columndesc );
      } else {
        // just use the last column index + 1
        column.column                   = lastcol + 1;
      }
      lastcol                           = column.column;
      column.title                      = columndesc.getTitle();
      column.transformer                = columndesc.getTransformer();
      columns.add( column );
    }
    
    Column[] result = new Column[ columns.size() ];
    columns.toArray( result );
    Arrays.sort( result );
    return result;
    
  }
  
  /**
   * Returns the column index either by value or an api call.
   * 
   * @param sheet    The sheet currently being used. Not <code>null</code>.
   * @param column   The actual column description. Not <code>null</code>.
   * 
   * @return   The column index which could be identified.
   */
  private int getColumn( Sheet sheet, PLEXColumnDescription column ) throws PLEXException {
    if( column.getColumn() != null ) {
      try {
        return Integer.parseInt( column.getColumn() );
      } catch( NumberFormatException ex ) {
        return toIndex( column.getColumn() );
      }
    } else /* if( column.getColumndetect() != null ) */ {
      PLEXApiCall apicall = column.getColumndetect();
      return apimanager.detectColumn( apicall.getRefid(), apicall.getArg(), sheet );
    }
  }

  /**
   * Returns the column associated with an alphabetical index.
   * 
   * @param column   The textual column. Neither <code>null</code> nor empty.
   * 
   * @return   The numerical index for the column.
   * 
   * @throws PLEXException   The column could not be parsed.
   */
  private int toIndex( String column ) throws PLEXException {
    column = column.toLowerCase();
    if ( column.length() == 1 ) {
      return column.charAt(0) - 'a';
    } else {
      int c1 = column.charAt(0) - 'a' + 1;
      int c2 = column.charAt(1) - 'a';
      return c1 * 26 + c2;
    }
  }

  /**
   * Returns the first row within a sheet or an api call.
   * 
   * @param sheet         The sheet currently being used. Not <code>null</code>.
   * @param description   The sheet which first row has to be calculated. 
   * 
   * @return   The column index which could be identified.
   */
  private int getFirstRow( Sheet sheet, PLEXSheetDescription description ) throws PLEXException {
    if( description.getFirstrow() != null ) {
      return description.getFirstrow().intValue();
    } else /* if( description.getFirstrowdetect() != null ) */ {
      PLEXApiCall apicall = description.getFirstrowdetect();
      return apimanager.detectRow( apicall.getRefid(), apicall.getArg(), sheet );
    }
  }

  /**
   * Simple helper datastructure used to describe a column.
   */
  private static final class Column implements Comparable<Column> {

    public int                 column;
    public String              title;
    public List<PLEXApiCall>   transformer;
    
    /**
     * {@inheritDoc}
     */
    public int compareTo( Column other ) {
      return Integer.valueOf( column ).compareTo( Integer.valueOf( other.column ) );
    }
    
  } /* ENDCLASS */
  
} /* ENDCLASS */
