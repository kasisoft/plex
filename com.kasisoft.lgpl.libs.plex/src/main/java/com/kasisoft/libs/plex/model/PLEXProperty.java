







package com.kasisoft.libs.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tProperty")
public class PLEXProperty {

    @XmlAttribute
    protected String key;
    @XmlAttribute
    protected String value;

    
    public String getKey() {
        return key;
    }

    
    public void setKey(String value) {
        this.key = value;
    }

    
    public String getValue() {
        return value;
    }

    
    public void setValue(String value) {
        this.value = value;
    }

}
