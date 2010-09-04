







package com.kasisoft.lgpl.libs.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInteger")
public class PLEXInteger
    extends PLEXInjector
{

    @XmlAttribute(required = true)
    protected int value;

    
    public int getValue() {
        return value;
    }

    
    public void setValue(int value) {
        this.value = value;
    }

}
