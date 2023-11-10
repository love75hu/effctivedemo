//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>x_DocumentSubstanceMood�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <pre>{@code
 * <simpleType name="x_DocumentSubstanceMood">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="INT"/>
 *     <enumeration value="EVN"/>
 *     <enumeration value="PRMS"/>
 *     <enumeration value="PRP"/>
 *     <enumeration value="RQO"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "x_DocumentSubstanceMood")
@XmlEnum
public enum XDocumentSubstanceMood {

    INT,
    EVN,
    PRMS,
    PRP,
    RQO;

    public String value() {
        return name();
    }

    public static XDocumentSubstanceMood fromValue(String v) {
        return valueOf(v);
    }

}
