
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Roi complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Roi">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="associated_image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="associated_channel" type="{http://www.example.org/phisSchema}StringArray" minOccurs="0"/>
 *         &lt;element name="coordinates" type="{http://www.example.org/phisSchema}Coordinates"/>
 *         &lt;element name="user_owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user_group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creation_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="edit_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="depicted_anatomical_structure" type="{http://www.example.org/phisSchema}ExpressionAnnotationArray" minOccurs="0"/>
 *         &lt;element name="is_expression_pattern" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="abnormality_in_anatomical_structure" type="{http://www.example.org/phisSchema}AnnotationArray" minOccurs="0"/>
 *         &lt;element name="phenotype_annotations" type="{http://www.example.org/phisSchema}AnnotationArray" minOccurs="0"/>
 *         &lt;element name="observations" type="{http://www.example.org/phisSchema}StringArray" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Roi", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Roi {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "associated_image", required = true)
    protected String associatedImage;
    @XmlElement(name = "associated_channel")
    protected StringArray associatedChannel;
    @XmlElement(required = true)
    protected Coordinates coordinates;
    @XmlElement(name = "user_owner")
    protected String userOwner;
    @XmlElement(name = "user_group")
    protected String userGroup;
    @XmlElement(name = "creation_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "edit_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar editDate;
    @XmlElement(name = "depicted_anatomical_structure")
    protected ExpressionAnnotationArray depictedAnatomicalStructure;
    @XmlElement(name = "is_expression_pattern")
    protected Boolean isExpressionPattern;
    @XmlElement(name = "abnormality_in_anatomical_structure")
    protected AnnotationArray abnormalityInAnatomicalStructure;
    @XmlElement(name = "phenotype_annotations")
    protected AnnotationArray phenotypeAnnotations;
    protected StringArray observations;

    /**
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
     * Gets the value of the associatedImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedImage() {
        return associatedImage;
    }

    /**
     * Sets the value of the associatedImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedImage(String value) {
        this.associatedImage = value;
    }

    /**
     * Gets the value of the associatedChannel property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getAssociatedChannel() {
        return associatedChannel;
    }

    /**
     * Sets the value of the associatedChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setAssociatedChannel(StringArray value) {
        this.associatedChannel = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link Coordinates }
     *     
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link Coordinates }
     *     
     */
    public void setCoordinates(Coordinates value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the userOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserOwner() {
        return userOwner;
    }

    /**
     * Sets the value of the userOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserOwner(String value) {
        this.userOwner = value;
    }

    /**
     * Gets the value of the userGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserGroup() {
        return userGroup;
    }

    /**
     * Sets the value of the userGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserGroup(String value) {
        this.userGroup = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the editDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEditDate() {
        return editDate;
    }

    /**
     * Sets the value of the editDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEditDate(XMLGregorianCalendar value) {
        this.editDate = value;
    }

    /**
     * Gets the value of the depictedAnatomicalStructure property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressionAnnotationArray }
     *     
     */
    public ExpressionAnnotationArray getDepictedAnatomicalStructure() {
        return depictedAnatomicalStructure;
    }

    /**
     * Sets the value of the depictedAnatomicalStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressionAnnotationArray }
     *     
     */
    public void setDepictedAnatomicalStructure(ExpressionAnnotationArray value) {
        this.depictedAnatomicalStructure = value;
    }

    /**
     * Gets the value of the isExpressionPattern property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsExpressionPattern() {
        return isExpressionPattern;
    }

    /**
     * Sets the value of the isExpressionPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsExpressionPattern(Boolean value) {
        this.isExpressionPattern = value;
    }

    /**
     * Gets the value of the abnormalityInAnatomicalStructure property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationArray }
     *     
     */
    public AnnotationArray getAbnormalityInAnatomicalStructure() {
        return abnormalityInAnatomicalStructure;
    }

    /**
     * Sets the value of the abnormalityInAnatomicalStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationArray }
     *     
     */
    public void setAbnormalityInAnatomicalStructure(AnnotationArray value) {
        this.abnormalityInAnatomicalStructure = value;
    }

    /**
     * Gets the value of the phenotypeAnnotations property.
     * 
     * @return
     *     possible object is
     *     {@link AnnotationArray }
     *     
     */
    public AnnotationArray getPhenotypeAnnotations() {
        return phenotypeAnnotations;
    }

    /**
     * Sets the value of the phenotypeAnnotations property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnnotationArray }
     *     
     */
    public void setPhenotypeAnnotations(AnnotationArray value) {
        this.phenotypeAnnotations = value;
    }

    /**
     * Gets the value of the observations property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getObservations() {
        return observations;
    }

    /**
     * Sets the value of the observations property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setObservations(StringArray value) {
        this.observations = value;
    }

}
