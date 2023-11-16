//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.*;


/**
 * <p>POCD_MT000040.ClinicalDocument complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="POCD_MT000040.ClinicalDocument">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId"/>
 *         <element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="id" type="{urn:hl7-org:v3}II"/>
 *         <element name="code" type="{urn:hl7-org:v3}CE"/>
 *         <element name="title" type="{urn:hl7-org:v3}ST" minOccurs="0"/>
 *         <element name="effectiveTime" type="{urn:hl7-org:v3}TS"/>
 *         <element name="confidentialityCode" type="{urn:hl7-org:v3}CE"/>
 *         <element name="languageCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         <element name="setId" type="{urn:hl7-org:v3}II" minOccurs="0"/>
 *         <element name="versionNumber" type="{urn:hl7-org:v3}INT" minOccurs="0"/>
 *         <element name="copyTime" type="{urn:hl7-org:v3}TS" minOccurs="0"/>
 *         <element name="recordTarget" type="{urn:hl7-org:v3}POCD_MT000040.RecordTarget" maxOccurs="unbounded"/>
 *         <element name="author" type="{urn:hl7-org:v3}POCD_MT000040.Author" maxOccurs="unbounded"/>
 *         <element name="dataEnterer" type="{urn:hl7-org:v3}POCD_MT000040.DataEnterer" minOccurs="0"/>
 *         <element name="informant" type="{urn:hl7-org:v3}POCD_MT000040.Informant12" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="custodian" type="{urn:hl7-org:v3}POCD_MT000040.Custodian"/>
 *         <element name="informationRecipient" type="{urn:hl7-org:v3}POCD_MT000040.InformationRecipient" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="legalAuthenticator" type="{urn:hl7-org:v3}POCD_MT000040.LegalAuthenticator" minOccurs="0"/>
 *         <element name="authenticator" type="{urn:hl7-org:v3}POCD_MT000040.Authenticator" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="participant" type="{urn:hl7-org:v3}POCD_MT000040.Participant1" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="inFulfillmentOf" type="{urn:hl7-org:v3}POCD_MT000040.InFulfillmentOf" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="documentationOf" type="{urn:hl7-org:v3}POCD_MT000040.DocumentationOf" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="relatedDocument" type="{urn:hl7-org:v3}POCD_MT000040.RelatedDocument" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="authorization" type="{urn:hl7-org:v3}POCD_MT000040.Authorization" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="componentOf" type="{urn:hl7-org:v3}POCD_MT000040.Component1" minOccurs="0"/>
 *         <element name="component" type="{urn:hl7-org:v3}POCD_MT000040.Component2"/>
 *       </sequence>
 *       <attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       <attribute name="classCode" type="{urn:hl7-org:v3}ActClinicalDocument" fixed="DOCCLIN" />
 *       <attribute name="moodCode" type="{urn:hl7-org:v3}ActMood" fixed="EVN" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.ClinicalDocument", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "code",
    "title",
    "effectiveTime",
    "confidentialityCode",
    "languageCode",
    "setId",
    "versionNumber",
    "copyTime",
    "recordTarget",
    "author",
    "dataEnterer",
    "informant",
    "custodian",
    "informationRecipient",
    "legalAuthenticator",
    "authenticator",
    "participant",
    "inFulfillmentOf",
    "documentationOf",
    "relatedDocument",
    "authorization",
    "componentOf",
    "component"
})
@XmlRootElement(name = "ClinicalDocument")
public class POCDMT000040ClinicalDocument {

    protected List<CS> realmCode;
    @XmlElement(required = true)
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    @XmlElement(required = true)
    protected II id;
    @XmlElement(required = true)
    protected CE code;
    protected ST title;
    @XmlElement(required = true)
    protected TS effectiveTime;
    @XmlElement(required = true)
    protected CE confidentialityCode;
    protected CS languageCode;
    protected II setId;
    protected INT versionNumber;
    protected TS copyTime;
    @XmlElement(required = true)
    protected List<POCDMT000040RecordTarget> recordTarget;
    @XmlElement(required = true)
    protected List<POCDMT000040Author> author;
    protected POCDMT000040DataEnterer dataEnterer;
    protected List<POCDMT000040Informant12> informant;
    @XmlElement(required = true)
    protected POCDMT000040Custodian custodian;
    protected List<POCDMT000040InformationRecipient> informationRecipient;
    protected POCDMT000040LegalAuthenticator legalAuthenticator;
    protected List<POCDMT000040Authenticator> authenticator;
    protected List<POCDMT000040Participant1> participant;
    protected List<POCDMT000040InFulfillmentOf> inFulfillmentOf;
    protected List<POCDMT000040DocumentationOf> documentationOf;
    protected List<POCDMT000040RelatedDocument> relatedDocument;
    protected List<POCDMT000040Authorization> authorization;
    protected POCDMT000040Component1 componentOf;
    @XmlElement(required = true)
    protected POCDMT000040Component2 component;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode")
    protected ActClinicalDocument classCode;
    @XmlAttribute(name = "moodCode")
    protected List<String> moodCode;

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
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setId(II value) {
        this.id = value;
    }

    /**
     * 获取code属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getCode() {
        return code;
    }

    /**
     * 设置code属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setCode(CE value) {
        this.code = value;
    }

    /**
     * 获取title属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ST }
     *     
     */
    public ST getTitle() {
        return title;
    }

    /**
     * 设置title属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ST }
     *     
     */
    public void setTitle(ST value) {
        this.title = value;
    }

    /**
     * 获取effectiveTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置effectiveTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setEffectiveTime(TS value) {
        this.effectiveTime = value;
    }

    /**
     * 获取confidentialityCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getConfidentialityCode() {
        return confidentialityCode;
    }

    /**
     * 设置confidentialityCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setConfidentialityCode(CE value) {
        this.confidentialityCode = value;
    }

    /**
     * 获取languageCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getLanguageCode() {
        return languageCode;
    }

    /**
     * 设置languageCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setLanguageCode(CS value) {
        this.languageCode = value;
    }

    /**
     * 获取setId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getSetId() {
        return setId;
    }

    /**
     * 设置setId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setSetId(II value) {
        this.setId = value;
    }

    /**
     * 获取versionNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link INT }
     *     
     */
    public INT getVersionNumber() {
        return versionNumber;
    }

    /**
     * 设置versionNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link INT }
     *     
     */
    public void setVersionNumber(INT value) {
        this.versionNumber = value;
    }

    /**
     * 获取copyTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getCopyTime() {
        return copyTime;
    }

    /**
     * 设置copyTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setCopyTime(TS value) {
        this.copyTime = value;
    }

    /**
     * Gets the value of the recordTarget property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the recordTarget property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040RecordTarget }
     * 
     * 
     * @return
     *     The value of the recordTarget property.
     */
    public List<POCDMT000040RecordTarget> getRecordTarget() {
        if (recordTarget == null) {
            recordTarget = new ArrayList<>();
        }
        return this.recordTarget;
    }

    /**
     * Gets the value of the author property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the author property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Author }
     * 
     * 
     * @return
     *     The value of the author property.
     */
    public List<POCDMT000040Author> getAuthor() {
        if (author == null) {
            author = new ArrayList<>();
        }
        return this.author;
    }

    /**
     * 获取dataEnterer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040DataEnterer }
     *     
     */
    public POCDMT000040DataEnterer getDataEnterer() {
        return dataEnterer;
    }

    /**
     * 设置dataEnterer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040DataEnterer }
     *     
     */
    public void setDataEnterer(POCDMT000040DataEnterer value) {
        this.dataEnterer = value;
    }

    /**
     * Gets the value of the informant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the informant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Informant12 }
     * 
     * 
     * @return
     *     The value of the informant property.
     */
    public List<POCDMT000040Informant12> getInformant() {
        if (informant == null) {
            informant = new ArrayList<>();
        }
        return this.informant;
    }

    /**
     * 获取custodian属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Custodian }
     *     
     */
    public POCDMT000040Custodian getCustodian() {
        return custodian;
    }

    /**
     * 设置custodian属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Custodian }
     *     
     */
    public void setCustodian(POCDMT000040Custodian value) {
        this.custodian = value;
    }

    /**
     * Gets the value of the informationRecipient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the informationRecipient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInformationRecipient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040InformationRecipient }
     * 
     * 
     * @return
     *     The value of the informationRecipient property.
     */
    public List<POCDMT000040InformationRecipient> getInformationRecipient() {
        if (informationRecipient == null) {
            informationRecipient = new ArrayList<>();
        }
        return this.informationRecipient;
    }

    /**
     * 获取legalAuthenticator属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040LegalAuthenticator }
     *     
     */
    public POCDMT000040LegalAuthenticator getLegalAuthenticator() {
        return legalAuthenticator;
    }

    /**
     * 设置legalAuthenticator属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040LegalAuthenticator }
     *     
     */
    public void setLegalAuthenticator(POCDMT000040LegalAuthenticator value) {
        this.legalAuthenticator = value;
    }

    /**
     * Gets the value of the authenticator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the authenticator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthenticator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Authenticator }
     * 
     * 
     * @return
     *     The value of the authenticator property.
     */
    public List<POCDMT000040Authenticator> getAuthenticator() {
        if (authenticator == null) {
            authenticator = new ArrayList<>();
        }
        return this.authenticator;
    }

    /**
     * Gets the value of the participant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the participant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParticipant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Participant1 }
     * 
     * 
     * @return
     *     The value of the participant property.
     */
    public List<POCDMT000040Participant1> getParticipant() {
        if (participant == null) {
            participant = new ArrayList<>();
        }
        return this.participant;
    }

    /**
     * Gets the value of the inFulfillmentOf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the inFulfillmentOf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInFulfillmentOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040InFulfillmentOf }
     * 
     * 
     * @return
     *     The value of the inFulfillmentOf property.
     */
    public List<POCDMT000040InFulfillmentOf> getInFulfillmentOf() {
        if (inFulfillmentOf == null) {
            inFulfillmentOf = new ArrayList<>();
        }
        return this.inFulfillmentOf;
    }

    /**
     * Gets the value of the documentationOf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the documentationOf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentationOf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040DocumentationOf }
     * 
     * 
     * @return
     *     The value of the documentationOf property.
     */
    public List<POCDMT000040DocumentationOf> getDocumentationOf() {
        if (documentationOf == null) {
            documentationOf = new ArrayList<>();
        }
        return this.documentationOf;
    }

    /**
     * Gets the value of the relatedDocument property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the relatedDocument property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040RelatedDocument }
     * 
     * 
     * @return
     *     The value of the relatedDocument property.
     */
    public List<POCDMT000040RelatedDocument> getRelatedDocument() {
        if (relatedDocument == null) {
            relatedDocument = new ArrayList<>();
        }
        return this.relatedDocument;
    }

    /**
     * Gets the value of the authorization property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the authorization property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorization().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Authorization }
     * 
     * 
     * @return
     *     The value of the authorization property.
     */
    public List<POCDMT000040Authorization> getAuthorization() {
        if (authorization == null) {
            authorization = new ArrayList<>();
        }
        return this.authorization;
    }

    /**
     * 获取componentOf属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Component1 }
     *     
     */
    public POCDMT000040Component1 getComponentOf() {
        return componentOf;
    }

    /**
     * 设置componentOf属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Component1 }
     *     
     */
    public void setComponentOf(POCDMT000040Component1 value) {
        this.componentOf = value;
    }

    /**
     * 获取component属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Component2 }
     *     
     */
    public POCDMT000040Component2 getComponent() {
        return component;
    }

    /**
     * 设置component属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Component2 }
     *     
     */
    public void setComponent(POCDMT000040Component2 value) {
        this.component = value;
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
     * 获取classCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ActClinicalDocument }
     *     
     */
    public ActClinicalDocument getClassCode() {
        if (classCode == null) {
            return ActClinicalDocument.DOCCLIN;
        } else {
            return classCode;
        }
    }

    /**
     * 设置classCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ActClinicalDocument }
     *     
     */
    public void setClassCode(ActClinicalDocument value) {
        this.classCode = value;
    }

    /**
     * Gets the value of the moodCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the moodCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMoodCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the moodCode property.
     */
    public List<String> getMoodCode() {
        if (moodCode == null) {
            moodCode = new ArrayList<>();
        }
        return this.moodCode;
    }

}
