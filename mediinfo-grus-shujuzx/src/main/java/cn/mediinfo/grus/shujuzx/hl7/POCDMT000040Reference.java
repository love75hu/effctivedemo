//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>POCD_MT000040.Reference complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="POCD_MT000040.Reference">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         <element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="seperatableInd" type="{urn:hl7-org:v3}BL" minOccurs="0"/>
 *         <choice>
 *           <element name="externalAct" type="{urn:hl7-org:v3}POCD_MT000040.ExternalAct"/>
 *           <element name="externalObservation" type="{urn:hl7-org:v3}POCD_MT000040.ExternalObservation"/>
 *           <element name="externalProcedure" type="{urn:hl7-org:v3}POCD_MT000040.ExternalProcedure"/>
 *           <element name="externalDocument" type="{urn:hl7-org:v3}POCD_MT000040.ExternalDocument"/>
 *         </choice>
 *       </sequence>
 *       <attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       <attribute name="typeCode" use="required" type="{urn:hl7-org:v3}x_ActRelationshipExternalReference" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Reference", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "seperatableInd",
    "externalAct",
    "externalObservation",
    "externalProcedure",
    "externalDocument"
})
public class POCDMT000040Reference {

    protected List<CS> realmCode;
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    protected BL seperatableInd;
    protected POCDMT000040ExternalAct externalAct;
    protected POCDMT000040ExternalObservation externalObservation;
    protected POCDMT000040ExternalProcedure externalProcedure;
    protected POCDMT000040ExternalDocument externalDocument;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "typeCode", required = true)
    protected XActRelationshipExternalReference typeCode;

    /**
     * Gets the value of the realmCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the realmCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
     * 
     * 
     * @return
     *     The value of the realmCode property.
     */
    public List<CS> getRealmCode() {
        if (realmCode == null) {
            realmCode = new ArrayList<>();
        }
        return this.realmCode;
    }

    /**
     * ��ȡtypeId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040InfrastructureRootTypeId }
     *     
     */
    public POCDMT000040InfrastructureRootTypeId getTypeId() {
        return typeId;
    }

    /**
     * ����typeId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040InfrastructureRootTypeId }
     *     
     */
    public void setTypeId(POCDMT000040InfrastructureRootTypeId value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the templateId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     * @return
     *     The value of the templateId property.
     */
    public List<II> getTemplateId() {
        if (templateId == null) {
            templateId = new ArrayList<>();
        }
        return this.templateId;
    }

    /**
     * ��ȡseperatableInd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BL }
     *     
     */
    public BL getSeperatableInd() {
        return seperatableInd;
    }

    /**
     * ����seperatableInd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BL }
     *     
     */
    public void setSeperatableInd(BL value) {
        this.seperatableInd = value;
    }

    /**
     * ��ȡexternalAct���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalAct }
     *     
     */
    public POCDMT000040ExternalAct getExternalAct() {
        return externalAct;
    }

    /**
     * ����externalAct���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalAct }
     *     
     */
    public void setExternalAct(POCDMT000040ExternalAct value) {
        this.externalAct = value;
    }

    /**
     * ��ȡexternalObservation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalObservation }
     *     
     */
    public POCDMT000040ExternalObservation getExternalObservation() {
        return externalObservation;
    }

    /**
     * ����externalObservation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalObservation }
     *     
     */
    public void setExternalObservation(POCDMT000040ExternalObservation value) {
        this.externalObservation = value;
    }

    /**
     * ��ȡexternalProcedure���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalProcedure }
     *     
     */
    public POCDMT000040ExternalProcedure getExternalProcedure() {
        return externalProcedure;
    }

    /**
     * ����externalProcedure���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalProcedure }
     *     
     */
    public void setExternalProcedure(POCDMT000040ExternalProcedure value) {
        this.externalProcedure = value;
    }

    /**
     * ��ȡexternalDocument���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ExternalDocument }
     *     
     */
    public POCDMT000040ExternalDocument getExternalDocument() {
        return externalDocument;
    }

    /**
     * ����externalDocument���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ExternalDocument }
     *     
     */
    public void setExternalDocument(POCDMT000040ExternalDocument value) {
        this.externalDocument = value;
    }

    /**
     * Gets the value of the nullFlavor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nullFlavor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the nullFlavor property.
     */
    public List<String> getNullFlavor() {
        if (nullFlavor == null) {
            nullFlavor = new ArrayList<>();
        }
        return this.nullFlavor;
    }

    /**
     * ��ȡtypeCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XActRelationshipExternalReference }
     *     
     */
    public XActRelationshipExternalReference getTypeCode() {
        return typeCode;
    }

    /**
     * ����typeCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XActRelationshipExternalReference }
     *     
     */
    public void setTypeCode(XActRelationshipExternalReference value) {
        this.typeCode = value;
    }

}
