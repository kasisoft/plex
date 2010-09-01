







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInterface", propOrder = {
    "injectors"
})
public class PLEXInterface {

    @XmlElements({
        @XmlElement(name = "integer", type = PLEXInteger.class),
        @XmlElement(name = "list", type = PLEXStringList.class),
        @XmlElement(name = "string", type = PLEXString.class),
        @XmlElement(name = "double", type = PLEXDouble.class),
        @XmlElement(name = "boolean", type = PLEXBoolean.class)
    })
    protected List<PLEXInjector> injectors;
    @XmlAttribute
    protected PLEXApiType api;
    @XmlAttribute
    protected String id;
    @XmlAttribute
    protected String classname;

    
    public List<PLEXInjector> getInjectors() {
        if (injectors == null) {
            injectors = new ArrayList<PLEXInjector>();
        }
        return this.injectors;
    }

    
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
