
package com.kasisoft.libs.plex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für tInterface complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="tInterface"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element name="list" type="{}tStringList"/&gt;
 *         &lt;element name="integer" type="{}tInteger"/&gt;
 *         &lt;element name="boolean" type="{}tBoolean"/&gt;
 *         &lt;element name="double" type="{}tDouble"/&gt;
 *         &lt;element name="string" type="{}tString"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="api" type="{}tApi" /&gt;
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="classname" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tInterface", propOrder = {
    "injectors"
})
@java.lang.SuppressWarnings("all")
public class PLEXInterface {

    @XmlElements({
        @XmlElement(name = "list", type = PLEXStringList.class),
        @XmlElement(name = "integer", type = PLEXInteger.class),
        @XmlElement(name = "boolean", type = PLEXBoolean.class),
        @XmlElement(name = "double", type = PLEXDouble.class),
        @XmlElement(name = "string", type = PLEXString.class)
    })
    protected List<PLEXInjector> injectors;
    @XmlAttribute(name = "api")
    protected PLEXApiType api;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "classname")
    protected String classname;

    /**
     * Gets the value of the injectors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the injectors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInjectors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PLEXStringList }
     * {@link PLEXInteger }
     * {@link PLEXBoolean }
     * {@link PLEXDouble }
     * {@link PLEXString }
     * 
     * 
     */
    public List<PLEXInjector> getInjectors() {
        if (injectors == null) {
            injectors = new ArrayList<PLEXInjector>();
        }
        return this.injectors;
    }

    /**
     * Ruft den Wert der api-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link PLEXApiType }
     *     
     */
    public PLEXApiType getApi() {
        return api;
    }

    /**
     * Legt den Wert der api-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link PLEXApiType }
     *     
     */
    public void setApi(PLEXApiType value) {
        this.api = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der classname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Legt den Wert der classname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassname(String value) {
        this.classname = value;
    }

}
