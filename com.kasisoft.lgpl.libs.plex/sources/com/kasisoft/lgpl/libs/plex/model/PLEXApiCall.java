







package com.kasisoft.lgpl.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tApiCall", propOrder = {
    "arg"
})
public class PLEXApiCall {

    protected List<String> arg;
    @XmlAttribute
    protected String refid;

    
    public List<String> getArg() {
        if (arg == null) {
            arg = new ArrayList<String>();
        }
        return this.arg;
    }

    
    public String getRefid() {
        return refid;
    }

    
    public void setRefid(String value) {
        this.refid = value;
    }

}
