//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *             Data that is primarily intended for human interpretation
 *             or for further machine processing is outside the scope of
 *             HL7. This includes unformatted or formatted written language,
 *             multimedia data, or structured information as defined by a
 *             different standard (e.g., XML-signatures.)  Instead of the
 *             data itself, an ED may contain 
 *             only a reference (see TEL.) Note
 *             that the ST data type is a
 *             specialization of the ED data type
 *             when the ED media type is text/plain.
 *          
 * 
 * <p>ED complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="ED">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}BIN">
 *       <sequence>
 *         <element name="reference" type="{urn:hl7-org:v3}TEL" minOccurs="0"/>
 *         <element name="thumbnail" type="{urn:hl7-org:v3}thumbnail" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="mediaType" type="{urn:hl7-org:v3}cs" default="text/plain" />
 *       <attribute name="language" type="{urn:hl7-org:v3}cs" />
 *       <attribute name="compression" type="{urn:hl7-org:v3}CompressionAlgorithm" />
 *       <attribute name="integrityCheck" type="{urn:hl7-org:v3}bin" />
 *       <attribute name="integrityCheckAlgorithm" type="{urn:hl7-org:v3}IntegrityCheckAlgorithm" default="SHA-1" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ED", propOrder = {
    "reference",
    "thumbnail"
})
@XmlSeeAlso({
    Thumbnail.class,
    ST.class
})
public class ED
    extends BIN
{

    protected TEL reference;
    protected Thumbnail thumbnail;
    @XmlAttribute(name = "mediaType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String mediaType;
    @XmlAttribute(name = "language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String language;
    @XmlAttribute(name = "compression")
    protected CompressionAlgorithm compression;
    @XmlAttribute(name = "integrityCheck")
    protected byte[] integrityCheck;
    @XmlAttribute(name = "integrityCheckAlgorithm")
    protected IntegrityCheckAlgorithm integrityCheckAlgorithm;

    /**
     * 获取reference属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TEL }
     *     
     */
    public TEL getReference() {
        return reference;
    }

    /**
     * 设置reference属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TEL }
     *     
     */
    public void setReference(TEL value) {
        this.reference = value;
    }

    /**
     * 获取thumbnail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Thumbnail }
     *     
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置thumbnail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Thumbnail }
     *     
     */
    public void setThumbnail(Thumbnail value) {
        this.thumbnail = value;
    }

    /**
     * 获取mediaType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaType() {
        if (mediaType == null) {
            return "text/plain";
        } else {
            return mediaType;
        }
    }

    /**
     * 设置mediaType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaType(String value) {
        this.mediaType = value;
    }

    /**
     * 获取language属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置language属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * 获取compression属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CompressionAlgorithm }
     *     
     */
    public CompressionAlgorithm getCompression() {
        return compression;
    }

    /**
     * 设置compression属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CompressionAlgorithm }
     *     
     */
    public void setCompression(CompressionAlgorithm value) {
        this.compression = value;
    }

    /**
     * 获取integrityCheck属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getIntegrityCheck() {
        return integrityCheck;
    }

    /**
     * 设置integrityCheck属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setIntegrityCheck(byte[] value) {
        this.integrityCheck = value;
    }

    /**
     * 获取integrityCheckAlgorithm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link IntegrityCheckAlgorithm }
     *     
     */
    public IntegrityCheckAlgorithm getIntegrityCheckAlgorithm() {
        if (integrityCheckAlgorithm == null) {
            return IntegrityCheckAlgorithm.SHA_1;
        } else {
            return integrityCheckAlgorithm;
        }
    }

    /**
     * 设置integrityCheckAlgorithm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link IntegrityCheckAlgorithm }
     *     
     */
    public void setIntegrityCheckAlgorithm(IntegrityCheckAlgorithm value) {
        this.integrityCheckAlgorithm = value;
    }

}
