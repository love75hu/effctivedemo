
package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActClinicalDocument�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActClinicalDocument">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="DOCPUBH"/>
 *     <enumeration value="DOCCLIN"/>
 *     <enumeration value="CDALVLONE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActClinicalDocument")
@XmlEnum
public enum ActClinicalDocument {

    DOCPUBH,
    DOCCLIN,
    CDALVLONE;

    public String value() {
        return name();
    }

    public static ActClinicalDocument fromValue(String v) {
        return valueOf(v);
    }

}
