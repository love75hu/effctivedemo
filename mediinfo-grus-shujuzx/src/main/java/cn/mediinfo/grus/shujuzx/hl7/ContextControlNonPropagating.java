//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ContextControlNonPropagating�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ContextControlNonPropagating">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="AN"/>
 *     <enumeration value="ON"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ContextControlNonPropagating")
@XmlEnum
public enum ContextControlNonPropagating {

    AN,
    ON;

    public String value() {
        return name();
    }

    public static ContextControlNonPropagating fromValue(String v) {
        return valueOf(v);
    }

}
