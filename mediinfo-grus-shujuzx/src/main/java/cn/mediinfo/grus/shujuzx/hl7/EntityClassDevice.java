//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>EntityClassDevice�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="EntityClassDevice">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="DEV"/>
 *     <enumeration value="CER"/>
 *     <enumeration value="MODDV"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "EntityClassDevice")
@XmlEnum
public enum EntityClassDevice {

    DEV,
    CER,
    MODDV;

    public String value() {
        return name();
    }

    public static EntityClassDevice fromValue(String v) {
        return valueOf(v);
    }

}
