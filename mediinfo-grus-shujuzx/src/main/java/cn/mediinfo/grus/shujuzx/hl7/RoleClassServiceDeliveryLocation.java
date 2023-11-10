//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RoleClassServiceDeliveryLocation�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="RoleClassServiceDeliveryLocation">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="SDLOC"/>
 *     <enumeration value="DSDLOC"/>
 *     <enumeration value="ISDLOC"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "RoleClassServiceDeliveryLocation")
@XmlEnum
public enum RoleClassServiceDeliveryLocation {

    SDLOC,
    DSDLOC,
    ISDLOC;

    public String value() {
        return name();
    }

    public static RoleClassServiceDeliveryLocation fromValue(String v) {
        return valueOf(v);
    }

}
