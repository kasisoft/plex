/**
 * Name........: Importer
 * Description.: This class provides the importing capabilities controlled by a corresponding
 *               descriptor.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

import com.kasisoft.lgpl.libs.common.io.*;

import com.kasisoft.lgpl.plex.instance.*;
import com.kasisoft.lgpl.plex.model.*;

import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.ss.usermodel.*;
import org.xml.sax.*;

import javax.xml.*;
import javax.xml.bind.*;
import javax.xml.validation.*;

import java.net.*;

import java.io.*;

/**
 * This class provides the importing capabilities controlled by a corresponding descriptor.
 */
public class Importer {

  private ImportDriver   driver;
  
  /**
   * Initializes this importer using a specific declarator.
   * 
   * @param declaration   The declarator used for the import process. Not <code>null</code>.
   * @param tracker       The tracker used for informational purposes. Maybe <code>null</code>.
   * 
   * @throws PLEXException   The import process failed for some reason.
   */
  public Importer( URL declaration, ImportTracker tracker ) throws PLEXException {

    if( tracker == null ) {
      tracker = new DefaultImportTracker();
    }

    // load the schema used for the plex declaration
    Schema schema = null;
    try {
      SchemaFactory factory   = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
      URL           schemaurl = Importer.class.getResource( "/plex.xsd" );
      schema                  = factory.newSchema( schemaurl );
    } catch( SAXException   ex ) {
      throw new PLEXException( PLEXFailure.MissingSchema, ex );
    }

    try {
      
      // load the plex declaration
      JAXBContext   context       = JAXBContext.newInstance( ObjectFactory.class );
      Unmarshaller  unmarshaller  = context.createUnmarshaller();
      unmarshaller.setSchema( schema );
      
      PLEXModel     plexmodel     = (PLEXModel) unmarshaller.unmarshal( declaration );
  
      // initialize the import driver
      driver                      = new ImportDriver( plexmodel, tracker );
    
    } catch( JAXBException  ex ) {
      throw new PLEXException( PLEXFailure.DeclarationError, ex );
    }
    
  }
  
  /**
   * Invokes the import process for the supplied excel document.
   * 
   * @param excel   The excel document which has to be imported. Must be non <code>null</code> and
   *                a regular file.
   *                
   * @return   A plain representation of a excel data. Not <code>null</code>.
   * 
   * @throws PLEXException   The import process failed for some reason.
   */
  public PlainExcel runImport( File excel ) throws PLEXException {
    Workbook    workbook = null;
    InputStream instream = null;
    try {
      instream  = new FileInputStream( excel );
      workbook  = WorkbookFactory.create( instream );
    } catch( IOException            ex ) {
      throw new PLEXException( PLEXFailure.IO, ex, excel );
    } catch( InvalidFormatException ex ) {
      throw new PLEXException( PLEXFailure.InvalidExcel, ex, excel );
    } finally {
      IoFunctions.close( instream );
    }
    return driver.importData( workbook );
  }
  
} /* ENDCLASS */
