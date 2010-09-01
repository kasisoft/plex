/**
 * Name........: CustomObjectFactory
 * Description.: Customised implementation of the ObjectFactory in order to override some types.
 * Author......: Daniel Kasmeroglu
 * E-Mail......: daniel.kasmeroglu@kasisoft.net
 * Company.....: Kasisoft
 * License.....: LGPL
 */
package com.kasisoft.lgpl.plex.customised;

import com.kasisoft.lgpl.plex.model.*;

import javax.xml.bind.annotation.*;

/**
 * Customised implementation of the ObjectFactory in order to override some types.
 */
@XmlRegistry
public class CustomObjectFactory extends ObjectFactory {

  /**
   * {@inheritDoc}
   */
  public PLEXInjector createPLEXInjector() {
    return super.createPLEXInjector();
  }

} /* ENDCLASS */
