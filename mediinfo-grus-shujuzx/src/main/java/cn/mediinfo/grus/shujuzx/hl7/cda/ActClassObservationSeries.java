//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActClassObservationSeries的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="ActClassObservationSeries">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="OBSSER"/>
 *     <enumeration value="OBSCOR"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActClassObservationSeries")
@XmlEnum
public enum ActClassObservationSeries {

    OBSSER,
    OBSCOR;

    public String value() {
        return name();
    }

    public static ActClassObservationSeries fromValue(String v) {
        return valueOf(v);
    }

}
