//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>RTO_PQ_PQ complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="RTO_PQ_PQ">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}QTY">
 *       <sequence>
 *         <element name="numerator" type="{urn:hl7-org:v3}PQ"/>
 *         <element name="denominator" type="{urn:hl7-org:v3}PQ"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RTO_PQ_PQ", propOrder = {
    "numerator",
    "denominator"
})
public class RTOPQPQ
    extends QTY
{

    @XmlElement(required = true)
    protected PQ numerator;
    @XmlElement(required = true)
    protected PQ denominator;

    /**
     * ��ȡnumerator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getNumerator() {
        return numerator;
    }

    /**
     * ����numerator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setNumerator(PQ value) {
        this.numerator = value;
    }

    /**
     * ��ȡdenominator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getDenominator() {
        return denominator;
    }

    /**
     * ����denominator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setDenominator(PQ value) {
        this.denominator = value;
    }

}
