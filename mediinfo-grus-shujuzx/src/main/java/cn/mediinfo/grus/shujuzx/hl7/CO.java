//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             Coded data, where the domain from which the codeset comes
 *             is ordered. The Coded Ordinal data type adds semantics
 *             related to ordering so that models that make use of such
 *             domains may introduce model elements that involve statements
 *             about the order of the terms in a domain. 
 *          
 * 
 * <p>CO complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="CO">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}CV">
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CO")
public class CO
    extends CV
{


}
