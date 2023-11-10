//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ParticipationInformationRecipient�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ParticipationInformationRecipient">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="IRCP"/>
 *     <enumeration value="NOT"/>
 *     <enumeration value="PRCP"/>
 *     <enumeration value="REFB"/>
 *     <enumeration value="REFT"/>
 *     <enumeration value="TRC"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ParticipationInformationRecipient")
@XmlEnum
public enum ParticipationInformationRecipient {

    IRCP,
    NOT,
    PRCP,
    REFB,
    REFT,
    TRC;

    public String value() {
        return name();
    }

    public static ParticipationInformationRecipient fromValue(String v) {
        return valueOf(v);
    }

}
