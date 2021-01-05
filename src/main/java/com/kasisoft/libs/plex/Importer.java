package com.kasisoft.libs.plex;

import static com.kasisoft.libs.plex.internal.Messages.*;

import com.kasisoft.libs.common.lang.*;
import com.kasisoft.libs.common.text.*;
import com.kasisoft.libs.plex.api.*;
import com.kasisoft.libs.plex.instance.*;
import com.kasisoft.libs.plex.model.*;

import org.apache.poi.ss.usermodel.*;

import javax.validation.constraints.*;
import javax.xml.*;
import javax.xml.bind.*;
import javax.xml.validation.*;

import java.util.*;

import java.net.*;

import java.io.*;

import java.lang.reflect.*;

import lombok.experimental.*;

import lombok.*;

/**
 * This class provides the importing capabilities controlled by a corresponding descriptor.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Importer {

  ImportDriver    driver;
  ImportMonitor   monitor;
  
  /**
   * Initializes this importer using a specific declarator.
   * 
   * @param declaration   The declarator used for the import process. Not <code>null</code>.
   */
  public Importer(@NotNull URL declaration) {

    // load the schema used for the plex declaration
    Schema schema = null;
    try {
      SchemaFactory factory   = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      URL           schemaurl = Importer.class.getResource("/plex.xsd");
      schema                  = factory.newSchema(schemaurl);
    } catch (Exception ex) {
      throw PLEXException.wrap(missing_schema, ex);
    }

    try {
      
      // load the plex declaration
      JAXBContext   context       = JAXBContext.newInstance(ObjectFactory.class);
      Unmarshaller  unmarshaller  = context.createUnmarshaller();
      unmarshaller.setSchema(schema);
      
      PLEXModel     plexmodel     = (PLEXModel) unmarshaller.unmarshal(declaration);
  
      // initialize the import driver
      ApiManager    manager       = createApiManager(plexmodel);

      checkConsistency(manager, plexmodel);
      
      driver                      = new ImportDriver(plexmodel, manager);
    
    } catch (Exception  ex) {
      throw PLEXException.wrap(declaration_error.format(invalid_declaration), ex);
    }
   
    monitor = ImportMonitor.DEFAULT;
    
  }
  
  /**
   * Changes the current monitor to be used in order to keep track of the progress.
   * 
   * @param newmonitor   The new monitor to be used. Maybe <code>null</code>.
   */
  public void setImportMonitor(ImportMonitor newmonitor) {
    monitor = newmonitor;
    if (monitor == null) {
      monitor = ImportMonitor.DEFAULT;
    }
  }
  
  /**
   * Invokes the import process for the supplied excel document.
   * 
   * @param excel   The excel document which has to be imported. Must be non <code>null</code> and
   *                a regular file.
   *                
   * @return   A plain representation of a excel data. Not <code>null</code>.
   */
  public PlainExcel runImport(@NotNull File excel) {
    try (InputStream instream = new FileInputStream(excel)) {
      monitor.openingWorkbook(excel);
      try (Workbook workbook = WorkbookFactory.create(instream)) {
        monitor.openedWorkbook(workbook.getNumberOfSheets());
        return driver.importData(workbook, monitor);
      }
    } catch (Exception ex) {
      throw PLEXException.wrap(io.format(excel), ex);
    }
  }

  /**
   * Creates a new instance of an api manager based upon a specific plex declaration.
   * 
   * @param model   The plex declaration which provides the necessary information for the
   *                api definitions.
   * 
   * @return   A new instance of an api manager. Not <code>null</code>.
   */
  private ApiManager createApiManager(@NotNull PLEXModel model) {
    Map<String, ApiDefinition> result = new Hashtable<>();
    if (model.getGeneral() != null) {
      for (PLEXInterface plexinterface : model.getGeneral().getInterface()) {
        String classname = plexinterface.getClassname();
        Object instance  = ReflectionFunctions.newInstance(classname);
        if (instance == null) {
          throw PLEXException.wrap(declaration_error.format(failed_instantiation.format(classname)));
        }
        if (!plexinterface.getInjectors().isEmpty()) {
          configureInstance(instance, plexinterface.getInjectors());
        }
        result.put(plexinterface.getId(), (ApiDefinition) instance);
      }
    }
    return new ApiManager(result);
  }
  
  /**
   * Configures a specified instance according to the injections provided by the declaration file.
   * 
   * @param instance    The instance which will be altered. Not <code>null</code>.
   * @param injectors   The list of injections that have to be performed. Not <code>null</code>.
   */
  private void configureInstance(@NotNull Object instance, @NotNull List<PLEXInjector> injectors) {
    injectors.forEach($ -> configureInstance(instance, $));
  }
  
  /**
   * Configures a specified instance according to a injection provided by the declaration file.
   * 
   * @todo [15-Aug-2010:KASI]   Maybe the methods should be cached but for now there's no
   *                            performance related constraint.
   * 
   * @param instance   The instance which will be altered. Not <code>null</code>.
   * @param injector   The injection that has to be performed. Not <code>null</code>.
   */
  private void configureInstance(@NotNull Object instance, @NotNull PLEXInjector injector) {
    
    Object value  = getValue (injector);
    String setter = getSetter(injector);
    
    try {
      
      Method method = lookupMethod(instance.getClass(), setter);
      if (method == null) {
        throw new NoSuchMethodException();
      }
      
      Class<?>[] paramtypes = method.getParameterTypes();
      if ((paramtypes == null) || (paramtypes.length != 1)) {
        throw new NoSuchMethodException();
      }
      
      method.invoke(instance, value);
      
    } catch (Exception ex) {
      throw PLEXException.wrap(declaration_error.format(failed_configuration.format(instance.getClass().getName())), ex);
    }
    
  }
  
  private Method lookupMethod(Class<?> clazz, String setter) {
    Method[] methods = clazz.getMethods();
    for (Method method : methods) {
      if (setter.equals(method.getName())) {
        return method;
      }
    }
    return null;
  }
  
  /**
   * Returns the name of the setter method identified through the supplied injector.
   * 
   * @param injector   The injector instance used to identify the apropriate setter.
   *                   Not <code>null</code>.
   *                   
   * @return   The setter name for the injection process. Neither <code>null</code> nor empty.
   */
  private String getSetter(@NotNull PLEXInjector injector) {
    String name = injector.getName();
    if (name.length() == 1) {
      return String.format("set%s", name.toUpperCase());
    } else {
      return 
        String.format( "set%s%s", 
          String.valueOf(Character.toUpperCase(injector.getName().charAt(0))), 
          injector.getName().substring(1) 
        );
    }
  }

  /**
   * Returns the value which has to be set for the injection.
   * 
   * @param injector   The injector which provides the value. Not <code>null</code>.
   * 
   * @return   The value associated with the supplied injector. Not <code>null</code>.
   */
  private Object getValue(@NotNull PLEXInjector injector) {
           if (injector instanceof PLEXBoolean) { return Boolean.valueOf(((PLEXBoolean) injector).isValue());
    } else if (injector instanceof PLEXInteger) { return Integer.valueOf(((PLEXInteger) injector).getValue());
    } else if (injector instanceof PLEXString)  { return ((PLEXString) injector).getValue();
    } else if (injector instanceof PLEXDouble)  { return Double.valueOf(((PLEXDouble) injector).getValue());
    } else /* if( injector instanceof PLEXStringList ) */ {
      PLEXStringList list   = (PLEXStringList) injector;
      List<String>   result = new ArrayList<>();
      for (int i = 0; i < list.getItem().size(); i++) {
        result.add(list.getItem().get(i));
      }
      return result;
    }
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman   The api manager used to access the functions. Not <code>null</code>.
   * @param model    The declaration which has to be checked for consistency errors. Not <code>null</code>.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXModel model) {
    checkConsistency(apiman, model.getGeneral());
    model.getSheet().forEach($ -> checkConsistency(apiman, $));
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman    The api manager used to access the functions. Not <code>null</code>.
   * @param general   The declaration which has to be checked for consistency errors. Not <code>null</code>.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXGeneral general) {
    general.getInterface().forEach($ -> checkConsistency(apiman, $));
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman    The api manager used to access the functions. Not <code>null</code>.
   * @param apidecl   The declaration which has to be checked for consistency errors. Not <code>null</code>.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXInterface apidecl) {
    String classname = apidecl.getClassname();
    try {
      Class<?> clazz = Class.forName(classname);
      switch (apidecl.getApi()) {
      case COLUMN     : checkType (ColumnResolver   . class, clazz); break;
      case METADATA   : checkType (MetadataProvider . class, clazz); break;
      case ROW        : checkType (RowResolver      . class, clazz); break;
      case TRANSFORM  : checkType (ValueTransform   . class, clazz); break;
      }
    } catch (ClassNotFoundException ex) {
      throw PLEXException.wrap(declaration_error.format(missing_classname.format(classname)));
    }
  }
  
  /**
   * Checks whether a specific type supports a specific api.
   * 
   * @param requiredapi   The API that needs to be supported. Not <code>null</code>.
   * @param currenttype   The current type that needs to be tested. Not <code>null</code>.
   */
  private void checkType(@NotNull Class<?> requiredapi, @NotNull Class<?> currenttype) {
    if (!requiredapi.isAssignableFrom(currenttype)) {
      throw PLEXException.wrap(declaration_error.format(invalid_type.format(currenttype.getName(), requiredapi.getName())));
    }
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman   The api manager used to access the functions. Not <code>null</code>.
   * @param sheet    The declaration which has to be checked for consistency errors. Not <code>null</code>.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXSheetDescription sheet) {
    if ((sheet.getFirstrow() == null) && (sheet.getFirstrowdetect() == null)) {
      throw PLEXException.wrap(declaration_error.format(missing_first_row));
    }
    PLEXApiCall apicall = sheet.getFirstrowdetect();
    if (apicall != null) {
      if (!apiman.isRowResolver(apicall.getRefid())) {
        throw PLEXException.wrap(declaration_error.format( missing_row_resolver.format(apicall.getRefid())));
      }
      if (!apiman.canHandleArgs(apicall.getRefid(), apicall.getArg())) {
        throw PLEXException.wrap(declaration_error.format(syntax_row_resolver.format(StringFunctions.toString(apicall.getArg().toArray()), apicall.getRefid())));
      }
    }
    for (int i = 0; i < sheet.getColumn().size(); i++) {
      checkConsistency(apiman, sheet.getColumn().get(i), i == 0);
    }
    if (sheet.getMetadata() != null) {
      checkConsistency(apiman, sheet.getMetadata());
    }
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman     The api manager used to access the functions. Not <code>null</code>.
   * @param metadata   The declaration which has to be checked for consistency errors. Not <code>null</code>.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXMetadata metadata) {
    PLEXApiCall apicall = metadata.getMetadetect();
    if (apicall != null) {
      if (!apiman.isMetdataProvider(apicall.getRefid())) {
        throw PLEXException.wrap(declaration_error.format(missing_metadata_provider.format(apicall.getRefid())));
      }
      if (!apiman.canHandleArgs(apicall.getRefid(), apicall.getArg())) {
        throw PLEXException.wrap(declaration_error.format( syntax_metadata_provider.format(StringFunctions.toString(apicall.getArg().toArray()), apicall.getRefid())));
      }
    }
  }
  
  /**
   * Checks the consistency for the supplied model.
   * 
   * @param apiman     The api manager used to access the functions. Not <code>null</code>.
   * @param metadata   The declaration which has to be checked for consistency errors. Not <code>null</code>.
   * @param first      <code>true</code> <=> The first column is about to be checked.
   */
  private void checkConsistency(@NotNull ApiManager apiman, @NotNull PLEXColumnDescription column, boolean first) {
    if ((column.getColumn() == null) && (column.getColumndetect() == null)) {
      if (first) {
        throw PLEXException.wrap(declaration_error.format(missing_column_infos));
      }
    }
    PLEXApiCall apicall = column.getColumndetect();
    if (apicall != null) {
      if (!apiman.isColumnResolver(apicall.getRefid())) {
        throw PLEXException.wrap(declaration_error.format(missing_column_resolver.format(apicall.getRefid())));
      }
      if (!apiman.canHandleArgs(apicall.getRefid(), apicall.getArg())) {
        throw PLEXException.wrap(declaration_error.format(syntax_column_resolver.format(StringFunctions.toString(apicall.getArg().toArray()), apicall.getRefid())));
      }
    }
  }
  
} /* ENDCLASS */
