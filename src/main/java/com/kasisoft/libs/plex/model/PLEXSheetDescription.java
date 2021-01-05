
package com.kasisoft.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tSheetDescription complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tSheetDescription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="firstrowdetect" type="{}tApiCall" minOccurs="0"/&gt;
 *         &lt;element name="metadata" type="{}tMetadata" minOccurs="0"/&gt;
 *         &lt;element name="column" type="{}tColumn" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="namepattern" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="firstrow" type="{}sRow" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tSheetDescription", propOrder = {
    "firstrowdetect",
    "metadata",
    "column"
})
@java.lang.SuppressWarnings("all")
public class PLEXSheetDescription {

    protected PLEXApiCall firstrowdetect;
    protected PLEXMetadata metadata;
    protected List<PLEXColumnDescription> column;
    @XmlAttribute(name = "namepattern")
    protected String namepattern;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "firstrow")
    protected Integer firstrow;

    /**
     * Ruft den Wert der firstrowdetect-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXApiCall }
     *     
     */
    public PLEXApiCall getFirstrowdetect() {
        return firstrowdetect;
    }

    /**
     * Legt den Wert der firstrowdetect-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXApiCall }
     *     
     */
    public void setFirstrowdetect(PLEXApiCall value) {
        this.firstrowdetect = value;
    }

    /**
     * Ruft den Wert der metadata-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXMetadata }
     *     
     */
    public PLEXMetadata getMetadata() {
        return metadata;
    }

    /**
     * Legt den Wert der metadata-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXMetadata }
     *     
     */
    public void setMetadata(PLEXMetadata value) {
        this.metadata = value;
    }

    /**
     * Gets the value of the column property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the column property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLEXColumnDescription }
     * 
     * 
     */
    public List<PLEXColumnDescription> getColumn() {
        if (column == null) {
            column = new ArrayList<PLEXColumnDescription>();
        }
        return this.column;
    }

    /**
     * Ruft den Wert der namepattern-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamepattern() {
        return namepattern;
    }

    /**
     * Legt den Wert der namepattern-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamepattern(String value) {
        this.namepattern = value;
    }

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der firstrow-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFirstrow() {
        return firstrow;
    }

    /**
     * Legt den Wert der firstrow-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFirstrow(Integer value) {
        this.firstrow = value;
    }

}
