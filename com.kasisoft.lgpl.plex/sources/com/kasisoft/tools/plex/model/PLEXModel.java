







package com.kasisoft.tools.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "general",
    "sheet"
})
@XmlRootElement(name = "plex")
public class PLEXModel {

    @XmlElement(required = true)
    protected PLEXGeneral general;
    @XmlElement(required = true)
    protected List<PLEXSheetDescription> sheet;

    
    public PLEXGeneral getGeneral() {
        return general;
    }

    
    public void setGeneral(PLEXGeneral value) {
        this.general = value;
    }

    
    public List<PLEXSheetDescription> getSheet() {
        if (sheet == null) {
            sheet = new ArrayList<PLEXSheetDescription>();
        }
        return this.sheet;
    }

}
