//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActRelationshipHasComponent�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActRelationshipHasComponent">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="COMP"/>
 *     <enumeration value="ARR"/>
 *     <enumeration value="CTRLV"/>
 *     <enumeration value="DEP"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActRelationshipHasComponent")
@XmlEnum
public enum ActRelationshipHasComponent {

    COMP,
    ARR,
    CTRLV,
    DEP;

    public String value() {
        return name();
    }

    public static ActRelationshipHasComponent fromValue(String v) {
        return valueOf(v);
    }

}
