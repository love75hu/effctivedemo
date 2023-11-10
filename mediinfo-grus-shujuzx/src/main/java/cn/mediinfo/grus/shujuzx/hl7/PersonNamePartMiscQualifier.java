//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>PersonNamePartMiscQualifier�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="PersonNamePartMiscQualifier">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="CL"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "PersonNamePartMiscQualifier")
@XmlEnum
public enum PersonNamePartMiscQualifier {

    CL;

    public String value() {
        return name();
    }

    public static PersonNamePartMiscQualifier fromValue(String v) {
        return valueOf(v);
    }

}
