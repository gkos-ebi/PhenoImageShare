
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Organism complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Organism">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxon" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sex" type="{http://www.example.org/phisSchema}Sex" minOccurs="0"/>
 *         &lt;element name="ncbi_taxon_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stage" type="{http://www.example.org/phisSchema}OntologyTerm" minOccurs="0"/>
 *         &lt;element name="background_strain" type="{http://www.example.org/phisSchema}StringArray" minOccurs="0"/>
 *         &lt;element name="organism_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.example.org/phisSchema}Group" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Organism", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class Organism {

    protected String age;
    @XmlElement(required = true)
    protected String taxon;
    @XmlSchemaType(name = "string")
    protected Sex sex;
    @XmlElement(name = "ncbi_taxon_id")
    protected String ncbiTaxonId;
    protected OntologyTerm stage;
    @XmlElement(name = "background_strain")
    protected StringArray backgroundStrain;
    @XmlElement(name = "organism_id")
    protected String organismId;
    protected Group group;

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAge(String value) {
        this.age = value;
    }

    /**
     * Gets the value of the taxon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxon() {
        return taxon;
    }

    /**
     * Sets the value of the taxon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxon(String value) {
        this.taxon = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link Sex }
     *     
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sex }
     *     
     */
    public void setSex(Sex value) {
        this.sex = value;
    }

    /**
     * Gets the value of the ncbiTaxonId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcbiTaxonId() {
        return ncbiTaxonId;
    }

    /**
     * Sets the value of the ncbiTaxonId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcbiTaxonId(String value) {
        this.ncbiTaxonId = value;
    }

    /**
     * Gets the value of the stage property.
     * 
     * @return
     *     possible object is
     *     {@link OntologyTerm }
     *     
     */
    public OntologyTerm getStage() {
        return stage;
    }

    /**
     * Sets the value of the stage property.
     * 
     * @param value
     *     allowed object is
     *     {@link OntologyTerm }
     *     
     */
    public void setStage(OntologyTerm value) {
        this.stage = value;
    }

    /**
     * Gets the value of the backgroundStrain property.
     * 
     * @return
     *     possible object is
     *     {@link StringArray }
     *     
     */
    public StringArray getBackgroundStrain() {
        return backgroundStrain;
    }

    /**
     * Sets the value of the backgroundStrain property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringArray }
     *     
     */
    public void setBackgroundStrain(StringArray value) {
        this.backgroundStrain = value;
    }

    /**
     * Gets the value of the organismId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganismId() {
        return organismId;
    }

    /**
     * Sets the value of the organismId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganismId(String value) {
        this.organismId = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link Group }
     *     
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link Group }
     *     
     */
    public void setGroup(Group value) {
        this.group = value;
    }

}
