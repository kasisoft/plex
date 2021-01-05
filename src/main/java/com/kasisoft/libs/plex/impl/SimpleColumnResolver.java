package com.kasisoft.libs.plex.impl;

import static com.kasisoft.libs.plex.internal.Messages.*;

import com.kasisoft.libs.plex.*;
import com.kasisoft.libs.plex.api.*;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;

import java.util.*;

import lombok.experimental.*;

import lombok.*;

/**
 * This ColumnResolver implementation simply checks for the occurrence of the first column which 
 * contains content.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleColumnResolver implements ColumnResolver {

  @Override
  public int detectColumn(@NotBlank String id, @NotNull Sheet sheet, String... args) throws PLEXException {
    int result = detectColumn(sheet);
    if (result == -1) {
      throw PLEXException.wrap(error_in_api_function.format(missing_column.format(sheet.getSheetName())));
    }
    int offset = 0;
    if (args.length > 0) {
      offset = Integer.parseInt(args[0]);
    }
    return result + offset;
  }

  private int detectColumn(@NotNull Sheet sheet) {
    Row row = sheet.getRow( sheet.getFirstRowNum() );
    for (int i = 0; i < 256; i++) {
      Cell cell = row.getCell(i);
      if ((cell != null) && (cell.getCellType() != Cell.CELL_TYPE_BLANK)) {
        return i;
      }
    }
    return -1;
  }
  
  @Override
  public boolean canHandleArguments(@NotBlank String id, @NotNull List<String> args) {
    if (!args.isEmpty()) {
      try {
        Integer.parseInt(args.get(0));
      } catch (NumberFormatException ex) {
        return false;
      }
    }
    return true;
  }

} /* ENDCLASS */
