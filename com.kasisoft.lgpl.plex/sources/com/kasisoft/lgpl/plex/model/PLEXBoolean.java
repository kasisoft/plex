







package com.kasisoft.lgpl.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tBoolean")
public class PLEXBoolean
    extends PLEXInjector
{

    @XmlAttribute(required = true)
    protected boolean value;

    
    public boolean isValue() {
        return value;
    }

    
    public void setValue(boolean value) {
        this.value = value;
    }

}
