//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>TemporallyPertains�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="TemporallyPertains">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="SAS"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "TemporallyPertains")
@XmlEnum
public enum TemporallyPertains {

    SAS;

    public String value() {
        return name();
    }

    public static TemporallyPertains fromValue(String v) {
        return valueOf(v);
    }

}
