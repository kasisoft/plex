







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tColumn", propOrder = {
    "columndetect",
    "transformer"
})
public class PLEXColumnDescription {

    protected PLEXApiCall columndetect;
    protected List<PLEXApiCall> transformer;
    @XmlAttribute
    protected String title;
    @XmlAttribute
    protected String column;

    
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

    
    public String getColumn() {
        return column;
    }

    
    public void setColumn(String value) {
        this.column = value;
    }

}
