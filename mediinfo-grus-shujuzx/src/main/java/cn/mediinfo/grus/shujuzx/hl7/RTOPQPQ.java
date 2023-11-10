//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RTO_PQ_PQ complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="RTO_PQ_PQ">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}QTY">
 *       <sequence>
 *         <element name="numerator" type="{urn:hl7-org:v3}PQ"/>
 *         <element name="denominator" type="{urn:hl7-org:v3}PQ"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTO_PQ_PQ", propOrder = {
    "numerator",
    "denominator"
})
public class RTOPQPQ
    extends QTY
{

    @XmlElement(required = true)
    protected PQ numerator;
    @XmlElement(required = true)
    protected PQ denominator;

    /**
     * 获取numerator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getNumerator() {
        return numerator;
    }

    /**
     * 设置numerator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setNumerator(PQ value) {
        this.numerator = value;
    }

    /**
     * 获取denominator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getDenominator() {
        return denominator;
    }

    /**
     * 设置denominator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setDenominator(PQ value) {
        this.denominator = value;
    }

}
