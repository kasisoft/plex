







package com.kasisoft.libs.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDouble")
public class PLEXDouble
    extends PLEXInjector
{

    @XmlAttribute(required = true)
    protected double value;

    
    public double getValue() {
        return value;
    }

    
    public void setValue(double value) {
        this.value = value;
    }

}
