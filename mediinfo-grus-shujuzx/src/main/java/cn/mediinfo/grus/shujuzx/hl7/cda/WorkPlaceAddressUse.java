//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>WorkPlaceAddressUse的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="WorkPlaceAddressUse">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="WP"/>
 *     <enumeration value="DIR"/>
 *     <enumeration value="PUB"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "WorkPlaceAddressUse")
@XmlEnum
public enum WorkPlaceAddressUse {

    WP,
    DIR,
    PUB;

    public String value() {
        return name();
    }

    public static WorkPlaceAddressUse fromValue(String v) {
        return valueOf(v);
    }

}