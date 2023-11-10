//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ParticipationPrfEntVrf�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ParticipationPrfEntVrf">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="PRF"/>
 *     <enumeration value="VRF"/>
 *     <enumeration value="ENT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ParticipationPrfEntVrf")
@XmlEnum
public enum XParticipationPrfEntVrf {

    PRF,
    VRF,
    ENT;

    public String value() {
        return name();
    }

    public static XParticipationPrfEntVrf fromValue(String v) {
        return valueOf(v);
    }

}
