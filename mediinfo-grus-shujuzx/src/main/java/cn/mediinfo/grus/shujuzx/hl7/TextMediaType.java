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
 * <p>TextMediaType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="TextMediaType">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="text/html"/>
 *     <enumeration value="text/plain"/>
 *     <enumeration value="text/rtf"/>
 *     <enumeration value="text/sgml"/>
 *     <enumeration value="text/x-hl7-ft"/>
 *     <enumeration value="text/xml"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "TextMediaType")
@XmlEnum
public enum TextMediaType {

    @XmlEnumValue("text/html")
    TEXT_HTML("text/html"),
    @XmlEnumValue("text/plain")
    TEXT_PLAIN("text/plain"),
    @XmlEnumValue("text/rtf")
    TEXT_RTF("text/rtf"),
    @XmlEnumValue("text/sgml")
    TEXT_SGML("text/sgml"),
    @XmlEnumValue("text/x-hl7-ft")
    TEXT_X_HL_7_FT("text/x-hl7-ft"),
    @XmlEnumValue("text/xml")
    TEXT_XML("text/xml");
    private final String value;

    TextMediaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TextMediaType fromValue(String v) {
        for (TextMediaType c: TextMediaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
