//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>GLIST_TS complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="GLIST_TS">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ANY">
 *       <sequence>
 *         <element name="head" type="{urn:hl7-org:v3}TS"/>
 *         <element name="increment" type="{urn:hl7-org:v3}PQ"/>
 *       </sequence>
 *       <attribute name="period" type="{urn:hl7-org:v3}int" />
 *       <attribute name="denominator" type="{urn:hl7-org:v3}int" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GLIST_TS", propOrder = {
    "head",
    "increment"
})
public class GLISTTS
    extends ANY
{

    @XmlElement(required = true)
    protected TS head;
    @XmlElement(required = true)
    protected PQ increment;
    @XmlAttribute(name = "period")
    protected BigInteger period;
    @XmlAttribute(name = "denominator")
    protected BigInteger denominator;

    /**
     * ��ȡhead���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getHead() {
        return head;
    }

    /**
     * ����head���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setHead(TS value) {
        this.head = value;
    }

    /**
     * ��ȡincrement���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getIncrement() {
        return increment;
    }

    /**
     * ����increment���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setIncrement(PQ value) {
        this.increment = value;
    }

    /**
     * ��ȡperiod���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPeriod() {
        return period;
    }

    /**
     * ����period���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPeriod(BigInteger value) {
        this.period = value;
    }

    /**
     * ��ȡdenominator���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDenominator() {
        return denominator;
    }

    /**
     * ����denominator���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDenominator(BigInteger value) {
        this.denominator = value;
    }

}
