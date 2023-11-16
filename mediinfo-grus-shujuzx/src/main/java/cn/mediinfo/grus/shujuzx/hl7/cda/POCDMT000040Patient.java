//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>POCD_MT000040.Patient complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="POCD_MT000040.Patient">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="realmCode" type="{urn:hl7-org:v3}CS" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="typeId" type="{urn:hl7-org:v3}POCD_MT000040.InfrastructureRoot.typeId" minOccurs="0"/>
 *         <element name="templateId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="id" type="{urn:hl7-org:v3}II" minOccurs="0"/>
 *         <element name="name" type="{urn:hl7-org:v3}PN" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="administrativeGenderCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="birthTime" type="{urn:hl7-org:v3}TS" minOccurs="0"/>
 *         <element name="maritalStatusCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="religiousAffiliationCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="raceCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="ethnicGroupCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="guardian" type="{urn:hl7-org:v3}POCD_MT000040.Guardian" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="birthplace" type="{urn:hl7-org:v3}POCD_MT000040.Birthplace" minOccurs="0"/>
 *         <element name="nationality" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         <element name="age" type="{urn:hl7-org:v3}PQ" minOccurs="0"/>
 *         <element name="employerOrganization" type="{urn:hl7-org:v3}PCHIS.Employerorganization" minOccurs="0"/>
 *         <element name="household" type="{urn:hl7-org:v3}PCHIS.Householdregistrationplace" minOccurs="0"/>
 *         <element name="nativePlace" type="{urn:hl7-org:v3}PCHIS.Nativeplace" minOccurs="0"/>
 *         <element name="educationLevel" type="{urn:hl7-org:v3}PCHIS.Educationlevel" minOccurs="0"/>
 *         <element name="occupation" type="{urn:hl7-org:v3}PCHIS.Occupation" minOccurs="0"/>
 *         <element name="languageCommunication" type="{urn:hl7-org:v3}POCD_MT000040.LanguageCommunication" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *       <attribute name="classCode" type="{urn:hl7-org:v3}EntityClass" fixed="PSN" />
 *       <attribute name="determinerCode" type="{urn:hl7-org:v3}EntityDeterminer" fixed="INSTANCE" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "POCD_MT000040.Patient", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "id",
    "name",
    "administrativeGenderCode",
    "birthTime",
    "maritalStatusCode",
    "religiousAffiliationCode",
    "raceCode",
    "ethnicGroupCode",
    "guardian",
    "birthplace",
    "nationality",
    "age",
    "employerOrganization",
    "household",
    "nativePlace",
    "educationLevel",
    "occupation",
    "languageCommunication"
})
public class POCDMT000040Patient {

    protected List<CS> realmCode;
    protected POCDMT000040InfrastructureRootTypeId typeId;
    protected List<II> templateId;
    protected II id;
    protected List<PN> name;
    protected CE administrativeGenderCode;
    protected TS birthTime;
    protected CE maritalStatusCode;
    protected CE religiousAffiliationCode;
    protected CE raceCode;
    protected CE ethnicGroupCode;
    protected List<POCDMT000040Guardian> guardian;
    protected POCDMT000040Birthplace birthplace;
    protected CE nationality;
    protected PQ age;
    protected PCHISEmployerorganization employerOrganization;
    protected PCHISHouseholdregistrationplace household;
    protected PCHISNativeplace nativePlace;
    protected PCHISEducationlevel educationLevel;
    protected PCHISOccupation occupation;
    protected List<POCDMT000040LanguageCommunication> languageCommunication;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;
    @XmlAttribute(name = "classCode")
    protected List<String> classCode;
    @XmlAttribute(name = "determinerCode")
    protected String determinerCode;

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
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PN }
     * 
     * 
     * @return
     *     The value of the name property.
     */
    public List<PN> getName() {
        if (name == null) {
            name = new ArrayList<>();
        }
        return this.name;
    }

    /**
     * 获取administrativeGenderCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getAdministrativeGenderCode() {
        return administrativeGenderCode;
    }

    /**
     * 设置administrativeGenderCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setAdministrativeGenderCode(CE value) {
        this.administrativeGenderCode = value;
    }

    /**
     * 获取birthTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getBirthTime() {
        return birthTime;
    }

    /**
     * 设置birthTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setBirthTime(TS value) {
        this.birthTime = value;
    }

    /**
     * 获取maritalStatusCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getMaritalStatusCode() {
        return maritalStatusCode;
    }

    /**
     * 设置maritalStatusCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setMaritalStatusCode(CE value) {
        this.maritalStatusCode = value;
    }

    /**
     * 获取religiousAffiliationCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getReligiousAffiliationCode() {
        return religiousAffiliationCode;
    }

    /**
     * 设置religiousAffiliationCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setReligiousAffiliationCode(CE value) {
        this.religiousAffiliationCode = value;
    }

    /**
     * 获取raceCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getRaceCode() {
        return raceCode;
    }

    /**
     * 设置raceCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setRaceCode(CE value) {
        this.raceCode = value;
    }

    /**
     * 获取ethnicGroupCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getEthnicGroupCode() {
        return ethnicGroupCode;
    }

    /**
     * 设置ethnicGroupCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setEthnicGroupCode(CE value) {
        this.ethnicGroupCode = value;
    }

    /**
     * Gets the value of the guardian property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the guardian property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuardian().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040Guardian }
     * 
     * 
     * @return
     *     The value of the guardian property.
     */
    public List<POCDMT000040Guardian> getGuardian() {
        if (guardian == null) {
            guardian = new ArrayList<>();
        }
        return this.guardian;
    }

    /**
     * 获取birthplace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link POCDMT000040Birthplace }
     *     
     */
    public POCDMT000040Birthplace getBirthplace() {
        return birthplace;
    }

    /**
     * 设置birthplace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link POCDMT000040Birthplace }
     *     
     */
    public void setBirthplace(POCDMT000040Birthplace value) {
        this.birthplace = value;
    }

    /**
     * 获取nationality属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getNationality() {
        return nationality;
    }

    /**
     * 设置nationality属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setNationality(CE value) {
        this.nationality = value;
    }

    /**
     * 获取age属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PQ }
     *     
     */
    public PQ getAge() {
        return age;
    }

    /**
     * 设置age属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PQ }
     *     
     */
    public void setAge(PQ value) {
        this.age = value;
    }

    /**
     * 获取employerOrganization属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PCHISEmployerorganization }
     *     
     */
    public PCHISEmployerorganization getEmployerOrganization() {
        return employerOrganization;
    }

    /**
     * 设置employerOrganization属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PCHISEmployerorganization }
     *     
     */
    public void setEmployerOrganization(PCHISEmployerorganization value) {
        this.employerOrganization = value;
    }

    /**
     * 获取household属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PCHISHouseholdregistrationplace }
     *     
     */
    public PCHISHouseholdregistrationplace getHousehold() {
        return household;
    }

    /**
     * 设置household属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PCHISHouseholdregistrationplace }
     *     
     */
    public void setHousehold(PCHISHouseholdregistrationplace value) {
        this.household = value;
    }

    /**
     * 获取nativePlace属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PCHISNativeplace }
     *     
     */
    public PCHISNativeplace getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置nativePlace属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PCHISNativeplace }
     *     
     */
    public void setNativePlace(PCHISNativeplace value) {
        this.nativePlace = value;
    }

    /**
     * 获取educationLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PCHISEducationlevel }
     *     
     */
    public PCHISEducationlevel getEducationLevel() {
        return educationLevel;
    }

    /**
     * 设置educationLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PCHISEducationlevel }
     *     
     */
    public void setEducationLevel(PCHISEducationlevel value) {
        this.educationLevel = value;
    }

    /**
     * 获取occupation属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PCHISOccupation }
     *     
     */
    public PCHISOccupation getOccupation() {
        return occupation;
    }

    /**
     * 设置occupation属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PCHISOccupation }
     *     
     */
    public void setOccupation(PCHISOccupation value) {
        this.occupation = value;
    }

    /**
     * Gets the value of the languageCommunication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the languageCommunication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLanguageCommunication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POCDMT000040LanguageCommunication }
     * 
     * 
     * @return
     *     The value of the languageCommunication property.
     */
    public List<POCDMT000040LanguageCommunication> getLanguageCommunication() {
        if (languageCommunication == null) {
            languageCommunication = new ArrayList<>();
        }
        return this.languageCommunication;
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
     * Gets the value of the classCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the classCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the classCode property.
     */
    public List<String> getClassCode() {
        if (classCode == null) {
            classCode = new ArrayList<>();
        }
        return this.classCode;
    }

    /**
     * 获取determinerCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeterminerCode() {
        if (determinerCode == null) {
            return "INSTANCE";
        } else {
            return determinerCode;
        }
    }

    /**
     * 设置determinerCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeterminerCode(String value) {
        this.determinerCode = value;
    }

}
