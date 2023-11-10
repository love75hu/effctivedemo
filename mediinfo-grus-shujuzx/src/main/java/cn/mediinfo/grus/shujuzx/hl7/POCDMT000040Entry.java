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
 * <p>POCD_MT000040.Entry complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="POCD_MT000040.Entry">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         <element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         <choice>
 *           <element name="act" type="{urn:hl7-org:v3}POCD_MT000040.Act"/>
 *           <element name="encounter" type="{urn:hl7-org:v3}POCD_MT000040.Encounter"/>
 *           <element name="observation" type="{urn:hl7-org:v3}POCD_MT000040.Observation"/>
 *           <element name="observationMedia" type="{urn:hl7-org:v3}POCD_MT000040.ObservationMedia"/>
 *           <element name="organizer" type="{urn:hl7-org:v3}POCD_MT000040.Organizer"/>
 *           <element name="procedure" type="{urn:hl7-org:v3}POCD_MT000040.Procedure"/>
 *           <element name="regionOfInterest" type="{urn:hl7-org:v3}POCD_MT000040.RegionOfInterest"/>
 *           <element name="substanceAdministration" type="{urn:hl7-org:v3}POCD_MT000040.SubstanceAdministration"/>
 *           <element name="supply" type="{urn:hl7-org:v3}POCD_MT000040.Supply"/>
 *         </choice>
 *       </sequence>
 *       <attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       <attribute name="typeCode" type="{urn:hl7-org:v3}x_ActRelationshipEntry" default="COMP" />
 *       <attribute name="contextConductionInd" type="{urn:hl7-org:v3}bl" fixed="true" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Entry", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "act",
    "encounter",
    "observation",
    "observationMedia",
    "organizer",
    "procedure",
    "regionOfInterest",
    "substanceAdministration",
    "supply"
})
public class POCDMT000040Entry {

    protected List<CS> realmCode;
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    protected POCDMT000040Act act;
    protected POCDMT000040Encounter encounter;
    protected POCDMT000040Observation observation;
    protected POCDMT000040ObservationMedia observationMedia;
    protected POCDMT000040Organizer organizer;
    protected POCDMT000040Procedure procedure;
    protected POCDMT000040RegionOfInterest regionOfInterest;
    protected POCDMT000040SubstanceAdministration substanceAdministration;
    protected POCDMT000040Supply supply;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "typeCode")
    protected XActRelationshipEntry typeCode;
    @XmlAttribute(name = "contextConductionInd")
    protected Boolean contextConductionInd;

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
     * ��ȡact���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Act }
     *     
     */
    public POCDMT000040Act getAct() {
        return act;
    }

    /**
     * ����act���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Act }
     *     
     */
    public void setAct(POCDMT000040Act value) {
        this.act = value;
    }

    /**
     * ��ȡencounter���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Encounter }
     *     
     */
    public POCDMT000040Encounter getEncounter() {
        return encounter;
    }

    /**
     * ����encounter���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Encounter }
     *     
     */
    public void setEncounter(POCDMT000040Encounter value) {
        this.encounter = value;
    }

    /**
     * ��ȡobservation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Observation }
     *     
     */
    public POCDMT000040Observation getObservation() {
        return observation;
    }

    /**
     * ����observation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Observation }
     *     
     */
    public void setObservation(POCDMT000040Observation value) {
        this.observation = value;
    }

    /**
     * ��ȡobservationMedia���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040ObservationMedia }
     *     
     */
    public POCDMT000040ObservationMedia getObservationMedia() {
        return observationMedia;
    }

    /**
     * ����observationMedia���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040ObservationMedia }
     *     
     */
    public void setObservationMedia(POCDMT000040ObservationMedia value) {
        this.observationMedia = value;
    }

    /**
     * ��ȡorganizer���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Organizer }
     *     
     */
    public POCDMT000040Organizer getOrganizer() {
        return organizer;
    }

    /**
     * ����organizer���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Organizer }
     *     
     */
    public void setOrganizer(POCDMT000040Organizer value) {
        this.organizer = value;
    }

    /**
     * ��ȡprocedure���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Procedure }
     *     
     */
    public POCDMT000040Procedure getProcedure() {
        return procedure;
    }

    /**
     * ����procedure���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Procedure }
     *     
     */
    public void setProcedure(POCDMT000040Procedure value) {
        this.procedure = value;
    }

    /**
     * ��ȡregionOfInterest���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040RegionOfInterest }
     *     
     */
    public POCDMT000040RegionOfInterest getRegionOfInterest() {
        return regionOfInterest;
    }

    /**
     * ����regionOfInterest���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040RegionOfInterest }
     *     
     */
    public void setRegionOfInterest(POCDMT000040RegionOfInterest value) {
        this.regionOfInterest = value;
    }

    /**
     * ��ȡsubstanceAdministration���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040SubstanceAdministration }
     *     
     */
    public POCDMT000040SubstanceAdministration getSubstanceAdministration() {
        return substanceAdministration;
    }

    /**
     * ����substanceAdministration���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040SubstanceAdministration }
     *     
     */
    public void setSubstanceAdministration(POCDMT000040SubstanceAdministration value) {
        this.substanceAdministration = value;
    }

    /**
     * ��ȡsupply���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Supply }
     *     
     */
    public POCDMT000040Supply getSupply() {
        return supply;
    }

    /**
     * ����supply���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Supply }
     *     
     */
    public void setSupply(POCDMT000040Supply value) {
        this.supply = value;
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
     *     {@link XActRelationshipEntry }
     *     
     */
    public XActRelationshipEntry getTypeCode() {
        if (typeCode == null) {
            return XActRelationshipEntry.COMP;
        } else {
            return typeCode;
        }
    }

    /**
     * ����typeCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XActRelationshipEntry }
     *     
     */
    public void setTypeCode(XActRelationshipEntry value) {
        this.typeCode = value;
    }

    /**
     * ��ȡcontextConductionInd���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isContextConductionInd() {
        if (contextConductionInd == null) {
            return true;
        } else {
            return contextConductionInd;
        }
    }

    /**
     * ����contextConductionInd���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setContextConductionInd(Boolean value) {
        this.contextConductionInd = value;
    }

}
