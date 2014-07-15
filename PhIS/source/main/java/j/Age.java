//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.15 at 03:43:03 PM BST 
//


package j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Age complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Age">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="age_since_birth" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="embryonic_age" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Age", propOrder = {
    "ageSinceBirth",
    "embryonicAge"
})
public class Age {

    @XmlElement(name = "age_since_birth")
    protected Float ageSinceBirth;
    @XmlElement(name = "embryonic_age")
    protected Float embryonicAge;

    /**
     * Gets the value of the ageSinceBirth property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAgeSinceBirth() {
        return ageSinceBirth;
    }

    /**
     * Sets the value of the ageSinceBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAgeSinceBirth(Float value) {
        this.ageSinceBirth = value;
    }

    /**
     * Gets the value of the embryonicAge property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getEmbryonicAge() {
        return embryonicAge;
    }

    /**
     * Sets the value of the embryonicAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setEmbryonicAge(Float value) {
        this.embryonicAge = value;
    }

}
