//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>EntityClassContainer�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="EntityClassContainer">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="CONT"/>
 *     <enumeration value="HOLD"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "EntityClassContainer")
@XmlEnum
public enum EntityClassContainer {

    CONT,
    HOLD;

    public String value() {
        return name();
    }

    public static EntityClassContainer fromValue(String v) {
        return valueOf(v);
    }

}
