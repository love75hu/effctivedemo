//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_InformationRecipientRole�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_InformationRecipientRole">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="ASSIGNED"/>
 *     <enumeration value="HLTHCHRT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_InformationRecipientRole")
@XmlEnum
public enum XInformationRecipientRole {

    ASSIGNED,
    HLTHCHRT;

    public String value() {
        return name();
    }

    public static XInformationRecipientRole fromValue(String v) {
        return valueOf(v);
    }

}
