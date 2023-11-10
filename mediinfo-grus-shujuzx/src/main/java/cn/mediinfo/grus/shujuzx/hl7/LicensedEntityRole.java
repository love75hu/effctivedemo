//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>LicensedEntityRole�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="LicensedEntityRole">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="LIC"/>
 *     <enumeration value="NOT"/>
 *     <enumeration value="PROV"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "LicensedEntityRole")
@XmlEnum
public enum LicensedEntityRole {

    LIC,
    NOT,
    PROV;

    public String value() {
        return name();
    }

    public static LicensedEntityRole fromValue(String v) {
        return valueOf(v);
    }

}
