//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>NameRepresentationUse�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="NameRepresentationUse">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="ABC"/>
 *     <enumeration value="IDE"/>
 *     <enumeration value="SYL"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "NameRepresentationUse")
@XmlEnum
public enum NameRepresentationUse {

    ABC,
    IDE,
    SYL;

    public String value() {
        return name();
    }

    public static NameRepresentationUse fromValue(String v) {
        return valueOf(v);
    }

}
