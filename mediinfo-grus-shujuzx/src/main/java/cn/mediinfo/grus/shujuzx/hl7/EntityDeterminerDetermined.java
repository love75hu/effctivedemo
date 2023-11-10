//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>EntityDeterminerDetermined�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="EntityDeterminerDetermined">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="KIND"/>
 *     <enumeration value="QUANTIFIED_KIND"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "EntityDeterminerDetermined")
@XmlEnum
public enum EntityDeterminerDetermined {

    KIND,
    QUANTIFIED_KIND;

    public String value() {
        return name();
    }

    public static EntityDeterminerDetermined fromValue(String v) {
        return valueOf(v);
    }

}
