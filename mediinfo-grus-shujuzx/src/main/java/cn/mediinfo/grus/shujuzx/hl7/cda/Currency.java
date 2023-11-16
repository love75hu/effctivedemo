//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Currency的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="Currency">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="ARS"/>
 *     <enumeration value="AUD"/>
 *     <enumeration value="BRL"/>
 *     <enumeration value="CAD"/>
 *     <enumeration value="CHF"/>
 *     <enumeration value="CLF"/>
 *     <enumeration value="CNY"/>
 *     <enumeration value="DEM"/>
 *     <enumeration value="ESP"/>
 *     <enumeration value="EUR"/>
 *     <enumeration value="FIM"/>
 *     <enumeration value="FRF"/>
 *     <enumeration value="GBP"/>
 *     <enumeration value="ILS"/>
 *     <enumeration value="INR"/>
 *     <enumeration value="JPY"/>
 *     <enumeration value="KRW"/>
 *     <enumeration value="MXN"/>
 *     <enumeration value="NLG"/>
 *     <enumeration value="NZD"/>
 *     <enumeration value="PHP"/>
 *     <enumeration value="RUR"/>
 *     <enumeration value="THB"/>
 *     <enumeration value="TRL"/>
 *     <enumeration value="TWD"/>
 *     <enumeration value="USD"/>
 *     <enumeration value="ZAR"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "Currency")
@XmlEnum
public enum Currency {

    ARS,
    AUD,
    BRL,
    CAD,
    CHF,
    CLF,
    CNY,
    DEM,
    ESP,
    EUR,
    FIM,
    FRF,
    GBP,
    ILS,
    INR,
    JPY,
    KRW,
    MXN,
    NLG,
    NZD,
    PHP,
    RUR,
    THB,
    TRL,
    TWD,
    USD,
    ZAR;

    public String value() {
        return name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }

}
