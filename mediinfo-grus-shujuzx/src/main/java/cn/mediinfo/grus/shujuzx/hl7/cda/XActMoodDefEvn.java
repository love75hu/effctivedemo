//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActMoodDefEvn的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="x_ActMoodDefEvn">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="DEF"/>
 *     <enumeration value="EVN"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActMoodDefEvn")
@XmlEnum
public enum XActMoodDefEvn {

    DEF,
    EVN;

    public String value() {
        return name();
    }

    public static XActMoodDefEvn fromValue(String v) {
        return valueOf(v);
    }

}
