//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>EntityClassPlace�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="EntityClassPlace">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="PLC"/>
 *     <enumeration value="CITY"/>
 *     <enumeration value="COUNTRY"/>
 *     <enumeration value="COUNTY"/>
 *     <enumeration value="PROVINCE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "EntityClassPlace")
@XmlEnum
public enum EntityClassPlace {

    PLC,
    CITY,
    COUNTRY,
    COUNTY,
    PROVINCE;

    public String value() {
        return name();
    }

    public static EntityClassPlace fromValue(String v) {
        return valueOf(v);
    }

}
