//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RelatedLinkType�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="RelatedLinkType">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="REL"/>
 *     <enumeration value="BACKUP"/>
 *     <enumeration value="DIRAUTH"/>
 *     <enumeration value="INDAUTH"/>
 *     <enumeration value="PART"/>
 *     <enumeration value="REPL"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "RelatedLinkType")
@XmlEnum
public enum RelatedLinkType {

    REL,
    BACKUP,
    DIRAUTH,
    INDAUTH,
    PART,
    REPL;

    public String value() {
        return name();
    }

    public static RelatedLinkType fromValue(String v) {
        return valueOf(v);
    }

}
