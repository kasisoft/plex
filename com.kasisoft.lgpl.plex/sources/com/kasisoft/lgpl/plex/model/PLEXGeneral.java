







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generaltype", propOrder = {
    "_interface"
})
public class PLEXGeneral {

    @XmlElement(name = "interface")
    protected List<PLEXInterface> _interface;

    
    public List<PLEXInterface> getInterface() {
        if (_interface == null) {
            _interface = new ArrayList<PLEXInterface>();
        }
        return this._interface;
    }

}
