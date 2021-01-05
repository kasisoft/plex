
package com.kasisoft.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tColumn complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tColumn"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="columndetect" type="{}tApiCall" minOccurs="0"/&gt;
 *         &lt;element name="transformer" type="{}tApiCall" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="column" type="{}sColumn" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tColumn", propOrder = {
    "columndetect",
    "transformer"
})
@java.lang.SuppressWarnings("all")
public class PLEXColumnDescription {

    protected PLEXApiCall columndetect;
    protected List<PLEXApiCall> transformer;
    @XmlAttribute(name = "title")
    protected String title;
    @XmlAttribute(name = "column")
    protected String column;

    /**
     * Ruft den Wert der columndetect-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXApiCall }
     *     
     */
    public PLEXApiCall getColumndetect() {
        return columndetect;
    }

    /**
     * Legt den Wert der columndetect-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXApiCall }
     *     
     */
    public void setColumndetect(PLEXApiCall value) {
        this.columndetect = value;
    }

    /**
     * Gets the value of the transformer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transformer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransformer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLEXApiCall }
     * 
     * 
     */
    public List<PLEXApiCall> getTransformer() {
        if (transformer == null) {
            transformer = new ArrayList<PLEXApiCall>();
        }
        return this.transformer;
    }

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Ruft den Wert der column-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColumn() {
        return column;
    }

    /**
     * Legt den Wert der column-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColumn(String value) {
        this.column = value;
    }

}
