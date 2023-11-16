//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>POCD_MT000040.Reference complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取typeId属性的值。
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
     * 设置typeId属性的值。
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
     * 获取seperatableInd属性的值。
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
     * 设置seperatableInd属性的值。
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
     * 获取externalAct属性的值。
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
     * 设置externalAct属性的值。
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
     * 获取externalObservation属性的值。
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
     * 设置externalObservation属性的值。
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
     * 获取externalProcedure属性的值。
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
     * 设置externalProcedure属性的值。
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
     * 获取externalDocument属性的值。
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
     * 设置externalDocument属性的值。
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
     * 获取typeCode属性的值。
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
     * 设置typeCode属性的值。
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
