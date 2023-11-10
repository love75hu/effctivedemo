//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>PPD_TS complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="PPD_TS">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}TS">
 *       <sequence>
 *         <element name="standardDeviation" type="{urn:hl7-org:v3}PQ" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="distributionType" type="{urn:hl7-org:v3}ProbabilityDistributionType" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PPD_TS", propOrder = {
    "standardDeviation"
})
@XmlSeeAlso({
    IVXBPPDTS.class,
    SXCMPPDTS.class
})
public class PPDTS
    extends TS
{

    protected PQ standardDeviation;
    @XmlAttribute(name = "distributionType")
    protected ProbabilityDistributionType distributionType;

    /**
     * 获取standardDeviation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getStandardDeviation() {
        return standardDeviation;
    }

    /**
     * 设置standardDeviation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setStandardDeviation(PQ value) {
        this.standardDeviation = value;
    }

    /**
     * 获取distributionType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProbabilityDistributionType }
     *     
     */
    public ProbabilityDistributionType getDistributionType() {
        return distributionType;
    }

    /**
     * 设置distributionType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProbabilityDistributionType }
     *     
     */
    public void setDistributionType(ProbabilityDistributionType value) {
        this.distributionType = value;
    }

}
