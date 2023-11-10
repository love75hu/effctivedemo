//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActMoodIntent的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="ActMoodIntent">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="INT"/>
 *     <enumeration value="APT"/>
 *     <enumeration value="ARQ"/>
 *     <enumeration value="PRMS"/>
 *     <enumeration value="PRP"/>
 *     <enumeration value="RQO"/>
 *     <enumeration value="SLOT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActMoodIntent")
@XmlEnum
public enum ActMoodIntent {

    INT,
    APT,
    ARQ,
    PRMS,
    PRP,
    RQO,
    SLOT;

    public String value() {
        return name();
    }

    public static ActMoodIntent fromValue(String v) {
        return valueOf(v);
    }

}
