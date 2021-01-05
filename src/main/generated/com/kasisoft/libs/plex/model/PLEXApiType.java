
package com.kasisoft.libs.plex.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tApi.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="tApi"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="column"/&gt;
 *     &lt;enumeration value="row"/&gt;
 *     &lt;enumeration value="transform"/&gt;
 *     &lt;enumeration value="metadata"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tApi")
@XmlEnum
public enum PLEXApiType {

    @XmlEnumValue("column")
    COLUMN("column"),
    @XmlEnumValue("row")
    ROW("row"),
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
