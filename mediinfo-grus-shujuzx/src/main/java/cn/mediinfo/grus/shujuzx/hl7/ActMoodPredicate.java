//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActMoodPredicate�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActMoodPredicate">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="EVN.CRT"/>
 *     <enumeration value="GOL"/>
 *     <enumeration value="OPT"/>
 *     <enumeration value="PERM"/>
 *     <enumeration value="PERMRQ"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActMoodPredicate")
@XmlEnum
public enum ActMoodPredicate {

    @XmlEnumValue("EVN.CRT")
    EVN_CRT("EVN.CRT"),
    GOL("GOL"),
    OPT("OPT"),
    PERM("PERM"),
    PERMRQ("PERMRQ");
    private final String value;

    ActMoodPredicate(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ActMoodPredicate fromValue(String v) {
        for (ActMoodPredicate c: ActMoodPredicate.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
