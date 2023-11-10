//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActClassPublicHealthCase�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActClassPublicHealthCase">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="CASE"/>
 *     <enumeration value="OUTB"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActClassPublicHealthCase")
@XmlEnum
public enum ActClassPublicHealthCase {

    CASE,
    OUTB;

    public String value() {
        return name();
    }

    public static ActClassPublicHealthCase fromValue(String v) {
        return valueOf(v);
    }

}
