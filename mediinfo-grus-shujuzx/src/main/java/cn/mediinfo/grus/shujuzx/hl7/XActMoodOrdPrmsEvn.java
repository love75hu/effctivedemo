//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_ActMoodOrdPrmsEvn�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_ActMoodOrdPrmsEvn">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="EVN"/>
 *     <enumeration value="PRMS"/>
 *     <enumeration value="RQO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_ActMoodOrdPrmsEvn")
@XmlEnum
public enum XActMoodOrdPrmsEvn {

    EVN,
    PRMS,
    RQO;

    public String value() {
        return name();
    }

    public static XActMoodOrdPrmsEvn fromValue(String v) {
        return valueOf(v);
    }

}
