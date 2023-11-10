//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>ActRelationshipObjective�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="ActRelationshipObjective">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="OBJC"/>
 *     <enumeration value="OBJF"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "ActRelationshipObjective")
@XmlEnum
public enum ActRelationshipObjective {

    OBJC,
    OBJF;

    public String value() {
        return name();
    }

    public static ActRelationshipObjective fromValue(String v) {
        return valueOf(v);
    }

}
