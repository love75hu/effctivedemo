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


/**
 * 
 *             Fractional numbers. Typically used whenever quantities
 *             are measured, estimated, or computed from other real
 *             numbers.  The typical representation is decimal, where
 *             the number of significant decimal digits is known as the
 *             precision. Real numbers are needed beyond integers
 *             whenever quantities of the real world are measured,
 *             estimated, or computed from other real numbers. The term
 *             "Real number" in this specification is used to mean
 *             that fractional values are covered without necessarily
 *             implying the full set of the mathematical real numbers.
 *          
 * 
 * <p>REAL complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="REAL">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}QTY">
 *       <attribute name="value" type="{urn:hl7-org:v3}real" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "REAL")
@XmlSeeAlso({
    SXCMREAL.class,
    IVXBREAL.class
})
public class REAL
    extends QTY
{

    @XmlAttribute(name = "value")
    protected String value;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}