
package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GenotypeComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenotypeComponent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="gene_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gene_symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gene_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_symbol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genetic_feature_ensembl_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="genomic_location" type="{http://www.example.org/phisSchema}GenomicLocation" minOccurs="0"/>
 *         &lt;element name="zygosity" type="{http://www.example.org/phisSchema}Zygosity" minOccurs="0"/>
 *         &lt;element name="mutation_type" type="{http://www.example.org/phisSchema}Annotation" minOccurs="0"/>
 *         &lt;element name="genome_assembly" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenotypeComponent", namespace = "http://www.example.org/phisSchema", propOrder = {

})
public class GenotypeComponent {

    @XmlElement(name = "gene_id")
    protected String geneId;
    @XmlElement(name = "gene_symbol")
    protected String geneSymbol;
    @XmlElement(name = "gene_name")
    protected String geneName;
    @XmlElement(name = "genetic_feature_id")
    protected String geneticFeatureId;
    @XmlElement(name = "genetic_feature_symbol")
    protected String geneticFeatureSymbol;
    @XmlElement(name = "genetic_feature_ensembl_id")
    protected String geneticFeatureEnsemblId;
    @XmlElement(name = "genomic_location")
    protected GenomicLocation genomicLocation;
    @XmlSchemaType(name = "string")
    protected Zygosity zygosity;
    @XmlElement(name = "mutation_type")
    protected Annotation mutationType;
    @XmlElement(name = "genome_assembly")
    protected String genomeAssembly;

    /**
     * Gets the value of the geneId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneId() {
        return geneId;
    }

    /**
     * Sets the value of the geneId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneId(String value) {
        this.geneId = value;
    }

    /**
     * Gets the value of the geneSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneSymbol() {
        return geneSymbol;
    }

    /**
     * Sets the value of the geneSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneSymbol(String value) {
        this.geneSymbol = value;
    }

    /**
     * Gets the value of the geneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneName() {
        return geneName;
    }

    /**
     * Sets the value of the geneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneName(String value) {
        this.geneName = value;
    }

    /**
     * Gets the value of the geneticFeatureId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureId() {
        return geneticFeatureId;
    }

    /**
     * Sets the value of the geneticFeatureId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureId(String value) {
        this.geneticFeatureId = value;
    }

    /**
     * Gets the value of the geneticFeatureSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureSymbol() {
        return geneticFeatureSymbol;
    }

    /**
     * Sets the value of the geneticFeatureSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureSymbol(String value) {
        this.geneticFeatureSymbol = value;
    }

    /**
     * Gets the value of the geneticFeatureEnsemblId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneticFeatureEnsemblId() {
        return geneticFeatureEnsemblId;
    }

    /**
     * Sets the value of the geneticFeatureEnsemblId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneticFeatureEnsemblId(String value) {
        this.geneticFeatureEnsemblId = value;
    }

    /**
     * Gets the value of the genomicLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GenomicLocation }
     *     
     */
    public GenomicLocation getGenomicLocation() {
        return genomicLocation;
    }

    /**
     * Sets the value of the genomicLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenomicLocation }
     *     
     */
    public void setGenomicLocation(GenomicLocation value) {
        this.genomicLocation = value;
    }

    /**
     * Gets the value of the zygosity property.
     * 
     * @return
     *     possible object is
     *     {@link Zygosity }
     *     
     */
    public Zygosity getZygosity() {
        return zygosity;
    }

    /**
     * Sets the value of the zygosity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zygosity }
     *     
     */
    public void setZygosity(Zygosity value) {
        this.zygosity = value;
    }

    /**
     * Gets the value of the mutationType property.
     * 
     * @return
     *     possible object is
     *     {@link Annotation }
     *     
     */
    public Annotation getMutationType() {
        return mutationType;
    }

    /**
     * Sets the value of the mutationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Annotation }
     *     
     */
    public void setMutationType(Annotation value) {
        this.mutationType = value;
    }

    /**
     * Gets the value of the genomeAssembly property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenomeAssembly() {
        return genomeAssembly;
    }

    /**
     * Sets the value of the genomeAssembly property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenomeAssembly(String value) {
        this.genomeAssembly = value;
    }

}
