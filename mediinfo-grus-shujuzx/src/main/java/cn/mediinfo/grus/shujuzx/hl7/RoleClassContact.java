//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RoleClassContact�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="RoleClassContact">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="CON"/>
 *     <enumeration value="ECON"/>
 *     <enumeration value="NOK"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "RoleClassContact")
@XmlEnum
public enum RoleClassContact {

    CON,
    ECON,
    NOK;

    public String value() {
        return name();
    }

    public static RoleClassContact fromValue(String v) {
        return valueOf(v);
    }

}
