/**
 * Name........: PLEXFailure
 * Description.: Enumeration identifying a plex failure.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex;

/**
 * Enumeration identifying a plex failure.
 */
public enum PLEXFailure {

  MissingApiFunction,
  ErrorInApiFunction,
  IO,
  InvalidExcel,
  DeclarationError,
  MissingSchema;
  
} /* ENDENUM */
