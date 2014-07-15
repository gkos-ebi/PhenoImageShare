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
 * <p>Java class for Annotation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Annotation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="ontology_term" type="{http://www.example.org/phisSchema}OntologyTerm" minOccurs="0"/>
 *         &lt;element name="anatomy_freetext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annotationMode" type="{http://www.example.org/phisSchema}AnnotationMode" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Annotation", propOrder = {

})
public class Annotation {

    @XmlElement(name = "ontology_term")
    protected OntologyTerm ontologyTerm;
    @XmlElement(name = "anatomy_freetext")
    protected String anatomyFreetext;
    protected AnnotationMode annotationMode;

    /**
     * Gets the value of the ontologyTerm property.
     * 
     * @return
     *     possible object is
     *     {@link OntologyTerm }
     *     
     */
    public OntologyTerm getOntologyTerm() {
        return ontologyTerm;
    }

    /**
     * Sets the value of the ontologyTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link OntologyTerm }
     *     
     */
    public void setOntologyTerm(OntologyTerm value) {
        this.ontologyTerm = value;
    }

    /**
     * Gets the value of the anatomyFreetext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnatomyFreetext() {
        return anatomyFreetext;
    }

    /**
     * Sets the value of the anatomyFreetext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnatomyFreetext(String value) {
        this.anatomyFreetext = value;
    }

    /**
     * Gets the value of the annotationMode property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationMode }
     *     
     */
    public AnnotationMode getAnnotationMode() {
        return annotationMode;
    }

    /**
     * Sets the value of the annotationMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationMode }
     *     
     */
    public void setAnnotationMode(AnnotationMode value) {
        this.annotationMode = value;
    }

}
