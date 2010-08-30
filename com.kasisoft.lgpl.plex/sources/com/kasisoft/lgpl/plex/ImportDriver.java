/**
 * Name........: ImportDriver
 * Description.: Main logic used to run the import process. 
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

import com.kasisoft.lgpl.plex.api.*;
import com.kasisoft.lgpl.plex.instance.*;
import com.kasisoft.lgpl.plex.model.*;
import com.kasisoft.lgpl.tools.diagnostic.*;

import org.apache.poi.ss.usermodel.*;

import java.util.regex.*;

import java.util.*;

/**
 * Main logic used to run the import process.
 */
class ImportDriver {

  private static final String MSG_INVALIDCOLUMN = "The column '%s' could not be parsed !";
  private PLEXModel                           descriptor;
  private ImportTracker                       importtracker;
  private Map<Pattern,PLEXSheetDescription>   importspecs;
  private ApiManager                          apimanager;
  
  /**
   * Initialises this driver for the import process.
   * 
   * @param desc      The description model used for the import process. Not <code>null</code>.
   * @param tracker   The tracker used to monitor the import process. Not <code>null</code>.
   * 
   * @throws PLEXException   The declaration seems to be invalid.
   */
  public ImportDriver( 
    @KNotNull(name="desc")      PLEXModel       desc, 
    @KNotNull(name="tracker")   ImportTracker   tracker 
  ) throws PLEXException {
    
    importtracker = tracker;
    descriptor    = desc;
    importspecs   = new Hashtable<Pattern,PLEXSheetDescription>();
    for( PLEXSheetDescription description : descriptor.getSheet() ) {
      importspecs.put( Pattern.compile( description.getNamepattern() ), description );
    }
    
    PLEXGeneral general = descriptor.getGeneral();
    apimanager          = new ApiManager( general.getInterface() );
    
  }
  
  /**
   * Runs the import process for the supplied workbook.
   * 
   * @param workbook   The workbook which has to be imported. Not <code>null</code>.
   * 
   * @return   The imported data.
   * 
   * @throws PLEXException   The import failed for some reason.
   */
  public PlainExcel importData( @KNotNull(name="workbook") Workbook workbook ) throws PLEXException  {
    
    PlainExcel  result  = new PlainExcel();
    int       count   = workbook.getNumberOfSheets();
    
    for( int i = 0; i < count; i++ ) {
      
      Sheet   sheet = workbook.getSheetAt(i);
      String  name  = sheet.getSheetName();
      
      if( (name == null) || (name.trim().length() == 0) ) {
        importtracker.sheetWithoutName( workbook, sheet, i );
      } else {
        PLEXSheetDescription description = identifySheet( name );
        if( description == null ) {
          importtracker.unhandledSheet( workbook, sheet, i );
        } else {
          importtracker.handlingSheet( workbook, sheet, i );
          importSheet( result, sheet, description );
        }
      }
      
    }
    
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
  
  /**
   * Imports the content of the supplied sheet.
   * 
   * @param receiver      The data instance to collect the imported data. Not <code>null</code>.
   * @param sheet         The sheet which has to be imported. Not <code>null</code>.
   * @param description   The description used to drive the import process. 
   */
  private void importSheet( PlainExcel receiver, Sheet sheet, PLEXSheetDescription description ) throws PLEXException {
    
    PlainSheet    tablemodel  = new PlainSheet( sheet.getSheetName() );
    receiver.addTable( tablemodel );
    
    PLEXMetadata  metadata    = description.getMetadata();
    if( metadata != null ) {
      for( PLEXProperty property : metadata.getProperty() ) {
        tablemodel.setMetadata( property.getKey(), property.getValue() );
      }
      if( metadata.getMetadetect() != null ) {
        Map<String,String> properties = apimanager.getMetadata( metadata.getMetadetect().getRefid(), metadata.getMetadetect().getArg(), sheet );
        if( properties != null ) {
          for( Map.Entry<String,String> entry : properties.entrySet() ) {
            tablemodel.setMetadata( entry.getKey(), entry.getValue() );
          }
        }
      }
    }
    
    // get the columns sorted according to the input order
    Column[]            columns     = calculateColumns( sheet, description );
    
    // create titles for the table
    for( Column column : columns ) {
      tablemodel.addColumn( column.title );
    }
    
    int firstrow = getFirstRow( sheet, description.getFirstrow(), description.getFirstrowdetect() );
    for( int row = firstrow; row <= sheet.getLastRowNum(); row++ ) {
      
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
    
    List<Column> columns = new ArrayList<Column>();
    
    // collect simple columns
    for( PLEXColumnDescription columndesc : description.getColumn() ) {
      Column column       = new Column();
      column.column       = getColumn( sheet, columndesc.getColumn(), columndesc.getColumndetect() );
      column.title        = columndesc.getTitle();
      column.transformer  = columndesc.getTransformer();
      columns.add( column );
    }
    
    // collect groups of columns
    for( PLEXColumnGroup columngroup : description.getColumngroup() ) {
      int columnbase  = getColumn( sheet, columngroup.getColumn(), columngroup.getColumndetect() );
      int count       = getCount( sheet, columnbase, columngroup.getCount(), columngroup.getCountdetect() );
      if( count > 0 ) {
        for( int i = 0; i < count; i++ ) {
          int max = columnbase;
          for( PLEXColumnGroupMember member : columngroup.getMember() ) {
            Column column       = new Column();
            column.column       = columnbase + member.getOffset();
            column.title        = member.getTitle() + "[" + i + "]";
            column.transformer  = member.getTransformer();
            columns.add( column );
            max           = Math.max( column.column, max );
          }
          columnbase  = max + 1;
        }
      }
    }
    
    Column[] result = new Column[ columns.size() ];
    columns.toArray( result );
    Arrays.sort( result );
    return result;
    
  }
  
  /**
   * Returns the column index either by value or an api call.
   * 
   * @param sheet     The sheet currently being used. Not <code>null</code>.
   * @param column    The actual column value.
   * @param apicall   The api call description used to identify the column value. Maybe <code>null</code>.
   * 
   * @return   The column index which could be identified.
   */
  private int getColumn( Sheet sheet, String column, PLEXApiCall apicall ) throws PLEXException {
    int colidx = -1;
    if( column != null ) {
      try {
        colidx = Integer.parseInt( column );
      } catch( NumberFormatException ex ) {
        colidx = toIndex( column );
      }
    }
    if( colidx >= 0 ) {
      return colidx;
    } else {
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
    if( column.length() > 2 ) {
      throw new PLEXException( PLEXFailure.DeclarationError, MSG_INVALIDCOLUMN, column  );
    }
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
   * Returns the count of column groups either by value or an api call.
   * 
   * @param sheet         The sheet currently being used. Not <code>null</code>.
   * @param firstcolumn   The column where the column group starts.
   * @param count         The actual count value.
   * @param apicall       The api call description used to identify the column value. Maybe <code>null</code>.
   * 
   * @return   The column index which could be identified.
   */
  private int getCount( Sheet sheet, int firstcolumn, int count, PLEXApiCall apicall ) throws PLEXException {
    if( count >= 0 ) {
      return count;
    } else {
      return apimanager.detectCount( apicall.getRefid(), apicall.getArg(), sheet, firstcolumn );
    }
  }

  /**
   * Returns the first row within a sheet or an api call.
   * 
   * @param sheet         The sheet currently being used. Not <code>null</code>.
   * @param firstrow      The actual row value.
   * @param apicall       The api call description used to identify the column value. Maybe <code>null</code>.
   * 
   * @return   The column index which could be identified.
   */
  private int getFirstRow( Sheet sheet, int firstrow, PLEXApiCall apicall ) throws PLEXException {
    if( firstrow >= 0 ) {
      return firstrow;
    } else if( apicall != null ) {
      return apimanager.detectRow( apicall.getRefid(), apicall.getArg(), sheet );
    } else {
      throw new PLEXException( PLEXFailure.DeclarationError, "Missing first row !" );
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
