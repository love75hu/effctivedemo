//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlMixed;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             A name for a person, organization, place or thing. A
 *             sequence of name parts, such as given name or family
 *             name, prefix, suffix, etc. Examples for entity name
 *             values are "Jim Bob Walton, Jr.", "Health Level Seven,
 *             Inc.", "Lake Tahoe", etc. An entity name may be as simple
 *             as a character string or may consist of several entity name
 *             parts, such as, "Jim", "Bob", "Walton", and "Jr.", "Health
 *             Level Seven" and "Inc.", "Lake" and "Tahoe".
 *          
 * 
 * <p>EN complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="EN">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ANY">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="delimiter" type="{urn:hl7-org:v3}en.delimiter"/>
 *           <element name="family" type="{urn:hl7-org:v3}en.family"/>
 *           <element name="given" type="{urn:hl7-org:v3}en.given"/>
 *           <element name="prefix" type="{urn:hl7-org:v3}en.prefix"/>
 *           <element name="suffix" type="{urn:hl7-org:v3}en.suffix"/>
 *         </choice>
 *         <element name="validTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="use" type="{urn:hl7-org:v3}set_EntityNameUse" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EN", propOrder = {
    "content"
})
@XmlSeeAlso({
    PN.class,
    ON.class,
    TN.class
})
public class EN {

    @XmlElementRefs({
        @XmlElementRef(name = "delimiter", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "family", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "given", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "prefix", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "suffix", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "validTime", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    protected List<Serializable> content;
    @XmlAttribute(name = "use")
    protected List<String> use;

    /**
     * 
     *             A name for a person, organization, place or thing. A
     *             sequence of name parts, such as given name or family
     *             name, prefix, suffix, etc. Examples for entity name
     *             values are "Jim Bob Walton, Jr.", "Health Level Seven,
     *             Inc.", "Lake Tahoe", etc. An entity name may be as simple
     *             as a character string or may consist of several entity name
     *             parts, such as, "Jim", "Bob", "Walton", and "Jr.", "Health
     *             Level Seven" and "Inc.", "Lake" and "Tahoe".
     *          Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link EnDelimiter }{@code >}
     * {@link JAXBElement }{@code <}{@link EnFamily }{@code >}
     * {@link JAXBElement }{@code <}{@link EnGiven }{@code >}
     * {@link JAXBElement }{@code <}{@link EnPrefix }{@code >}
     * {@link JAXBElement }{@code <}{@link EnSuffix }{@code >}
     * {@link JAXBElement }{@code <}{@link IVLTS }{@code >}
     * {@link String }
     * 
     * 
     * @return
     *     The value of the content property.
     */
    public List<Serializable> getContent() {
        if (content == null) {
            content = new ArrayList<>();
        }
        return this.content;
    }

    /**
     * Gets the value of the use property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the use property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the use property.
     */
    public List<String> getUse() {
        if (use == null) {
            use = new ArrayList<>();
        }
        return this.use;
    }

}
