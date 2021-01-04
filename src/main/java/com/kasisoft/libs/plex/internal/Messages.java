package com.kasisoft.libs.plex.internal;

import com.kasisoft.libs.common.i18n.*;

/**
 * Collection of messages that could be overridden if necessary.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
public class Messages {

  @I18N("%s")
  public static I18NFormatter      declaration_error;

  @I18N("An error occured within an API function ! Cause: %s")
  public static I18NFormatter      error_in_api_function;

  @I18N("Failed to configure the api type '%s' !")
  public static I18NFormatter      failed_configuration;
  
  @I18N("Failed to instantiate class '%s' !")
  public static I18NFormatter      failed_instantiation; 

  @I18N("The plex declaration is invalid.")
  public static String             invalid_declaration; 

//  @I18N("The excel file '%s' is not valid !")
//  public  static I18NFormatter      invalid_excel;

  @I18N("%s: The value isn't a proper number.")
  public static I18NFormatter      invalid_number; 
    
  @I18N("The type '%s' doesn't implement '%s' !")
  public static I18NFormatter      invalid_type; 
    
  @I18N("The resource '%s' could not be loaded !")
  public static I18NFormatter      io;
  
  @I18N("The class '%s' is not available on the classpath !")
  public static I18NFormatter      missing_classname; 

  @I18N("A column for sheet '%s' could not be detected !")
  public static I18NFormatter      missing_column; 

  @I18N("A 'ColumnResolver' with the id '%s' doesn't exist !")
  public static I18NFormatter      missing_column_resolver; 

  @I18N("A first column must declare either the 'column' or 'columndetect' !")
  public static String             missing_column_infos;
    
  @I18N("A sheet must declare either the 'firstrow' or 'firstrowdetect' setting!")
  public static String             missing_first_row;
    
  @I18N("A 'RowResolver' with the id '%s' doesn't exist !")
  public static I18NFormatter      missing_row_resolver;
    
  @I18N("The schema 'plex.xsd' could not be found !")
  public static String             missing_schema;

  @I18N("A 'MetadataProvider' with the id '%s' doesn't exist !")
  public static I18NFormatter      missing_metadata_provider;
    
  @I18N("%s: The value is not supposed to contain a rest (=%s).")
  public static I18NFormatter      strict_error; 
  
  @I18N("The arguments {%s} for the 'ColumnResolver' with the id '%s' aren't valid !")
  public static I18NFormatter      syntax_column_resolver;

  @I18N("The arguments {%s} for the 'MetadataProvider' with the id '%s' aren't valid !")
  public static I18NFormatter      syntax_metadata_provider;
    
  @I18N("The arguments {%s} for the 'RowResolver' with the id '%s' aren't valid !")
  public static I18NFormatter      syntax_row_resolver;
  
  static {
    I18NSupport.initialize( Messages.class );
  }
  
} /* ENDCLASS */
