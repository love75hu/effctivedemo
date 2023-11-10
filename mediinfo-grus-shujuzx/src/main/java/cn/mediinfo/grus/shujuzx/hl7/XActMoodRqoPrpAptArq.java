//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActMoodRqoPrpAptArq�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ActMoodRqoPrpAptArq">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="APT"/>
 *     <enumeration value="ARQ"/>
 *     <enumeration value="PRP"/>
 *     <enumeration value="RQO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActMoodRqoPrpAptArq")
@XmlEnum
public enum XActMoodRqoPrpAptArq {

    APT,
    ARQ,
    PRP,
    RQO;

    public String value() {
        return name();
    }

    public static XActMoodRqoPrpAptArq fromValue(String v) {
        return valueOf(v);
    }

}
