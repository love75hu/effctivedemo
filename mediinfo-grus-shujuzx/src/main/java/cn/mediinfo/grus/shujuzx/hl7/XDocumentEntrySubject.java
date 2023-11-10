//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_DocumentEntrySubject�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_DocumentEntrySubject">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="SPEC"/>
 *     <enumeration value="PAT"/>
 *     <enumeration value="PRS"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_DocumentEntrySubject")
@XmlEnum
public enum XDocumentEntrySubject {

    SPEC,
    PAT,
    PRS;

    public String value() {
        return name();
    }

    public static XDocumentEntrySubject fromValue(String v) {
        return valueOf(v);
    }

}
