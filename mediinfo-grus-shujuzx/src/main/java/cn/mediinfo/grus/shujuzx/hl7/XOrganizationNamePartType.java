//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_OrganizationNamePartType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_OrganizationNamePartType">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="DEL"/>
 *     <enumeration value="PFX"/>
 *     <enumeration value="SFX"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_OrganizationNamePartType")
@XmlEnum
public enum XOrganizationNamePartType {

    DEL,
    PFX,
    SFX;

    public String value() {
        return name();
    }

    public static XOrganizationNamePartType fromValue(String v) {
        return valueOf(v);
    }

}
