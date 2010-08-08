







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "columntype", propOrder = {
    "columndetect",
    "transformer"
})
public class PLEXColumnDescription {

    protected PLEXApiCall columndetect;
    protected List<PLEXApiCall> transformer;
    @XmlAttribute
    protected String title;
    @XmlAttribute
    protected Integer column;

    
    public PLEXApiCall getColumndetect() {
        return columndetect;
    }

    
    public void setColumndetect(PLEXApiCall value) {
        this.columndetect = value;
    }

    
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

    
    public int getColumn() {
        if (column == null) {
            return -1;
        } else {
            return column.intValue();
        }
    }

    
    public void setColumn(Integer value) {
        this.column = value;
    }

}
