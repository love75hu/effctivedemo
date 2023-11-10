

package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActClassEntry�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActClassEntry">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="ENTRY"/>
 *     <enumeration value="BATTERY"/>
 *     <enumeration value="CLUSTER"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActClassEntry")
@XmlEnum
public enum ActClassEntry {

    ENTRY,
    BATTERY,
    CLUSTER;

    public String value() {
        return name();
    }

    public static ActClassEntry fromValue(String v) {
        return valueOf(v);
    }

}
