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
 * <p>ModelMediaType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ModelMediaType">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="model/vrml"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ModelMediaType")
@XmlEnum
public enum ModelMediaType {

    @XmlEnumValue("model/vrml")
    MODEL_VRML("model/vrml");
    private final String value;

    ModelMediaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ModelMediaType fromValue(String v) {
        for (ModelMediaType c: ModelMediaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
