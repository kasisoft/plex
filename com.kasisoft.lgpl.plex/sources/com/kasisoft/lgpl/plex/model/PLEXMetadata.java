







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadatatype", propOrder = {
    "metadetect",
    "property"
})
public class PLEXMetadata {

    protected PLEXApiCall metadetect;
    protected List<PLEXProperty> property;

    
    public PLEXApiCall getMetadetect() {
        return metadetect;
    }

    
    public void setMetadetect(PLEXApiCall value) {
        this.metadetect = value;
    }

    
    public List<PLEXProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<PLEXProperty>();
        }
        return this.property;
    }

}
