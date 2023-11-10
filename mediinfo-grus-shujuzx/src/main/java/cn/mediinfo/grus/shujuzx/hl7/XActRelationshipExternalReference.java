//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActRelationshipExternalReference�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ActRelationshipExternalReference">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="XCRPT"/>
 *     <enumeration value="RPLC"/>
 *     <enumeration value="SPRT"/>
 *     <enumeration value="ELNK"/>
 *     <enumeration value="REFR"/>
 *     <enumeration value="SUBJ"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActRelationshipExternalReference")
@XmlEnum
public enum XActRelationshipExternalReference {

    XCRPT,
    RPLC,
    SPRT,
    ELNK,
    REFR,
    SUBJ;

    public String value() {
        return name();
    }

    public static XActRelationshipExternalReference fromValue(String v) {
        return valueOf(v);
    }

}
