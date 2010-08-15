







package com.kasisoft.lgpl.plex.model;

import javax.xml.bind.annotation.XmlRegistry;



@XmlRegistry
public class ObjectFactory {


    
    public ObjectFactory() {
    }

    
    public PLEXGeneral createPLEXGeneral() {
        return new PLEXGeneral();
    }

    
    public PLEXModel createPLEXModel() {
        return new PLEXModel();
    }

    
    public PLEXColumnDescription createPLEXColumnDescription() {
        return new PLEXColumnDescription();
    }

    
    public PLEXInterface createPLEXInterface() {
        return new PLEXInterface();
    }

    
    public PLEXMetadata createPLEXMetadata() {
        return new PLEXMetadata();
    }

    
    public PLEXColumnGroupMember createPLEXColumnGroupMember() {
        return new PLEXColumnGroupMember();
    }

    
    public PLEXProperty createPLEXProperty() {
        return new PLEXProperty();
    }

    
    public PLEXColumnGroup createPLEXColumnGroup() {
        return new PLEXColumnGroup();
    }

    
    public PLEXSheetDescription createPLEXSheetDescription() {
        return new PLEXSheetDescription();
    }

    
    public PLEXApiCall createPLEXApiCall() {
        return new PLEXApiCall();
    }

}
