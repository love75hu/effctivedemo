//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RoleClassEmployee�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="RoleClassEmployee">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="EMP"/>
 *     <enumeration value="MIL"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "RoleClassEmployee")
@XmlEnum
public enum RoleClassEmployee {

    EMP,
    MIL;

    public String value() {
        return name();
    }

    public static RoleClassEmployee fromValue(String v) {
        return valueOf(v);
    }

}
