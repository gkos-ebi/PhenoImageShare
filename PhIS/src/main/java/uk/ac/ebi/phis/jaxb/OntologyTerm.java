
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OntologyTerm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OntologyTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="term_label" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="term_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OntologyTerm", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class OntologyTerm {

    @XmlElement(name = "term_label", required = true)
    protected String termLabel;
    @XmlElement(name = "term_id", required = true)
    protected String termId;

    /**
     * Gets the value of the termLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermLabel() {
        return termLabel;
    }

    /**
     * Sets the value of the termLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermLabel(String value) {
        this.termLabel = value;
    }

    /**
     * Gets the value of the termId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermId() {
        return termId;
    }

    /**
     * Sets the value of the termId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermId(String value) {
        this.termId = value;
    }

}
