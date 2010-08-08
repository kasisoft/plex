/**
 * Name........: MetadataProvider
 * Description.: Each implementor is capable to provide metadata information for a specific sheet.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.api;

import org.apache.poi.ss.usermodel.*;

import java.util.*;

/**
 * Each implementor is capable to provide metadata information for a specific sheet.
 */
public interface MetadataProvider {

  /**
   * Returns a map providing the metadata.
   * 
   * @param sheet   The sheet used to access the metadata. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The map providing the metadata. Not <code>null</code>.
   */
  Map<String,String> getMetadata( Sheet sheet, String ... args );
  
} /* ENDINTERFACE */