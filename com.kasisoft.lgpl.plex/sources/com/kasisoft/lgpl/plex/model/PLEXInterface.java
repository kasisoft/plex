







package com.kasisoft.lgpl.plex.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interfacetype")
public class PLEXInterface {

    @XmlAttribute
    protected PLEXApiType api;
    @XmlAttribute
    protected String id;
    @XmlAttribute
    protected String classname;

    
    public PLEXApiType getApi() {
        return api;
    }

    
    public void setApi(PLEXApiType value) {
        this.api = value;
    }

    
    public String getId() {
        return id;
    }

    
    public void setId(String value) {
        this.id = value;
    }

    
    public String getClassname() {
        return classname;
    }

    
    public void setClassname(String value) {
        this.classname = value;
    }

}
