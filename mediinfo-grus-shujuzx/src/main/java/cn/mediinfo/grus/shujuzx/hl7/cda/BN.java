//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             The BooleanNonNull type is used where a Boolean cannot
 *             have a null value. A Boolean value can be either
 *             true or false.
 *          
 * 
 * <p>BN complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="BN">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ANYNonNull">
 *       <attribute name="value" type="{urn:hl7-org:v3}bn" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BN")
public class BN
    extends ANYNonNull
{

    @XmlAttribute(name = "value")
    protected Boolean value;

    /**
     * 获取value属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValue() {
        return value;
    }

    /**
     * 设置value属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValue(Boolean value) {
        this.value = value;
    }

}
