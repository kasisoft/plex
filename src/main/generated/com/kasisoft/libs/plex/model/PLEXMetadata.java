
package com.kasisoft.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tMetadata complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tMetadata"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="metadetect" type="{}tApiCall" minOccurs="0"/&gt;
 *         &lt;element name="property" type="{}tProperty" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tMetadata", propOrder = {
    "metadetect",
    "property"
})
@java.lang.SuppressWarnings("all")
public class PLEXMetadata {

    protected PLEXApiCall metadetect;
    protected List<PLEXProperty> property;

    /**
     * Ruft den Wert der metadetect-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXApiCall }
     *     
     */
    public PLEXApiCall getMetadetect() {
        return metadetect;
    }

    /**
     * Legt den Wert der metadetect-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXApiCall }
     *     
     */
    public void setMetadetect(PLEXApiCall value) {
        this.metadetect = value;
    }

    /**
     * Gets the value of the property property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLEXProperty }
     * 
     * 
     */
    public List<PLEXProperty> getProperty() {
        if (property == null) {
            property = new ArrayList<PLEXProperty>();
        }
        return this.property;
    }

}
