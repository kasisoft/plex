package com.kasisoft.libs.plex.api;

import com.kasisoft.libs.plex.*;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;

/**
 * Resolver used to identify row indices.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public interface RowResolver extends ApiDefinition {

  /**
   * Detects a row index.
   * 
   * @param id      The ID of the api instance. Neither <code>null</code> nor empty.
   * @param sheet   The sheet which column has to be detected. Not <code>null</code>.
   * @param args    The arguments provided by the declaration file. Maybe <code>null</code>.
   * 
   * @return   The detected row index.
   * 
   * @throws PLEXException   The execution failed for some reason.
   */
  int detectRow(@NotBlank String id, @NotNull Sheet sheet, String ... args) throws PLEXException;
  
} /* ENDINTERFACE */
