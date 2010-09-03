







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSheetDescription", propOrder = {
    "firstrowdetect",
    "metadata",
    "column"
})
public class PLEXSheetDescription {

    protected PLEXApiCall firstrowdetect;
    protected PLEXMetadata metadata;
    protected List<PLEXColumnDescription> column;
    @XmlAttribute
    protected String namepattern;
    @XmlAttribute
    protected String name;
    @XmlAttribute
    protected Integer firstrow;

    
    public PLEXApiCall getFirstrowdetect() {
        return firstrowdetect;
    }

    
    public void setFirstrowdetect(PLEXApiCall value) {
        this.firstrowdetect = value;
    }

    
    public PLEXMetadata getMetadata() {
        return metadata;
    }

    
    public void setMetadata(PLEXMetadata value) {
        this.metadata = value;
    }

    
    public List<PLEXColumnDescription> getColumn() {
        if (column == null) {
            column = new ArrayList<PLEXColumnDescription>();
        }
        return this.column;
    }

    
    public String getNamepattern() {
        return namepattern;
    }

    
    public void setNamepattern(String value) {
        this.namepattern = value;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String value) {
        this.name = value;
    }

    
    public Integer getFirstrow() {
        return firstrow;
    }

    
    public void setFirstrow(Integer value) {
        this.firstrow = value;
    }

}
