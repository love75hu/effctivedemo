//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             The character string data type stands for text data,
 *             primarily intended for machine processing (e.g.,
 *             sorting, querying, indexing, etc.) Used for names,
 *             symbols, and formal expressions.
 *          
 * 
 * <p>ST complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="ST">
 *   <complexContent>
 *     <restriction base="{urn:hl7-org:v3}ED">
 *       <sequence>
 *         <element name="reference" type="{urn:hl7-org:v3}TEL" maxOccurs="0" minOccurs="0"/>
 *         <element name="thumbnail" type="{urn:hl7-org:v3}ED" maxOccurs="0" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="representation" type="{urn:hl7-org:v3}BinaryDataEncoding" fixed="TXT" />
 *       <attribute name="mediaType" type="{urn:hl7-org:v3}cs" fixed="text/plain" />
 *       <attribute name="language" type="{urn:hl7-org:v3}cs" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ST")
@XmlSeeAlso({
    SC.class,
    ADXP.class,
    ENXP.class
})
public class ST
    extends ED
{


}
