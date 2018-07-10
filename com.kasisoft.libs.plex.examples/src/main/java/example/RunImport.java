/**
 * Name........: RunImport
 * Description.: Sample application used for the import.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package example;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.instance.*;

import java.net.*;
import java.io.*;

/**
 * Sample application used for the import.
 */
public class RunImport {

  public static final void main( String[] args ) {

    if( args.length == 0 ) {
      System.err.println( "Missing argument !" );
      return;
    }
    
    String   name        = args[0];
    File     declaration = new File( String.format( "%s.plex", name ) );
    Importer importer    = null;
    try {
      importer = new Importer( declaration.toURI().toURL() );
    } catch( MalformedURLException ex ) {
      // we've made sure that the resource and thus the URL is correct, so ignore this
      return;
    } catch( PLEXException ex ) {
      // the declaration file was obviously invalid
      ex.printStackTrace();
      return;
    }
    
    try {
      File       excel    = new File( String.format( "%s.xls", name ) );
      PlainExcel plex     = importer.runImport( excel );
      System.out.println( plex );
    } catch( PLEXException ex ) {
      // the import failed for some reason
      ex.printStackTrace();
    }

  }
  
} /* ENDCLASS */
