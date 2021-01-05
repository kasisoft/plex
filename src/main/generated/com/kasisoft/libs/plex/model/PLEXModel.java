
package com.kasisoft.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für plex element declaration.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;element name="plex"&gt;
 *   &lt;complexType&gt;
 *     &lt;complexContent&gt;
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *         &lt;sequence&gt;
 *           &lt;element name="general" type="{}tGeneral"/&gt;
 *           &lt;element name="sheet" type="{}tSheetDescription" maxOccurs="unbounded"/&gt;
 *         &lt;/sequence&gt;
 *       &lt;/restriction&gt;
 *     &lt;/complexContent&gt;
 *   &lt;/complexType&gt;
 * &lt;/element&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "general",
    "sheet"
})
@XmlRootElement(name = "plex")
@java.lang.SuppressWarnings("all")
public class PLEXModel {

    @XmlElement(required = true)
    protected PLEXGeneral general;
    @XmlElement(required = true)
    protected List<PLEXSheetDescription> sheet;

    /**
     * Ruft den Wert der general-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXGeneral }
     *     
     */
    public PLEXGeneral getGeneral() {
        return general;
    }

    /**
     * Legt den Wert der general-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXGeneral }
     *     
     */
    public void setGeneral(PLEXGeneral value) {
        this.general = value;
    }

    /**
     * Gets the value of the sheet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sheet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSheet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLEXSheetDescription }
     * 
     * 
     */
    public List<PLEXSheetDescription> getSheet() {
        if (sheet == null) {
            sheet = new ArrayList<PLEXSheetDescription>();
        }
        return this.sheet;
    }

}
