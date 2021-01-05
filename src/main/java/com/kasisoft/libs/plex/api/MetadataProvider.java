package com.kasisoft.libs.plex.api;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;

import java.util.*;

/**
 * Each implementor is capable to provide metadata information for a specific sheet.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
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
   */
  Map<String, String> getMetadata(@NotBlank String id, @NotNull Sheet sheet, String ... args);
  
} /* ENDINTERFACE */