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
 * <p>IntegrityCheckAlgorithm�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="IntegrityCheckAlgorithm">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="SHA-1"/>
 *     <enumeration value="SHA-256"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "IntegrityCheckAlgorithm")
@XmlEnum
public enum IntegrityCheckAlgorithm {

    @XmlEnumValue("SHA-1")
    SHA_1("SHA-1"),
    @XmlEnumValue("SHA-256")
    SHA_256("SHA-256");
    private final String value;

    IntegrityCheckAlgorithm(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static IntegrityCheckAlgorithm fromValue(String v) {
        for (IntegrityCheckAlgorithm c: IntegrityCheckAlgorithm.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
