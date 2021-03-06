
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenomicLocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenomicLocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="start_pos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="end_pos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="chromosone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strand" type="{http://www.example.org/phisSchema}Strand" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenomicLocation", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class GenomicLocation {

    @XmlElement(name = "start_pos")
    protected Long startPos;
    @XmlElement(name = "end_pos")
    protected Long endPos;
    protected String chromosone;
    protected String strand;

    /**
     * Gets the value of the startPos property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStartPos() {
        return startPos;
    }

    /**
     * Sets the value of the startPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStartPos(Long value) {
        this.startPos = value;
    }

    /**
     * Gets the value of the endPos property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEndPos() {
        return endPos;
    }

    /**
     * Sets the value of the endPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEndPos(Long value) {
        this.endPos = value;
    }

    /**
     * Gets the value of the chromosone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChromosone() {
        return chromosone;
    }

    /**
     * Sets the value of the chromosone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChromosone(String value) {
        this.chromosone = value;
    }

    /**
     * Gets the value of the strand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrand() {
        return strand;
    }

    /**
     * Sets the value of the strand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrand(String value) {
        this.strand = value;
    }

}
