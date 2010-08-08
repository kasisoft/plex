







package com.kasisoft.tools.plex.model;

import javax.xml.bind.annotation.XmlRegistry;



@XmlRegistry
public class ObjectFactory {


    
    public ObjectFactory() {
    }

    
    public PLEXProperty createPLEXProperty() {
        return new PLEXProperty();
    }

    
    public PLEXSheetDescription createPLEXSheetDescription() {
        return new PLEXSheetDescription();
    }

    
    public PLEXModel createPLEXModel() {
        return new PLEXModel();
    }

    
    public PLEXMetadata createPLEXMetadata() {
        return new PLEXMetadata();
    }

    
    public PLEXColumnGroup createPLEXColumnGroup() {
        return new PLEXColumnGroup();
    }

    
    public PLEXGeneral createPLEXGeneral() {
        return new PLEXGeneral();
    }

    
    public PLEXInterface createPLEXInterface() {
        return new PLEXInterface();
    }

    
    public PLEXApiCall createPLEXApiCall() {
        return new PLEXApiCall();
    }

    
    public PLEXColumnGroupMember createPLEXColumnGroupMember() {
        return new PLEXColumnGroupMember();
    }

    
    public PLEXColumnDescription createPLEXColumnDescription() {
        return new PLEXColumnDescription();
    }

}
