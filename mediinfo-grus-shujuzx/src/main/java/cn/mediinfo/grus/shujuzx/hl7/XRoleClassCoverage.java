//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_RoleClassCoverage�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_RoleClassCoverage">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="COVPTY"/>
 *     <enumeration value="POLHOLD"/>
 *     <enumeration value="SPNSR"/>
 *     <enumeration value="UNDWRT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_RoleClassCoverage")
@XmlEnum
public enum XRoleClassCoverage {

    COVPTY,
    POLHOLD,
    SPNSR,
    UNDWRT;

    public String value() {
        return name();
    }

    public static XRoleClassCoverage fromValue(String v) {
        return valueOf(v);
    }

}
