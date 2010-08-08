







package com.kasisoft.lgpl.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "columngrouptype", propOrder = {
    "columndetect",
    "countdetect",
    "member"
})
public class PLEXColumnGroup {

    protected PLEXApiCall columndetect;
    protected PLEXApiCall countdetect;
    @XmlElement(required = true)
    protected List<PLEXColumnGroupMember> member;
    @XmlAttribute
    protected Integer column;
    @XmlAttribute
    protected Integer count;

    
    public PLEXApiCall getColumndetect() {
        return columndetect;
    }

    
    public void setColumndetect(PLEXApiCall value) {
        this.columndetect = value;
    }

    
    public PLEXApiCall getCountdetect() {
        return countdetect;
    }

    
    public void setCountdetect(PLEXApiCall value) {
        this.countdetect = value;
    }

    
    public List<PLEXColumnGroupMember> getMember() {
        if (member == null) {
            member = new ArrayList<PLEXColumnGroupMember>();
        }
        return this.member;
    }

    
    public int getColumn() {
        if (column == null) {
            return -1;
        } else {
            return column;
        }
    }

    
    public void setColumn(Integer value) {
        this.column = value;
    }

    
    public int getCount() {
        if (count == null) {
            return -1;
        } else {
            return count;
        }
    }

    
    public void setCount(Integer value) {
        this.count = value;
    }

}
