//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActClassSupply�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActClassSupply">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="SPLY"/>
 *     <enumeration value="DIET"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActClassSupply")
@XmlEnum
public enum ActClassSupply {

    SPLY,
    DIET;

    public String value() {
        return name();
    }

    public static ActClassSupply fromValue(String v) {
        return valueOf(v);
    }

}
