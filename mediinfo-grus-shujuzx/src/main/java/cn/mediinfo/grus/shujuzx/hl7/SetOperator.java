//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>SetOperator�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="SetOperator">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="A"/>
 *     <enumeration value="E"/>
 *     <enumeration value="H"/>
 *     <enumeration value="I"/>
 *     <enumeration value="P"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "SetOperator")
@XmlEnum
public enum SetOperator {

    A,
    E,
    H,
    I,
    P;

    public String value() {
        return name();
    }

    public static SetOperator fromValue(String v) {
        return valueOf(v);
    }

}
