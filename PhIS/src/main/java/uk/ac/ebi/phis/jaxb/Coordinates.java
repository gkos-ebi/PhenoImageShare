
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 		 	in % relative to the image dimensions
 * 		  
 * 
 * <p>Java class for Coordinates complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Coordinates">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="z_coordinates" type="{http://www.example.org/phisSchema}PercentArray" minOccurs="0"/>
 *         &lt;element name="y_coordinates" type="{http://www.example.org/phisSchema}PercentArray"/>
 *         &lt;element name="x_coordinates" type="{http://www.example.org/phisSchema}PercentArray"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Coordinates", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Coordinates {

    @XmlElement(name = "z_coordinates")
    protected PercentArray zCoordinates;
    @XmlElement(name = "y_coordinates", required = true)
    protected PercentArray yCoordinates;
    @XmlElement(name = "x_coordinates", required = true)
    protected PercentArray xCoordinates;

    /**
     * Gets the value of the zCoordinates property.
     * 
     * @return
     *     possible object is
     *     {@link PercentArray }
     *     
     */
    public PercentArray getZCoordinates() {
        return zCoordinates;
    }

    /**
     * Sets the value of the zCoordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link PercentArray }
     *     
     */
    public void setZCoordinates(PercentArray value) {
        this.zCoordinates = value;
    }

    /**
     * Gets the value of the yCoordinates property.
     * 
     * @return
     *     possible object is
     *     {@link PercentArray }
     *     
     */
    public PercentArray getYCoordinates() {
        return yCoordinates;
    }

    /**
     * Sets the value of the yCoordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link PercentArray }
     *     
     */
    public void setYCoordinates(PercentArray value) {
        this.yCoordinates = value;
    }

    /**
     * Gets the value of the xCoordinates property.
     * 
     * @return
     *     possible object is
     *     {@link PercentArray }
     *     
     */
    public PercentArray getXCoordinates() {
        return xCoordinates;
    }

    /**
     * Sets the value of the xCoordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link PercentArray }
     *     
     */
    public void setXCoordinates(PercentArray value) {
        this.xCoordinates = value;
    }

}
