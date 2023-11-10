//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>PersonNamePartAffixTypes�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="PersonNamePartAffixTypes">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="AC"/>
 *     <enumeration value="NB"/>
 *     <enumeration value="PR"/>
 *     <enumeration value="VV"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "PersonNamePartAffixTypes")
@XmlEnum
public enum PersonNamePartAffixTypes {

    AC,
    NB,
    PR,
    VV;

    public String value() {
        return name();
    }

    public static PersonNamePartAffixTypes fromValue(String v) {
        return valueOf(v);
    }

}
