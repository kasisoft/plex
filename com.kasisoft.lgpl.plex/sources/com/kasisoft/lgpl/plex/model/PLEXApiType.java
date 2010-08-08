







package com.kasisoft.lgpl.plex.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;



@XmlEnum
public enum PLEXApiType {

    @XmlEnumValue("column")
    COLUMN("column"),
    @XmlEnumValue("row")
    ROW("row"),
    @XmlEnumValue("count")
    COUNT("count"),
    @XmlEnumValue("transform")
    TRANSFORM("transform"),
    @XmlEnumValue("metadata")
    METADATA("metadata");
    private final String value;

    PLEXApiType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PLEXApiType fromValue(String v) {
        for (PLEXApiType c: PLEXApiType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
