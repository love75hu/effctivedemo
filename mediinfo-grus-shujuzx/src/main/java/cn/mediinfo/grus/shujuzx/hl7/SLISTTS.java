//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlList;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>SLIST_TS complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="SLIST_TS">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ANY">
 *       <sequence>
 *         <element name="origin" type="{urn:hl7-org:v3}TS"/>
 *         <element name="scale" type="{urn:hl7-org:v3}PQ"/>
 *         <element name="digits" type="{urn:hl7-org:v3}list_int"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLIST_TS", propOrder = {
    "origin",
    "scale",
    "digits"
})
public class SLISTTS
    extends ANY
{

    @XmlElement(required = true)
    protected TS origin;
    @XmlElement(required = true)
    protected PQ scale;
    @XmlList
    @XmlElement(required = true)
    protected List<BigInteger> digits;

    /**
     * ��ȡorigin���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getOrigin() {
        return origin;
    }

    /**
     * ����origin���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setOrigin(TS value) {
        this.origin = value;
    }

    /**
     * ��ȡscale���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getScale() {
        return scale;
    }

    /**
     * ����scale���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setScale(PQ value) {
        this.scale = value;
    }

    /**
     * Gets the value of the digits property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the digits property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDigits().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     * @return
     *     The value of the digits property.
     */
    public List<BigInteger> getDigits() {
        if (digits == null) {
            digits = new ArrayList<>();
        }
        return this.digits;
    }

}
