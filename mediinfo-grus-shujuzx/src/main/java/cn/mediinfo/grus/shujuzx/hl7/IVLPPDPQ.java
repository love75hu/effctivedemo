//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>IVL_PPD_PQ complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="IVL_PPD_PQ">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}SXCM_PPD_PQ">
 *       <choice minOccurs="0">
 *         <sequence>
 *           <element name="low" type="{urn:hl7-org:v3}IVXB_PPD_PQ"/>
 *           <choice minOccurs="0">
 *             <element name="width" type="{urn:hl7-org:v3}PPD_PQ" minOccurs="0"/>
 *             <element name="high" type="{urn:hl7-org:v3}IVXB_PPD_PQ" minOccurs="0"/>
 *           </choice>
 *         </sequence>
 *         <element name="high" type="{urn:hl7-org:v3}IVXB_PPD_PQ"/>
 *         <sequence>
 *           <element name="width" type="{urn:hl7-org:v3}PPD_PQ"/>
 *           <element name="high" type="{urn:hl7-org:v3}IVXB_PPD_PQ" minOccurs="0"/>
 *         </sequence>
 *         <sequence>
 *           <element name="center" type="{urn:hl7-org:v3}PPD_PQ"/>
 *           <element name="width" type="{urn:hl7-org:v3}PPD_PQ" minOccurs="0"/>
 *         </sequence>
 *       </choice>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IVL_PPD_PQ", propOrder = {
    "rest"
})
public class IVLPPDPQ
    extends SXCMPPDPQ
{

    @XmlElementRefs({
        @XmlElementRef(name = "low", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "width", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "high", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "center", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<? extends PPDPQ>> rest;

    /**
     * ��ȡ����ģ�͵����ಿ�֡�
     * 
     * <p>
     * ��������ԭ��, ����ȡ���Ǵ� "catch-all" ����: 
     * �ֶ����� "High" ��ģʽ��������ͬ����ʹ�á������: 
     * file:/D:/CDACoreSchemas/coreschemas/datatypes.xsd�ĵ� 595 ��
     * file:/D:/CDACoreSchemas/coreschemas/datatypes.xsd�ĵ� 586 ��
     * <p>
     * Ҫ���������, �뽫���Զ�������Ӧ����������������
     * ������һ���Ը���������: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     * {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     * {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     * {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     * 
     * 
     * @return
     *     The value of the rest property.
     */
    public List<JAXBElement<? extends PPDPQ>> getRest() {
        if (rest == null) {
            rest = new ArrayList<>();
        }
        return this.rest;
    }

}
