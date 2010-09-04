/**
 * Name........: MetadataProvider
 * Description.: Each implementor is capable to provide metadata information for a specific sheet.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.libs.plex.api;

import com.kasisoft.lgpl.libs.plex.*;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Each implementor is capable to provide metadata information for a specific sheet.
 */
public interface MetadataProvider extends ApiDefinition {

  /**
   * Returns a map providing the metadata.
   * 
   * @param id      The ID of the api instance. Neither <code>null</code> nor empty.
   * @param sheet   The sheet used to access the metadata. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The map providing the metadata. Not <code>null</code>.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  Map<String,String> getMetadata( String id, Sheet sheet, String ... args ) throws PLEXException;
  
} /* ENDINTERFACE */