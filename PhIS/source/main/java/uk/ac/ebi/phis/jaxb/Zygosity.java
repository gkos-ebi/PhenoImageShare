//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.07 at 03:58:45 PM BST 
//


package uk.ac.ebi.phis.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Zygosity.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Zygosity">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="homozygous"/>
 *     &lt;enumeration value="heterozygous"/>
 *     &lt;enumeration value="hemizygous"/>
 *     &lt;enumeration value="wild type"/>
 *     &lt;enumeration value="unspecified"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Zygosity")
@XmlEnum
public enum Zygosity {

    @XmlEnumValue("homozygous")
    HOMOZYGOUS("homozygous"),
    @XmlEnumValue("heterozygous")
    HETEROZYGOUS("heterozygous"),
    @XmlEnumValue("hemizygous")
    HEMIZYGOUS("hemizygous"),
    @XmlEnumValue("wild type")
    WILD_TYPE("wild type"),
    @XmlEnumValue("unspecified")
    UNSPECIFIED("unspecified");
    private final String value;

    Zygosity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Zygosity fromValue(String v) {
        for (Zygosity c: Zygosity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
