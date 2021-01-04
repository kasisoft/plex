package com.kasisoft.libs.plex.instance;

import com.kasisoft.libs.common.text.*;

import lombok.experimental.*;

import lombok.*;

import java.util.*;

/**
 * Basic implementation of a simple serializer allowing to emit structured information.
 * 
 * @author daniel.kasmeroglu@kasisoft.net
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleSerializer {

  static final String INDENTION = "  ";
  
  StringFBuffer   buffer;
  StringBuffer    indention;
  Stack<String>   tags;
  
  /**
   * Initializes this serializer.
   */
  public SimpleSerializer() {
    buffer    = new StringFBuffer();
    indention = new StringBuffer();
    tags      = new Stack<>();
  }
  
  /**
   * Indents the current context.
   */
  public void indent() {
    indention.append( INDENTION );
  }
  
  /**
   * Dedents the current context.
   */
  public void dedent() {
    if( indention.length() >= INDENTION.length() ) {
      indention.setLength( indention.length() - INDENTION.length() );
    }
  }
  
  /**
   * Open a specific tag.
   * 
   * @param tag     The tag that has to be opened. Neither <code>null</code> nor empty.
   * @param attrs   A list of pairs describing attributes. Maybe <code>null</code>.
   */
  public void open( String tag, String ... attrs ) {
    tags.push( tag );
    if( (attrs != null) && (attrs.length > 0) ) {
      buffer.appendF( "%s<%s", indention, tag );
      for( int i = 0; i < attrs.length; i += 2 ) {
        buffer.appendF( " %s=\"%s\"", attrs[ i + 0 ], attrs[ i + 1 ] );
      }
      buffer.append( ">\n" );
    } else {
      buffer.appendF( "%s<%s>\n", indention, tag );
    }
    indent();
  }
  
  /**
   * Closes the last opened tag.
   */
  public void close() {
    dedent();
    String tag = tags.pop();
    buffer.appendF( "%s</%s>\n", indention, tag );
  }
  
  /**
   * Writes a tag with it's content.
   * 
   * @param tag       The tag that has to be opened. Neither <code>null</code> nor empty.
   * @param content   The content used for the tag. Maybe <code>null</code>.
   * @param attrs     A list of pairs describing attributes. Maybe <code>null</code>.
   */
  public void write( String tag, String content, String ... attrs ) {
    if( (attrs != null) && (attrs.length > 0) ) {
      buffer.appendF( "%s<%s", indention, tag );
      for( int i = 0; i < attrs.length; i += 2 ) {
        buffer.appendF( " %s=\"%s\"", attrs[ i + 0 ], attrs[ i + 1 ] );
      }
      if( content == null ) {
        buffer.append( "/>\n" );
      } else {
        buffer.appendF( ">%s</%s>\n", content, tag );
      }
    } else {
      if( content == null ) {
        buffer.appendF( "%s<%s/>\n", indention, tag );
      } else {
        buffer.appendF( "%s<%s>%s</%s>\n", indention, tag, content, tag );
      }
    }
  }
  
  @Override
  public String toString() {
    return buffer.toString();
  }
  
} /* ENDCLASS */
