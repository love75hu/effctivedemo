//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>TimingEvent�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="TimingEvent">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="AC"/>
 *     <enumeration value="ACD"/>
 *     <enumeration value="ACM"/>
 *     <enumeration value="ACV"/>
 *     <enumeration value="HS"/>
 *     <enumeration value="IC"/>
 *     <enumeration value="ICD"/>
 *     <enumeration value="ICM"/>
 *     <enumeration value="ICV"/>
 *     <enumeration value="PC"/>
 *     <enumeration value="PCD"/>
 *     <enumeration value="PCM"/>
 *     <enumeration value="PCV"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "TimingEvent")
@XmlEnum
public enum TimingEvent {

    AC,
    ACD,
    ACM,
    ACV,
    HS,
    IC,
    ICD,
    ICM,
    ICV,
    PC,
    PCD,
    PCM,
    PCV;

    public String value() {
        return name();
    }

    public static TimingEvent fromValue(String v) {
        return valueOf(v);
    }

}
