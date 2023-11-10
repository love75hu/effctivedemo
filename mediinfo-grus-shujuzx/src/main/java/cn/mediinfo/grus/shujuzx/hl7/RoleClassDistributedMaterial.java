//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RoleClassDistributedMaterial�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="RoleClassDistributedMaterial">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="DST"/>
 *     <enumeration value="RET"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "RoleClassDistributedMaterial")
@XmlEnum
public enum RoleClassDistributedMaterial {

    DST,
    RET;

    public String value() {
        return name();
    }

    public static RoleClassDistributedMaterial fromValue(String v) {
        return valueOf(v);
    }

}
