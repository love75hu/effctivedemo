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
 *             A name for an organization. A sequence of name parts.
 *          
 * 
 * <p>ON complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="ON">
 *   <complexContent>
 *     <restriction base="{urn:hl7-org:v3}EN">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="delimiter" type="{urn:hl7-org:v3}en.delimiter"/>
 *           <element name="prefix" type="{urn:hl7-org:v3}en.prefix"/>
 *           <element name="suffix" type="{urn:hl7-org:v3}en.suffix"/>
 *         </choice>
 *         <element name="validTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="use" type="{urn:hl7-org:v3}set_EntityNameUse" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ON")
public class ON
    extends EN
{


}
