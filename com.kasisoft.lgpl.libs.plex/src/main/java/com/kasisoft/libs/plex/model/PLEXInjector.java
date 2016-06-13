







package com.kasisoft.libs.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInjector")
@XmlSeeAlso({
    PLEXDouble.class,
    PLEXStringList.class,
    PLEXBoolean.class,
    PLEXString.class,
    PLEXInteger.class
})
public class PLEXInjector {

    @XmlAttribute(required = true)
    protected String name;

    
    public String getName() {
        return name;
    }

    
    public void setName(String value) {
        this.name = value;
    }

}
