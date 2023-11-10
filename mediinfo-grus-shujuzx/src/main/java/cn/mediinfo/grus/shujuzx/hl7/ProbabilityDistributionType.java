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
 * <p>ProbabilityDistributionType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ProbabilityDistributionType">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="B"/>
 *     <enumeration value="E"/>
 *     <enumeration value="F"/>
 *     <enumeration value="G"/>
 *     <enumeration value="LN"/>
 *     <enumeration value="N"/>
 *     <enumeration value="T"/>
 *     <enumeration value="U"/>
 *     <enumeration value="X2"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ProbabilityDistributionType")
@XmlEnum
public enum ProbabilityDistributionType {

    B("B"),
    E("E"),
    F("F"),
    G("G"),
    LN("LN"),
    N("N"),
    T("T"),
    U("U"),
    @XmlEnumValue("X2")
    X_2("X2");
    private final String value;

    ProbabilityDistributionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProbabilityDistributionType fromValue(String v) {
        for (ProbabilityDistributionType c: ProbabilityDistributionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
