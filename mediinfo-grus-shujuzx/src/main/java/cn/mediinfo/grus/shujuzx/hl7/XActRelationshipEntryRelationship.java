//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActRelationshipEntryRelationship�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ActRelationshipEntryRelationship">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="XCRPT"/>
 *     <enumeration value="COMP"/>
 *     <enumeration value="RSON"/>
 *     <enumeration value="SPRT"/>
 *     <enumeration value="CAUS"/>
 *     <enumeration value="GEVL"/>
 *     <enumeration value="MFST"/>
 *     <enumeration value="REFR"/>
 *     <enumeration value="SAS"/>
 *     <enumeration value="SUBJ"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActRelationshipEntryRelationship")
@XmlEnum
public enum XActRelationshipEntryRelationship {

    XCRPT,
    COMP,
    RSON,
    SPRT,
    CAUS,
    GEVL,
    MFST,
    REFR,
    SAS,
    SUBJ;

    public String value() {
        return name();
    }

    public static XActRelationshipEntryRelationship fromValue(String v) {
        return valueOf(v);
    }

}
