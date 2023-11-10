//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActClassDocumentEntryAct�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ActClassDocumentEntryAct">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="ACT"/>
 *     <enumeration value="ACCM"/>
 *     <enumeration value="CONS"/>
 *     <enumeration value="CTTEVENT"/>
 *     <enumeration value="INC"/>
 *     <enumeration value="INFRM"/>
 *     <enumeration value="PCPR"/>
 *     <enumeration value="REG"/>
 *     <enumeration value="SPCTRT"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActClassDocumentEntryAct")
@XmlEnum
public enum XActClassDocumentEntryAct {

    ACT,
    ACCM,
    CONS,
    CTTEVENT,
    INC,
    INFRM,
    PCPR,
    REG,
    SPCTRT;

    public String value() {
        return name();
    }

    public static XActClassDocumentEntryAct fromValue(String v) {
        return valueOf(v);
    }

}
