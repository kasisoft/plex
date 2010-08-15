







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tColumnGroupMember", propOrder = {
    "transformer"
})
public class PLEXColumnGroupMember {

    protected List<PLEXApiCall> transformer;
    @XmlAttribute
    protected String title;
    @XmlAttribute(required = true)
    protected int offset;

    
    public List<PLEXApiCall> getTransformer() {
        if (transformer == null) {
            transformer = new ArrayList<PLEXApiCall>();
        }
        return this.transformer;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String value) {
        this.title = value;
    }

    
    public int getOffset() {
        return offset;
    }

    
    public void setOffset(int value) {
        this.offset = value;
    }

}
