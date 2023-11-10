//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.mediinfo.grus.shujuzx.hl7 package.
 * <p>An ObjectFactory allows you to programmatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _ClinicalDocument_QNAME = new QName("urn:hl7-org:v3", "ClinicalDocument");
    private static final QName _StrucDocThContent_QNAME = new QName("urn:hl7-org:v3", "content");
    private static final QName _StrucDocThLinkHtml_QNAME = new QName("urn:hl7-org:v3", "linkHtml");
    private static final QName _StrucDocThSub_QNAME = new QName("urn:hl7-org:v3", "sub");
    private static final QName _StrucDocThSup_QNAME = new QName("urn:hl7-org:v3", "sup");
    private static final QName _StrucDocThBr_QNAME = new QName("urn:hl7-org:v3", "br");
    private static final QName _StrucDocThFootnote_QNAME = new QName("urn:hl7-org:v3", "footnote");
    private static final QName _StrucDocThFootnoteRef_QNAME = new QName("urn:hl7-org:v3", "footnoteRef");
    private static final QName _StrucDocThRenderMultiMedia_QNAME = new QName("urn:hl7-org:v3", "renderMultiMedia");
    private static final QName _StrucDocTdParagraph_QNAME = new QName("urn:hl7-org:v3", "paragraph");
    private static final QName _StrucDocTdList_QNAME = new QName("urn:hl7-org:v3", "list");
    private static final QName _StrucDocParagraphCaption_QNAME = new QName("urn:hl7-org:v3", "caption");
    private static final QName _StrucDocItemTable_QNAME = new QName("urn:hl7-org:v3", "table");
    private static final QName _IVLPQLow_QNAME = new QName("urn:hl7-org:v3", "low");
    private static final QName _IVLPQWidth_QNAME = new QName("urn:hl7-org:v3", "width");
    private static final QName _IVLPQHigh_QNAME = new QName("urn:hl7-org:v3", "high");
    private static final QName _IVLPQCenter_QNAME = new QName("urn:hl7-org:v3", "center");
    private static final QName _ENDelimiter_QNAME = new QName("urn:hl7-org:v3", "delimiter");
    private static final QName _ENFamily_QNAME = new QName("urn:hl7-org:v3", "family");
    private static final QName _ENGiven_QNAME = new QName("urn:hl7-org:v3", "given");
    private static final QName _ENPrefix_QNAME = new QName("urn:hl7-org:v3", "prefix");
    private static final QName _ENSuffix_QNAME = new QName("urn:hl7-org:v3", "suffix");
    private static final QName _ENValidTime_QNAME = new QName("urn:hl7-org:v3", "validTime");
    private static final QName _ADCountry_QNAME = new QName("urn:hl7-org:v3", "country");
    private static final QName _ADState_QNAME = new QName("urn:hl7-org:v3", "state");
    private static final QName _ADCounty_QNAME = new QName("urn:hl7-org:v3", "county");
    private static final QName _ADCity_QNAME = new QName("urn:hl7-org:v3", "city");
    private static final QName _ADTownship_QNAME = new QName("urn:hl7-org:v3", "township");
    private static final QName _ADPostalCode_QNAME = new QName("urn:hl7-org:v3", "postalCode");
    private static final QName _ADStreetAddressLine_QNAME = new QName("urn:hl7-org:v3", "streetAddressLine");
    private static final QName _ADHouseNumber_QNAME = new QName("urn:hl7-org:v3", "houseNumber");
    private static final QName _ADHouseNumberNumeric_QNAME = new QName("urn:hl7-org:v3", "houseNumberNumeric");
    private static final QName _ADDirection_QNAME = new QName("urn:hl7-org:v3", "direction");
    private static final QName _ADStreetName_QNAME = new QName("urn:hl7-org:v3", "streetName");
    private static final QName _ADStreetNameBase_QNAME = new QName("urn:hl7-org:v3", "streetNameBase");
    private static final QName _ADStreetNameType_QNAME = new QName("urn:hl7-org:v3", "streetNameType");
    private static final QName _ADAdditionalLocator_QNAME = new QName("urn:hl7-org:v3", "additionalLocator");
    private static final QName _ADUnitID_QNAME = new QName("urn:hl7-org:v3", "unitID");
    private static final QName _ADUnitType_QNAME = new QName("urn:hl7-org:v3", "unitType");
    private static final QName _ADCareOf_QNAME = new QName("urn:hl7-org:v3", "careOf");
    private static final QName _ADCensusTract_QNAME = new QName("urn:hl7-org:v3", "censusTract");
    private static final QName _ADDeliveryAddressLine_QNAME = new QName("urn:hl7-org:v3", "deliveryAddressLine");
    private static final QName _ADDeliveryInstallationType_QNAME = new QName("urn:hl7-org:v3", "deliveryInstallationType");
    private static final QName _ADDeliveryInstallationArea_QNAME = new QName("urn:hl7-org:v3", "deliveryInstallationArea");
    private static final QName _ADDeliveryInstallationQualifier_QNAME = new QName("urn:hl7-org:v3", "deliveryInstallationQualifier");
    private static final QName _ADDeliveryMode_QNAME = new QName("urn:hl7-org:v3", "deliveryMode");
    private static final QName _ADDeliveryModeIdentifier_QNAME = new QName("urn:hl7-org:v3", "deliveryModeIdentifier");
    private static final QName _ADBuildingNumberSuffix_QNAME = new QName("urn:hl7-org:v3", "buildingNumberSuffix");
    private static final QName _ADPostBox_QNAME = new QName("urn:hl7-org:v3", "postBox");
    private static final QName _ADPrecinct_QNAME = new QName("urn:hl7-org:v3", "precinct");
    private static final QName _ADUseablePeriod_QNAME = new QName("urn:hl7-org:v3", "useablePeriod");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.mediinfo.grus.shujuzx.hl7
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link POCDMT000040ClinicalDocument }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ClinicalDocument }
     */
    public POCDMT000040ClinicalDocument createPOCDMT000040ClinicalDocument() {
        return new POCDMT000040ClinicalDocument();
    }

    /**
     * Create an instance of {@link BL }
     * 
     * @return
     *     the new instance of {@link BL }
     */
    public BL createBL() {
        return new BL();
    }

    /**
     * Create an instance of {@link ANYNonNull }
     * 
     * @return
     *     the new instance of {@link ANYNonNull }
     */
    public ANYNonNull createANYNonNull() {
        return new ANYNonNull();
    }

    /**
     * Create an instance of {@link BN }
     * 
     * @return
     *     the new instance of {@link BN }
     */
    public BN createBN() {
        return new BN();
    }

    /**
     * Create an instance of {@link ED }
     * 
     * @return
     *     the new instance of {@link ED }
     */
    public ED createED() {
        return new ED();
    }

    /**
     * Create an instance of {@link Thumbnail }
     * 
     * @return
     *     the new instance of {@link Thumbnail }
     */
    public Thumbnail createThumbnail() {
        return new Thumbnail();
    }

    /**
     * Create an instance of {@link ST }
     * 
     * @return
     *     the new instance of {@link ST }
     */
    public ST createST() {
        return new ST();
    }

    /**
     * Create an instance of {@link CD }
     * 
     * @return
     *     the new instance of {@link CD }
     */
    public CD createCD() {
        return new CD();
    }

    /**
     * Create an instance of {@link CE }
     * 
     * @return
     *     the new instance of {@link CE }
     */
    public CE createCE() {
        return new CE();
    }

    /**
     * Create an instance of {@link CV }
     * 
     * @return
     *     the new instance of {@link CV }
     */
    public CV createCV() {
        return new CV();
    }

    /**
     * Create an instance of {@link CS }
     * 
     * @return
     *     the new instance of {@link CS }
     */
    public CS createCS() {
        return new CS();
    }

    /**
     * Create an instance of {@link CO }
     * 
     * @return
     *     the new instance of {@link CO }
     */
    public CO createCO() {
        return new CO();
    }

    /**
     * Create an instance of {@link CR }
     * 
     * @return
     *     the new instance of {@link CR }
     */
    public CR createCR() {
        return new CR();
    }

    /**
     * Create an instance of {@link SC }
     * 
     * @return
     *     the new instance of {@link SC }
     */
    public SC createSC() {
        return new SC();
    }

    /**
     * Create an instance of {@link II }
     * 
     * @return
     *     the new instance of {@link II }
     */
    public II createII() {
        return new II();
    }

    /**
     * Create an instance of {@link TS }
     * 
     * @return
     *     the new instance of {@link TS }
     */
    public TS createTS() {
        return new TS();
    }

    /**
     * Create an instance of {@link TEL }
     * 
     * @return
     *     the new instance of {@link TEL }
     */
    public TEL createTEL() {
        return new TEL();
    }

    /**
     * Create an instance of {@link ADXP }
     * 
     * @return
     *     the new instance of {@link ADXP }
     */
    public ADXP createADXP() {
        return new ADXP();
    }

    /**
     * Create an instance of {@link AdxpDelimiter }
     * 
     * @return
     *     the new instance of {@link AdxpDelimiter }
     */
    public AdxpDelimiter createAdxpDelimiter() {
        return new AdxpDelimiter();
    }

    /**
     * Create an instance of {@link AdxpCountry }
     * 
     * @return
     *     the new instance of {@link AdxpCountry }
     */
    public AdxpCountry createAdxpCountry() {
        return new AdxpCountry();
    }

    /**
     * Create an instance of {@link AdxpState }
     * 
     * @return
     *     the new instance of {@link AdxpState }
     */
    public AdxpState createAdxpState() {
        return new AdxpState();
    }

    /**
     * Create an instance of {@link AdxpCounty }
     * 
     * @return
     *     the new instance of {@link AdxpCounty }
     */
    public AdxpCounty createAdxpCounty() {
        return new AdxpCounty();
    }

    /**
     * Create an instance of {@link AdxpCity }
     * 
     * @return
     *     the new instance of {@link AdxpCity }
     */
    public AdxpCity createAdxpCity() {
        return new AdxpCity();
    }

    /**
     * Create an instance of {@link AdxpTownship }
     * 
     * @return
     *     the new instance of {@link AdxpTownship }
     */
    public AdxpTownship createAdxpTownship() {
        return new AdxpTownship();
    }

    /**
     * Create an instance of {@link AdxpPostalCode }
     * 
     * @return
     *     the new instance of {@link AdxpPostalCode }
     */
    public AdxpPostalCode createAdxpPostalCode() {
        return new AdxpPostalCode();
    }

    /**
     * Create an instance of {@link AdxpStreetAddressLine }
     * 
     * @return
     *     the new instance of {@link AdxpStreetAddressLine }
     */
    public AdxpStreetAddressLine createAdxpStreetAddressLine() {
        return new AdxpStreetAddressLine();
    }

    /**
     * Create an instance of {@link AdxpHouseNumber }
     * 
     * @return
     *     the new instance of {@link AdxpHouseNumber }
     */
    public AdxpHouseNumber createAdxpHouseNumber() {
        return new AdxpHouseNumber();
    }

    /**
     * Create an instance of {@link AdxpHouseNumberNumeric }
     * 
     * @return
     *     the new instance of {@link AdxpHouseNumberNumeric }
     */
    public AdxpHouseNumberNumeric createAdxpHouseNumberNumeric() {
        return new AdxpHouseNumberNumeric();
    }

    /**
     * Create an instance of {@link AdxpDirection }
     * 
     * @return
     *     the new instance of {@link AdxpDirection }
     */
    public AdxpDirection createAdxpDirection() {
        return new AdxpDirection();
    }

    /**
     * Create an instance of {@link AdxpStreetName }
     * 
     * @return
     *     the new instance of {@link AdxpStreetName }
     */
    public AdxpStreetName createAdxpStreetName() {
        return new AdxpStreetName();
    }

    /**
     * Create an instance of {@link AdxpStreetNameBase }
     * 
     * @return
     *     the new instance of {@link AdxpStreetNameBase }
     */
    public AdxpStreetNameBase createAdxpStreetNameBase() {
        return new AdxpStreetNameBase();
    }

    /**
     * Create an instance of {@link AdxpStreetNameType }
     * 
     * @return
     *     the new instance of {@link AdxpStreetNameType }
     */
    public AdxpStreetNameType createAdxpStreetNameType() {
        return new AdxpStreetNameType();
    }

    /**
     * Create an instance of {@link AdxpAdditionalLocator }
     * 
     * @return
     *     the new instance of {@link AdxpAdditionalLocator }
     */
    public AdxpAdditionalLocator createAdxpAdditionalLocator() {
        return new AdxpAdditionalLocator();
    }

    /**
     * Create an instance of {@link AdxpUnitID }
     * 
     * @return
     *     the new instance of {@link AdxpUnitID }
     */
    public AdxpUnitID createAdxpUnitID() {
        return new AdxpUnitID();
    }

    /**
     * Create an instance of {@link AdxpUnitType }
     * 
     * @return
     *     the new instance of {@link AdxpUnitType }
     */
    public AdxpUnitType createAdxpUnitType() {
        return new AdxpUnitType();
    }

    /**
     * Create an instance of {@link AdxpCareOf }
     * 
     * @return
     *     the new instance of {@link AdxpCareOf }
     */
    public AdxpCareOf createAdxpCareOf() {
        return new AdxpCareOf();
    }

    /**
     * Create an instance of {@link AdxpCensusTract }
     * 
     * @return
     *     the new instance of {@link AdxpCensusTract }
     */
    public AdxpCensusTract createAdxpCensusTract() {
        return new AdxpCensusTract();
    }

    /**
     * Create an instance of {@link AdxpDeliveryAddressLine }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryAddressLine }
     */
    public AdxpDeliveryAddressLine createAdxpDeliveryAddressLine() {
        return new AdxpDeliveryAddressLine();
    }

    /**
     * Create an instance of {@link AdxpDeliveryInstallationType }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryInstallationType }
     */
    public AdxpDeliveryInstallationType createAdxpDeliveryInstallationType() {
        return new AdxpDeliveryInstallationType();
    }

    /**
     * Create an instance of {@link AdxpDeliveryInstallationArea }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryInstallationArea }
     */
    public AdxpDeliveryInstallationArea createAdxpDeliveryInstallationArea() {
        return new AdxpDeliveryInstallationArea();
    }

    /**
     * Create an instance of {@link AdxpDeliveryInstallationQualifier }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryInstallationQualifier }
     */
    public AdxpDeliveryInstallationQualifier createAdxpDeliveryInstallationQualifier() {
        return new AdxpDeliveryInstallationQualifier();
    }

    /**
     * Create an instance of {@link AdxpDeliveryMode }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryMode }
     */
    public AdxpDeliveryMode createAdxpDeliveryMode() {
        return new AdxpDeliveryMode();
    }

    /**
     * Create an instance of {@link AdxpDeliveryModeIdentifier }
     * 
     * @return
     *     the new instance of {@link AdxpDeliveryModeIdentifier }
     */
    public AdxpDeliveryModeIdentifier createAdxpDeliveryModeIdentifier() {
        return new AdxpDeliveryModeIdentifier();
    }

    /**
     * Create an instance of {@link AdxpBuildingNumberSuffix }
     * 
     * @return
     *     the new instance of {@link AdxpBuildingNumberSuffix }
     */
    public AdxpBuildingNumberSuffix createAdxpBuildingNumberSuffix() {
        return new AdxpBuildingNumberSuffix();
    }

    /**
     * Create an instance of {@link AdxpPostBox }
     * 
     * @return
     *     the new instance of {@link AdxpPostBox }
     */
    public AdxpPostBox createAdxpPostBox() {
        return new AdxpPostBox();
    }

    /**
     * Create an instance of {@link AdxpPrecinct }
     * 
     * @return
     *     the new instance of {@link AdxpPrecinct }
     */
    public AdxpPrecinct createAdxpPrecinct() {
        return new AdxpPrecinct();
    }

    /**
     * Create an instance of {@link AD }
     * 
     * @return
     *     the new instance of {@link AD }
     */
    public AD createAD() {
        return new AD();
    }

    /**
     * Create an instance of {@link ENXP }
     * 
     * @return
     *     the new instance of {@link ENXP }
     */
    public ENXP createENXP() {
        return new ENXP();
    }

    /**
     * Create an instance of {@link EnDelimiter }
     * 
     * @return
     *     the new instance of {@link EnDelimiter }
     */
    public EnDelimiter createEnDelimiter() {
        return new EnDelimiter();
    }

    /**
     * Create an instance of {@link EnFamily }
     * 
     * @return
     *     the new instance of {@link EnFamily }
     */
    public EnFamily createEnFamily() {
        return new EnFamily();
    }

    /**
     * Create an instance of {@link EnGiven }
     * 
     * @return
     *     the new instance of {@link EnGiven }
     */
    public EnGiven createEnGiven() {
        return new EnGiven();
    }

    /**
     * Create an instance of {@link EnPrefix }
     * 
     * @return
     *     the new instance of {@link EnPrefix }
     */
    public EnPrefix createEnPrefix() {
        return new EnPrefix();
    }

    /**
     * Create an instance of {@link EnSuffix }
     * 
     * @return
     *     the new instance of {@link EnSuffix }
     */
    public EnSuffix createEnSuffix() {
        return new EnSuffix();
    }

    /**
     * Create an instance of {@link EN }
     * 
     * @return
     *     the new instance of {@link EN }
     */
    public EN createEN() {
        return new EN();
    }

    /**
     * Create an instance of {@link PN }
     * 
     * @return
     *     the new instance of {@link PN }
     */
    public PN createPN() {
        return new PN();
    }

    /**
     * Create an instance of {@link ON }
     * 
     * @return
     *     the new instance of {@link ON }
     */
    public ON createON() {
        return new ON();
    }

    /**
     * Create an instance of {@link TN }
     * 
     * @return
     *     the new instance of {@link TN }
     */
    public TN createTN() {
        return new TN();
    }

    /**
     * Create an instance of {@link INT }
     * 
     * @return
     *     the new instance of {@link INT }
     */
    public INT createINT() {
        return new INT();
    }

    /**
     * Create an instance of {@link REAL }
     * 
     * @return
     *     the new instance of {@link REAL }
     */
    public REAL createREAL() {
        return new REAL();
    }

    /**
     * Create an instance of {@link PQR }
     * 
     * @return
     *     the new instance of {@link PQR }
     */
    public PQR createPQR() {
        return new PQR();
    }

    /**
     * Create an instance of {@link PQ }
     * 
     * @return
     *     the new instance of {@link PQ }
     */
    public PQ createPQ() {
        return new PQ();
    }

    /**
     * Create an instance of {@link MO }
     * 
     * @return
     *     the new instance of {@link MO }
     */
    public MO createMO() {
        return new MO();
    }

    /**
     * Create an instance of {@link RTO }
     * 
     * @return
     *     the new instance of {@link RTO }
     */
    public RTO createRTO() {
        return new RTO();
    }

    /**
     * Create an instance of {@link EIVLEvent }
     * 
     * @return
     *     the new instance of {@link EIVLEvent }
     */
    public EIVLEvent createEIVLEvent() {
        return new EIVLEvent();
    }

    /**
     * Create an instance of {@link SXCMTS }
     * 
     * @return
     *     the new instance of {@link SXCMTS }
     */
    public SXCMTS createSXCMTS() {
        return new SXCMTS();
    }

    /**
     * Create an instance of {@link IVLTS }
     * 
     * @return
     *     the new instance of {@link IVLTS }
     */
    public IVLTS createIVLTS() {
        return new IVLTS();
    }

    /**
     * Create an instance of {@link IVXBTS }
     * 
     * @return
     *     the new instance of {@link IVXBTS }
     */
    public IVXBTS createIVXBTS() {
        return new IVXBTS();
    }

    /**
     * Create an instance of {@link RTOQTYQTY }
     * 
     * @return
     *     the new instance of {@link RTOQTYQTY }
     */
    public RTOQTYQTY createRTOQTYQTY() {
        return new RTOQTYQTY();
    }

    /**
     * Create an instance of {@link PIVLTS }
     * 
     * @return
     *     the new instance of {@link PIVLTS }
     */
    public PIVLTS createPIVLTS() {
        return new PIVLTS();
    }

    /**
     * Create an instance of {@link EIVLTS }
     * 
     * @return
     *     the new instance of {@link EIVLTS }
     */
    public EIVLTS createEIVLTS() {
        return new EIVLTS();
    }

    /**
     * Create an instance of {@link IVLPQ }
     * 
     * @return
     *     the new instance of {@link IVLPQ }
     */
    public IVLPQ createIVLPQ() {
        return new IVLPQ();
    }

    /**
     * Create an instance of {@link SXCMPQ }
     * 
     * @return
     *     the new instance of {@link SXCMPQ }
     */
    public SXCMPQ createSXCMPQ() {
        return new SXCMPQ();
    }

    /**
     * Create an instance of {@link IVXBPQ }
     * 
     * @return
     *     the new instance of {@link IVXBPQ }
     */
    public IVXBPQ createIVXBPQ() {
        return new IVXBPQ();
    }

    /**
     * Create an instance of {@link PPDTS }
     * 
     * @return
     *     the new instance of {@link PPDTS }
     */
    public PPDTS createPPDTS() {
        return new PPDTS();
    }

    /**
     * Create an instance of {@link PPDPQ }
     * 
     * @return
     *     the new instance of {@link PPDPQ }
     */
    public PPDPQ createPPDPQ() {
        return new PPDPQ();
    }

    /**
     * Create an instance of {@link PIVLPPDTS }
     * 
     * @return
     *     the new instance of {@link PIVLPPDTS }
     */
    public PIVLPPDTS createPIVLPPDTS() {
        return new PIVLPPDTS();
    }

    /**
     * Create an instance of {@link SXCMPPDTS }
     * 
     * @return
     *     the new instance of {@link SXCMPPDTS }
     */
    public SXCMPPDTS createSXCMPPDTS() {
        return new SXCMPPDTS();
    }

    /**
     * Create an instance of {@link IVLPPDTS }
     * 
     * @return
     *     the new instance of {@link IVLPPDTS }
     */
    public IVLPPDTS createIVLPPDTS() {
        return new IVLPPDTS();
    }

    /**
     * Create an instance of {@link IVXBPPDTS }
     * 
     * @return
     *     the new instance of {@link IVXBPPDTS }
     */
    public IVXBPPDTS createIVXBPPDTS() {
        return new IVXBPPDTS();
    }

    /**
     * Create an instance of {@link EIVLPPDTS }
     * 
     * @return
     *     the new instance of {@link EIVLPPDTS }
     */
    public EIVLPPDTS createEIVLPPDTS() {
        return new EIVLPPDTS();
    }

    /**
     * Create an instance of {@link IVLPPDPQ }
     * 
     * @return
     *     the new instance of {@link IVLPPDPQ }
     */
    public IVLPPDPQ createIVLPPDPQ() {
        return new IVLPPDPQ();
    }

    /**
     * Create an instance of {@link SXCMPPDPQ }
     * 
     * @return
     *     the new instance of {@link SXCMPPDPQ }
     */
    public SXCMPPDPQ createSXCMPPDPQ() {
        return new SXCMPPDPQ();
    }

    /**
     * Create an instance of {@link IVXBPPDPQ }
     * 
     * @return
     *     the new instance of {@link IVXBPPDPQ }
     */
    public IVXBPPDPQ createIVXBPPDPQ() {
        return new IVXBPPDPQ();
    }

    /**
     * Create an instance of {@link SXPRTS }
     * 
     * @return
     *     the new instance of {@link SXPRTS }
     */
    public SXPRTS createSXPRTS() {
        return new SXPRTS();
    }

    /**
     * Create an instance of {@link SXCMCD }
     * 
     * @return
     *     the new instance of {@link SXCMCD }
     */
    public SXCMCD createSXCMCD() {
        return new SXCMCD();
    }

    /**
     * Create an instance of {@link SXCMMO }
     * 
     * @return
     *     the new instance of {@link SXCMMO }
     */
    public SXCMMO createSXCMMO() {
        return new SXCMMO();
    }

    /**
     * Create an instance of {@link SXCMINT }
     * 
     * @return
     *     the new instance of {@link SXCMINT }
     */
    public SXCMINT createSXCMINT() {
        return new SXCMINT();
    }

    /**
     * Create an instance of {@link SXCMREAL }
     * 
     * @return
     *     the new instance of {@link SXCMREAL }
     */
    public SXCMREAL createSXCMREAL() {
        return new SXCMREAL();
    }

    /**
     * Create an instance of {@link IVLINT }
     * 
     * @return
     *     the new instance of {@link IVLINT }
     */
    public IVLINT createIVLINT() {
        return new IVLINT();
    }

    /**
     * Create an instance of {@link IVXBINT }
     * 
     * @return
     *     the new instance of {@link IVXBINT }
     */
    public IVXBINT createIVXBINT() {
        return new IVXBINT();
    }

    /**
     * Create an instance of {@link IVLREAL }
     * 
     * @return
     *     the new instance of {@link IVLREAL }
     */
    public IVLREAL createIVLREAL() {
        return new IVLREAL();
    }

    /**
     * Create an instance of {@link IVXBREAL }
     * 
     * @return
     *     the new instance of {@link IVXBREAL }
     */
    public IVXBREAL createIVXBREAL() {
        return new IVXBREAL();
    }

    /**
     * Create an instance of {@link IVLMO }
     * 
     * @return
     *     the new instance of {@link IVLMO }
     */
    public IVLMO createIVLMO() {
        return new IVLMO();
    }

    /**
     * Create an instance of {@link IVXBMO }
     * 
     * @return
     *     the new instance of {@link IVXBMO }
     */
    public IVXBMO createIVXBMO() {
        return new IVXBMO();
    }

    /**
     * Create an instance of {@link HXITPQ }
     * 
     * @return
     *     the new instance of {@link HXITPQ }
     */
    public HXITPQ createHXITPQ() {
        return new HXITPQ();
    }

    /**
     * Create an instance of {@link HXITCE }
     * 
     * @return
     *     the new instance of {@link HXITCE }
     */
    public HXITCE createHXITCE() {
        return new HXITCE();
    }

    /**
     * Create an instance of {@link BXITCD }
     * 
     * @return
     *     the new instance of {@link BXITCD }
     */
    public BXITCD createBXITCD() {
        return new BXITCD();
    }

    /**
     * Create an instance of {@link BXITIVLPQ }
     * 
     * @return
     *     the new instance of {@link BXITIVLPQ }
     */
    public BXITIVLPQ createBXITIVLPQ() {
        return new BXITIVLPQ();
    }

    /**
     * Create an instance of {@link SLISTPQ }
     * 
     * @return
     *     the new instance of {@link SLISTPQ }
     */
    public SLISTPQ createSLISTPQ() {
        return new SLISTPQ();
    }

    /**
     * Create an instance of {@link SLISTTS }
     * 
     * @return
     *     the new instance of {@link SLISTTS }
     */
    public SLISTTS createSLISTTS() {
        return new SLISTTS();
    }

    /**
     * Create an instance of {@link GLISTTS }
     * 
     * @return
     *     the new instance of {@link GLISTTS }
     */
    public GLISTTS createGLISTTS() {
        return new GLISTTS();
    }

    /**
     * Create an instance of {@link GLISTPQ }
     * 
     * @return
     *     the new instance of {@link GLISTPQ }
     */
    public GLISTPQ createGLISTPQ() {
        return new GLISTPQ();
    }

    /**
     * Create an instance of {@link RTOPQPQ }
     * 
     * @return
     *     the new instance of {@link RTOPQPQ }
     */
    public RTOPQPQ createRTOPQPQ() {
        return new RTOPQPQ();
    }

    /**
     * Create an instance of {@link RTOMOPQ }
     * 
     * @return
     *     the new instance of {@link RTOMOPQ }
     */
    public RTOMOPQ createRTOMOPQ() {
        return new RTOMOPQ();
    }

    /**
     * Create an instance of {@link UVPTS }
     * 
     * @return
     *     the new instance of {@link UVPTS }
     */
    public UVPTS createUVPTS() {
        return new UVPTS();
    }

    /**
     * Create an instance of {@link StrucDocText }
     * 
     * @return
     *     the new instance of {@link StrucDocText }
     */
    public StrucDocText createStrucDocText() {
        return new StrucDocText();
    }

    /**
     * Create an instance of {@link StrucDocTitle }
     * 
     * @return
     *     the new instance of {@link StrucDocTitle }
     */
    public StrucDocTitle createStrucDocTitle() {
        return new StrucDocTitle();
    }

    /**
     * Create an instance of {@link StrucDocBr }
     * 
     * @return
     *     the new instance of {@link StrucDocBr }
     */
    public StrucDocBr createStrucDocBr() {
        return new StrucDocBr();
    }

    /**
     * Create an instance of {@link StrucDocCaption }
     * 
     * @return
     *     the new instance of {@link StrucDocCaption }
     */
    public StrucDocCaption createStrucDocCaption() {
        return new StrucDocCaption();
    }

    /**
     * Create an instance of {@link StrucDocCol }
     * 
     * @return
     *     the new instance of {@link StrucDocCol }
     */
    public StrucDocCol createStrucDocCol() {
        return new StrucDocCol();
    }

    /**
     * Create an instance of {@link StrucDocColgroup }
     * 
     * @return
     *     the new instance of {@link StrucDocColgroup }
     */
    public StrucDocColgroup createStrucDocColgroup() {
        return new StrucDocColgroup();
    }

    /**
     * Create an instance of {@link StrucDocContent }
     * 
     * @return
     *     the new instance of {@link StrucDocContent }
     */
    public StrucDocContent createStrucDocContent() {
        return new StrucDocContent();
    }

    /**
     * Create an instance of {@link StrucDocTitleContent }
     * 
     * @return
     *     the new instance of {@link StrucDocTitleContent }
     */
    public StrucDocTitleContent createStrucDocTitleContent() {
        return new StrucDocTitleContent();
    }

    /**
     * Create an instance of {@link StrucDocFootnote }
     * 
     * @return
     *     the new instance of {@link StrucDocFootnote }
     */
    public StrucDocFootnote createStrucDocFootnote() {
        return new StrucDocFootnote();
    }

    /**
     * Create an instance of {@link StrucDocTitleFootnote }
     * 
     * @return
     *     the new instance of {@link StrucDocTitleFootnote }
     */
    public StrucDocTitleFootnote createStrucDocTitleFootnote() {
        return new StrucDocTitleFootnote();
    }

    /**
     * Create an instance of {@link StrucDocFootnoteRef }
     * 
     * @return
     *     the new instance of {@link StrucDocFootnoteRef }
     */
    public StrucDocFootnoteRef createStrucDocFootnoteRef() {
        return new StrucDocFootnoteRef();
    }

    /**
     * Create an instance of {@link StrucDocItem }
     * 
     * @return
     *     the new instance of {@link StrucDocItem }
     */
    public StrucDocItem createStrucDocItem() {
        return new StrucDocItem();
    }

    /**
     * Create an instance of {@link StrucDocLinkHtml }
     * 
     * @return
     *     the new instance of {@link StrucDocLinkHtml }
     */
    public StrucDocLinkHtml createStrucDocLinkHtml() {
        return new StrucDocLinkHtml();
    }

    /**
     * Create an instance of {@link StrucDocList }
     * 
     * @return
     *     the new instance of {@link StrucDocList }
     */
    public StrucDocList createStrucDocList() {
        return new StrucDocList();
    }

    /**
     * Create an instance of {@link StrucDocParagraph }
     * 
     * @return
     *     the new instance of {@link StrucDocParagraph }
     */
    public StrucDocParagraph createStrucDocParagraph() {
        return new StrucDocParagraph();
    }

    /**
     * Create an instance of {@link StrucDocRenderMultiMedia }
     * 
     * @return
     *     the new instance of {@link StrucDocRenderMultiMedia }
     */
    public StrucDocRenderMultiMedia createStrucDocRenderMultiMedia() {
        return new StrucDocRenderMultiMedia();
    }

    /**
     * Create an instance of {@link StrucDocSub }
     * 
     * @return
     *     the new instance of {@link StrucDocSub }
     */
    public StrucDocSub createStrucDocSub() {
        return new StrucDocSub();
    }

    /**
     * Create an instance of {@link StrucDocSup }
     * 
     * @return
     *     the new instance of {@link StrucDocSup }
     */
    public StrucDocSup createStrucDocSup() {
        return new StrucDocSup();
    }

    /**
     * Create an instance of {@link StrucDocTable }
     * 
     * @return
     *     the new instance of {@link StrucDocTable }
     */
    public StrucDocTable createStrucDocTable() {
        return new StrucDocTable();
    }

    /**
     * Create an instance of {@link StrucDocTbody }
     * 
     * @return
     *     the new instance of {@link StrucDocTbody }
     */
    public StrucDocTbody createStrucDocTbody() {
        return new StrucDocTbody();
    }

    /**
     * Create an instance of {@link StrucDocTd }
     * 
     * @return
     *     the new instance of {@link StrucDocTd }
     */
    public StrucDocTd createStrucDocTd() {
        return new StrucDocTd();
    }

    /**
     * Create an instance of {@link StrucDocTfoot }
     * 
     * @return
     *     the new instance of {@link StrucDocTfoot }
     */
    public StrucDocTfoot createStrucDocTfoot() {
        return new StrucDocTfoot();
    }

    /**
     * Create an instance of {@link StrucDocTh }
     * 
     * @return
     *     the new instance of {@link StrucDocTh }
     */
    public StrucDocTh createStrucDocTh() {
        return new StrucDocTh();
    }

    /**
     * Create an instance of {@link StrucDocThead }
     * 
     * @return
     *     the new instance of {@link StrucDocThead }
     */
    public StrucDocThead createStrucDocThead() {
        return new StrucDocThead();
    }

    /**
     * Create an instance of {@link StrucDocTr }
     * 
     * @return
     *     the new instance of {@link StrucDocTr }
     */
    public StrucDocTr createStrucDocTr() {
        return new StrucDocTr();
    }

    /**
     * Create an instance of {@link PCHISPatienttype }
     * 
     * @return
     *     the new instance of {@link PCHISPatienttype }
     */
    public PCHISPatienttype createPCHISPatienttype() {
        return new PCHISPatienttype();
    }

    /**
     * Create an instance of {@link PCHISOccupation }
     * 
     * @return
     *     the new instance of {@link PCHISOccupation }
     */
    public PCHISOccupation createPCHISOccupation() {
        return new PCHISOccupation();
    }

    /**
     * Create an instance of {@link PCHISEducationlevel }
     * 
     * @return
     *     the new instance of {@link PCHISEducationlevel }
     */
    public PCHISEducationlevel createPCHISEducationlevel() {
        return new PCHISEducationlevel();
    }

    /**
     * Create an instance of {@link PCHISHouseholdregistrationplace }
     * 
     * @return
     *     the new instance of {@link PCHISHouseholdregistrationplace }
     */
    public PCHISHouseholdregistrationplace createPCHISHouseholdregistrationplace() {
        return new PCHISHouseholdregistrationplace();
    }

    /**
     * Create an instance of {@link PCHISNativeplace }
     * 
     * @return
     *     the new instance of {@link PCHISNativeplace }
     */
    public PCHISNativeplace createPCHISNativeplace() {
        return new PCHISNativeplace();
    }

    /**
     * Create an instance of {@link PCHISEmployerorganization }
     * 
     * @return
     *     the new instance of {@link PCHISEmployerorganization }
     */
    public PCHISEmployerorganization createPCHISEmployerorganization() {
        return new PCHISEmployerorganization();
    }

    /**
     * Create an instance of {@link PCHISProfessionaltechnicalposition }
     * 
     * @return
     *     the new instance of {@link PCHISProfessionaltechnicalposition }
     */
    public PCHISProfessionaltechnicalposition createPCHISProfessionaltechnicalposition() {
        return new PCHISProfessionaltechnicalposition();
    }

    /**
     * Create an instance of {@link POCDMT000040InfrastructureRootTypeId }
     * 
     * @return
     *     the new instance of {@link POCDMT000040InfrastructureRootTypeId }
     */
    public POCDMT000040InfrastructureRootTypeId createPOCDMT000040InfrastructureRootTypeId() {
        return new POCDMT000040InfrastructureRootTypeId();
    }

    /**
     * Create an instance of {@link POCDMT000040Act }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Act }
     */
    public POCDMT000040Act createPOCDMT000040Act() {
        return new POCDMT000040Act();
    }

    /**
     * Create an instance of {@link POCDMT000040AssignedAuthor }
     * 
     * @return
     *     the new instance of {@link POCDMT000040AssignedAuthor }
     */
    public POCDMT000040AssignedAuthor createPOCDMT000040AssignedAuthor() {
        return new POCDMT000040AssignedAuthor();
    }

    /**
     * Create an instance of {@link POCDMT000040AssignedCustodian }
     * 
     * @return
     *     the new instance of {@link POCDMT000040AssignedCustodian }
     */
    public POCDMT000040AssignedCustodian createPOCDMT000040AssignedCustodian() {
        return new POCDMT000040AssignedCustodian();
    }

    /**
     * Create an instance of {@link POCDMT000040AssignedEntity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040AssignedEntity }
     */
    public POCDMT000040AssignedEntity createPOCDMT000040AssignedEntity() {
        return new POCDMT000040AssignedEntity();
    }

    /**
     * Create an instance of {@link POCDMT000040AssociatedEntity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040AssociatedEntity }
     */
    public POCDMT000040AssociatedEntity createPOCDMT000040AssociatedEntity() {
        return new POCDMT000040AssociatedEntity();
    }

    /**
     * Create an instance of {@link POCDMT000040Authenticator }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Authenticator }
     */
    public POCDMT000040Authenticator createPOCDMT000040Authenticator() {
        return new POCDMT000040Authenticator();
    }

    /**
     * Create an instance of {@link POCDMT000040Author }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Author }
     */
    public POCDMT000040Author createPOCDMT000040Author() {
        return new POCDMT000040Author();
    }

    /**
     * Create an instance of {@link POCDMT000040AuthoringDevice }
     * 
     * @return
     *     the new instance of {@link POCDMT000040AuthoringDevice }
     */
    public POCDMT000040AuthoringDevice createPOCDMT000040AuthoringDevice() {
        return new POCDMT000040AuthoringDevice();
    }

    /**
     * Create an instance of {@link POCDMT000040Authorization }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Authorization }
     */
    public POCDMT000040Authorization createPOCDMT000040Authorization() {
        return new POCDMT000040Authorization();
    }

    /**
     * Create an instance of {@link POCDMT000040Birthplace }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Birthplace }
     */
    public POCDMT000040Birthplace createPOCDMT000040Birthplace() {
        return new POCDMT000040Birthplace();
    }

    /**
     * Create an instance of {@link POCDMT000040Component1 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Component1 }
     */
    public POCDMT000040Component1 createPOCDMT000040Component1() {
        return new POCDMT000040Component1();
    }

    /**
     * Create an instance of {@link POCDMT000040Component2 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Component2 }
     */
    public POCDMT000040Component2 createPOCDMT000040Component2() {
        return new POCDMT000040Component2();
    }

    /**
     * Create an instance of {@link POCDMT000040Component3 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Component3 }
     */
    public POCDMT000040Component3 createPOCDMT000040Component3() {
        return new POCDMT000040Component3();
    }

    /**
     * Create an instance of {@link POCDMT000040Component4 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Component4 }
     */
    public POCDMT000040Component4 createPOCDMT000040Component4() {
        return new POCDMT000040Component4();
    }

    /**
     * Create an instance of {@link POCDMT000040Component5 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Component5 }
     */
    public POCDMT000040Component5 createPOCDMT000040Component5() {
        return new POCDMT000040Component5();
    }

    /**
     * Create an instance of {@link POCDMT000040Consent }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Consent }
     */
    public POCDMT000040Consent createPOCDMT000040Consent() {
        return new POCDMT000040Consent();
    }

    /**
     * Create an instance of {@link POCDMT000040Consumable }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Consumable }
     */
    public POCDMT000040Consumable createPOCDMT000040Consumable() {
        return new POCDMT000040Consumable();
    }

    /**
     * Create an instance of {@link POCDMT000040Criterion }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Criterion }
     */
    public POCDMT000040Criterion createPOCDMT000040Criterion() {
        return new POCDMT000040Criterion();
    }

    /**
     * Create an instance of {@link POCDMT000040Custodian }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Custodian }
     */
    public POCDMT000040Custodian createPOCDMT000040Custodian() {
        return new POCDMT000040Custodian();
    }

    /**
     * Create an instance of {@link POCDMT000040CustodianOrganization }
     * 
     * @return
     *     the new instance of {@link POCDMT000040CustodianOrganization }
     */
    public POCDMT000040CustodianOrganization createPOCDMT000040CustodianOrganization() {
        return new POCDMT000040CustodianOrganization();
    }

    /**
     * Create an instance of {@link POCDMT000040DataEnterer }
     * 
     * @return
     *     the new instance of {@link POCDMT000040DataEnterer }
     */
    public POCDMT000040DataEnterer createPOCDMT000040DataEnterer() {
        return new POCDMT000040DataEnterer();
    }

    /**
     * Create an instance of {@link POCDMT000040Device }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Device }
     */
    public POCDMT000040Device createPOCDMT000040Device() {
        return new POCDMT000040Device();
    }

    /**
     * Create an instance of {@link POCDMT000040DocumentationOf }
     * 
     * @return
     *     the new instance of {@link POCDMT000040DocumentationOf }
     */
    public POCDMT000040DocumentationOf createPOCDMT000040DocumentationOf() {
        return new POCDMT000040DocumentationOf();
    }

    /**
     * Create an instance of {@link POCDMT000040EncompassingEncounter }
     * 
     * @return
     *     the new instance of {@link POCDMT000040EncompassingEncounter }
     */
    public POCDMT000040EncompassingEncounter createPOCDMT000040EncompassingEncounter() {
        return new POCDMT000040EncompassingEncounter();
    }

    /**
     * Create an instance of {@link POCDMT000040Encounter }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Encounter }
     */
    public POCDMT000040Encounter createPOCDMT000040Encounter() {
        return new POCDMT000040Encounter();
    }

    /**
     * Create an instance of {@link POCDMT000040EncounterParticipant }
     * 
     * @return
     *     the new instance of {@link POCDMT000040EncounterParticipant }
     */
    public POCDMT000040EncounterParticipant createPOCDMT000040EncounterParticipant() {
        return new POCDMT000040EncounterParticipant();
    }

    /**
     * Create an instance of {@link POCDMT000040Entity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Entity }
     */
    public POCDMT000040Entity createPOCDMT000040Entity() {
        return new POCDMT000040Entity();
    }

    /**
     * Create an instance of {@link POCDMT000040Entry }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Entry }
     */
    public POCDMT000040Entry createPOCDMT000040Entry() {
        return new POCDMT000040Entry();
    }

    /**
     * Create an instance of {@link POCDMT000040EntryRelationship }
     * 
     * @return
     *     the new instance of {@link POCDMT000040EntryRelationship }
     */
    public POCDMT000040EntryRelationship createPOCDMT000040EntryRelationship() {
        return new POCDMT000040EntryRelationship();
    }

    /**
     * Create an instance of {@link POCDMT000040ExternalAct }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ExternalAct }
     */
    public POCDMT000040ExternalAct createPOCDMT000040ExternalAct() {
        return new POCDMT000040ExternalAct();
    }

    /**
     * Create an instance of {@link POCDMT000040ExternalDocument }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ExternalDocument }
     */
    public POCDMT000040ExternalDocument createPOCDMT000040ExternalDocument() {
        return new POCDMT000040ExternalDocument();
    }

    /**
     * Create an instance of {@link POCDMT000040ExternalObservation }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ExternalObservation }
     */
    public POCDMT000040ExternalObservation createPOCDMT000040ExternalObservation() {
        return new POCDMT000040ExternalObservation();
    }

    /**
     * Create an instance of {@link POCDMT000040ExternalProcedure }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ExternalProcedure }
     */
    public POCDMT000040ExternalProcedure createPOCDMT000040ExternalProcedure() {
        return new POCDMT000040ExternalProcedure();
    }

    /**
     * Create an instance of {@link POCDMT000040Guardian }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Guardian }
     */
    public POCDMT000040Guardian createPOCDMT000040Guardian() {
        return new POCDMT000040Guardian();
    }

    /**
     * Create an instance of {@link POCDMT000040HealthCareFacility }
     * 
     * @return
     *     the new instance of {@link POCDMT000040HealthCareFacility }
     */
    public POCDMT000040HealthCareFacility createPOCDMT000040HealthCareFacility() {
        return new POCDMT000040HealthCareFacility();
    }

    /**
     * Create an instance of {@link POCDMT000040Informant12 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Informant12 }
     */
    public POCDMT000040Informant12 createPOCDMT000040Informant12() {
        return new POCDMT000040Informant12();
    }

    /**
     * Create an instance of {@link POCDMT000040InformationRecipient }
     * 
     * @return
     *     the new instance of {@link POCDMT000040InformationRecipient }
     */
    public POCDMT000040InformationRecipient createPOCDMT000040InformationRecipient() {
        return new POCDMT000040InformationRecipient();
    }

    /**
     * Create an instance of {@link POCDMT000040InFulfillmentOf }
     * 
     * @return
     *     the new instance of {@link POCDMT000040InFulfillmentOf }
     */
    public POCDMT000040InFulfillmentOf createPOCDMT000040InFulfillmentOf() {
        return new POCDMT000040InFulfillmentOf();
    }

    /**
     * Create an instance of {@link POCDMT000040IntendedRecipient }
     * 
     * @return
     *     the new instance of {@link POCDMT000040IntendedRecipient }
     */
    public POCDMT000040IntendedRecipient createPOCDMT000040IntendedRecipient() {
        return new POCDMT000040IntendedRecipient();
    }

    /**
     * Create an instance of {@link POCDMT000040LabeledDrug }
     * 
     * @return
     *     the new instance of {@link POCDMT000040LabeledDrug }
     */
    public POCDMT000040LabeledDrug createPOCDMT000040LabeledDrug() {
        return new POCDMT000040LabeledDrug();
    }

    /**
     * Create an instance of {@link POCDMT000040LanguageCommunication }
     * 
     * @return
     *     the new instance of {@link POCDMT000040LanguageCommunication }
     */
    public POCDMT000040LanguageCommunication createPOCDMT000040LanguageCommunication() {
        return new POCDMT000040LanguageCommunication();
    }

    /**
     * Create an instance of {@link POCDMT000040LegalAuthenticator }
     * 
     * @return
     *     the new instance of {@link POCDMT000040LegalAuthenticator }
     */
    public POCDMT000040LegalAuthenticator createPOCDMT000040LegalAuthenticator() {
        return new POCDMT000040LegalAuthenticator();
    }

    /**
     * Create an instance of {@link POCDMT000040Location }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Location }
     */
    public POCDMT000040Location createPOCDMT000040Location() {
        return new POCDMT000040Location();
    }

    /**
     * Create an instance of {@link POCDMT000040MaintainedEntity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040MaintainedEntity }
     */
    public POCDMT000040MaintainedEntity createPOCDMT000040MaintainedEntity() {
        return new POCDMT000040MaintainedEntity();
    }

    /**
     * Create an instance of {@link POCDMT000040ManufacturedProduct }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ManufacturedProduct }
     */
    public POCDMT000040ManufacturedProduct createPOCDMT000040ManufacturedProduct() {
        return new POCDMT000040ManufacturedProduct();
    }

    /**
     * Create an instance of {@link POCDMT000040Material }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Material }
     */
    public POCDMT000040Material createPOCDMT000040Material() {
        return new POCDMT000040Material();
    }

    /**
     * Create an instance of {@link POCDMT000040NonXMLBody }
     * 
     * @return
     *     the new instance of {@link POCDMT000040NonXMLBody }
     */
    public POCDMT000040NonXMLBody createPOCDMT000040NonXMLBody() {
        return new POCDMT000040NonXMLBody();
    }

    /**
     * Create an instance of {@link POCDMT000040Observation }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Observation }
     */
    public POCDMT000040Observation createPOCDMT000040Observation() {
        return new POCDMT000040Observation();
    }

    /**
     * Create an instance of {@link POCDMT000040ObservationMedia }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ObservationMedia }
     */
    public POCDMT000040ObservationMedia createPOCDMT000040ObservationMedia() {
        return new POCDMT000040ObservationMedia();
    }

    /**
     * Create an instance of {@link POCDMT000040ObservationRange }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ObservationRange }
     */
    public POCDMT000040ObservationRange createPOCDMT000040ObservationRange() {
        return new POCDMT000040ObservationRange();
    }

    /**
     * Create an instance of {@link POCDMT000040Order }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Order }
     */
    public POCDMT000040Order createPOCDMT000040Order() {
        return new POCDMT000040Order();
    }

    /**
     * Create an instance of {@link POCDMT000040Organization }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Organization }
     */
    public POCDMT000040Organization createPOCDMT000040Organization() {
        return new POCDMT000040Organization();
    }

    /**
     * Create an instance of {@link POCDMT000040OrganizationPartOf }
     * 
     * @return
     *     the new instance of {@link POCDMT000040OrganizationPartOf }
     */
    public POCDMT000040OrganizationPartOf createPOCDMT000040OrganizationPartOf() {
        return new POCDMT000040OrganizationPartOf();
    }

    /**
     * Create an instance of {@link POCDMT000040Organizer }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Organizer }
     */
    public POCDMT000040Organizer createPOCDMT000040Organizer() {
        return new POCDMT000040Organizer();
    }

    /**
     * Create an instance of {@link POCDMT000040ParentDocument }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ParentDocument }
     */
    public POCDMT000040ParentDocument createPOCDMT000040ParentDocument() {
        return new POCDMT000040ParentDocument();
    }

    /**
     * Create an instance of {@link POCDMT000040Participant1 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Participant1 }
     */
    public POCDMT000040Participant1 createPOCDMT000040Participant1() {
        return new POCDMT000040Participant1();
    }

    /**
     * Create an instance of {@link POCDMT000040Participant2 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Participant2 }
     */
    public POCDMT000040Participant2 createPOCDMT000040Participant2() {
        return new POCDMT000040Participant2();
    }

    /**
     * Create an instance of {@link POCDMT000040ParticipantRole }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ParticipantRole }
     */
    public POCDMT000040ParticipantRole createPOCDMT000040ParticipantRole() {
        return new POCDMT000040ParticipantRole();
    }

    /**
     * Create an instance of {@link POCDMT000040Patient }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Patient }
     */
    public POCDMT000040Patient createPOCDMT000040Patient() {
        return new POCDMT000040Patient();
    }

    /**
     * Create an instance of {@link POCDMT000040PatientRole }
     * 
     * @return
     *     the new instance of {@link POCDMT000040PatientRole }
     */
    public POCDMT000040PatientRole createPOCDMT000040PatientRole() {
        return new POCDMT000040PatientRole();
    }

    /**
     * Create an instance of {@link POCDMT000040Performer1 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Performer1 }
     */
    public POCDMT000040Performer1 createPOCDMT000040Performer1() {
        return new POCDMT000040Performer1();
    }

    /**
     * Create an instance of {@link POCDMT000040Performer2 }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Performer2 }
     */
    public POCDMT000040Performer2 createPOCDMT000040Performer2() {
        return new POCDMT000040Performer2();
    }

    /**
     * Create an instance of {@link POCDMT000040Person }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Person }
     */
    public POCDMT000040Person createPOCDMT000040Person() {
        return new POCDMT000040Person();
    }

    /**
     * Create an instance of {@link POCDMT000040Place }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Place }
     */
    public POCDMT000040Place createPOCDMT000040Place() {
        return new POCDMT000040Place();
    }

    /**
     * Create an instance of {@link POCDMT000040PlayingEntity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040PlayingEntity }
     */
    public POCDMT000040PlayingEntity createPOCDMT000040PlayingEntity() {
        return new POCDMT000040PlayingEntity();
    }

    /**
     * Create an instance of {@link POCDMT000040Precondition }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Precondition }
     */
    public POCDMT000040Precondition createPOCDMT000040Precondition() {
        return new POCDMT000040Precondition();
    }

    /**
     * Create an instance of {@link POCDMT000040Procedure }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Procedure }
     */
    public POCDMT000040Procedure createPOCDMT000040Procedure() {
        return new POCDMT000040Procedure();
    }

    /**
     * Create an instance of {@link POCDMT000040Product }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Product }
     */
    public POCDMT000040Product createPOCDMT000040Product() {
        return new POCDMT000040Product();
    }

    /**
     * Create an instance of {@link POCDMT000040RecordTarget }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RecordTarget }
     */
    public POCDMT000040RecordTarget createPOCDMT000040RecordTarget() {
        return new POCDMT000040RecordTarget();
    }

    /**
     * Create an instance of {@link POCDMT000040Reference }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Reference }
     */
    public POCDMT000040Reference createPOCDMT000040Reference() {
        return new POCDMT000040Reference();
    }

    /**
     * Create an instance of {@link POCDMT000040ReferenceRange }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ReferenceRange }
     */
    public POCDMT000040ReferenceRange createPOCDMT000040ReferenceRange() {
        return new POCDMT000040ReferenceRange();
    }

    /**
     * Create an instance of {@link POCDMT000040RegionOfInterestValue }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RegionOfInterestValue }
     */
    public POCDMT000040RegionOfInterestValue createPOCDMT000040RegionOfInterestValue() {
        return new POCDMT000040RegionOfInterestValue();
    }

    /**
     * Create an instance of {@link POCDMT000040RegionOfInterest }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RegionOfInterest }
     */
    public POCDMT000040RegionOfInterest createPOCDMT000040RegionOfInterest() {
        return new POCDMT000040RegionOfInterest();
    }

    /**
     * Create an instance of {@link POCDMT000040RelatedDocument }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RelatedDocument }
     */
    public POCDMT000040RelatedDocument createPOCDMT000040RelatedDocument() {
        return new POCDMT000040RelatedDocument();
    }

    /**
     * Create an instance of {@link POCDMT000040RelatedEntity }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RelatedEntity }
     */
    public POCDMT000040RelatedEntity createPOCDMT000040RelatedEntity() {
        return new POCDMT000040RelatedEntity();
    }

    /**
     * Create an instance of {@link POCDMT000040RelatedSubject }
     * 
     * @return
     *     the new instance of {@link POCDMT000040RelatedSubject }
     */
    public POCDMT000040RelatedSubject createPOCDMT000040RelatedSubject() {
        return new POCDMT000040RelatedSubject();
    }

    /**
     * Create an instance of {@link POCDMT000040ResponsibleParty }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ResponsibleParty }
     */
    public POCDMT000040ResponsibleParty createPOCDMT000040ResponsibleParty() {
        return new POCDMT000040ResponsibleParty();
    }

    /**
     * Create an instance of {@link POCDMT000040Section }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Section }
     */
    public POCDMT000040Section createPOCDMT000040Section() {
        return new POCDMT000040Section();
    }

    /**
     * Create an instance of {@link POCDMT000040ServiceEvent }
     * 
     * @return
     *     the new instance of {@link POCDMT000040ServiceEvent }
     */
    public POCDMT000040ServiceEvent createPOCDMT000040ServiceEvent() {
        return new POCDMT000040ServiceEvent();
    }

    /**
     * Create an instance of {@link POCDMT000040Specimen }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Specimen }
     */
    public POCDMT000040Specimen createPOCDMT000040Specimen() {
        return new POCDMT000040Specimen();
    }

    /**
     * Create an instance of {@link POCDMT000040SpecimenRole }
     * 
     * @return
     *     the new instance of {@link POCDMT000040SpecimenRole }
     */
    public POCDMT000040SpecimenRole createPOCDMT000040SpecimenRole() {
        return new POCDMT000040SpecimenRole();
    }

    /**
     * Create an instance of {@link POCDMT000040StructuredBody }
     * 
     * @return
     *     the new instance of {@link POCDMT000040StructuredBody }
     */
    public POCDMT000040StructuredBody createPOCDMT000040StructuredBody() {
        return new POCDMT000040StructuredBody();
    }

    /**
     * Create an instance of {@link POCDMT000040Subject }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Subject }
     */
    public POCDMT000040Subject createPOCDMT000040Subject() {
        return new POCDMT000040Subject();
    }

    /**
     * Create an instance of {@link POCDMT000040SubjectPerson }
     * 
     * @return
     *     the new instance of {@link POCDMT000040SubjectPerson }
     */
    public POCDMT000040SubjectPerson createPOCDMT000040SubjectPerson() {
        return new POCDMT000040SubjectPerson();
    }

    /**
     * Create an instance of {@link POCDMT000040SubstanceAdministration }
     * 
     * @return
     *     the new instance of {@link POCDMT000040SubstanceAdministration }
     */
    public POCDMT000040SubstanceAdministration createPOCDMT000040SubstanceAdministration() {
        return new POCDMT000040SubstanceAdministration();
    }

    /**
     * Create an instance of {@link POCDMT000040Supply }
     * 
     * @return
     *     the new instance of {@link POCDMT000040Supply }
     */
    public POCDMT000040Supply createPOCDMT000040Supply() {
        return new POCDMT000040Supply();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link POCDMT000040ClinicalDocument }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link POCDMT000040ClinicalDocument }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "ClinicalDocument")
    public JAXBElement<POCDMT000040ClinicalDocument> createClinicalDocument(POCDMT000040ClinicalDocument value) {
        return new JAXBElement<>(_ClinicalDocument_QNAME, POCDMT000040ClinicalDocument.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocTh.class)
    public JAXBElement<StrucDocContent> createStrucDocThContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocTh.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocThLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocTh.class)
    public JAXBElement<StrucDocSub> createStrucDocThSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocTh.class)
    public JAXBElement<StrucDocSup> createStrucDocThSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocTh.class)
    public JAXBElement<StrucDocBr> createStrucDocThBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocTh.class)
    public JAXBElement<StrucDocFootnote> createStrucDocThFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocTh.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocThFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocTh.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocThRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocTh.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocTd.class)
    public JAXBElement<StrucDocContent> createStrucDocTdContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocTd.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocTdLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocTd.class)
    public JAXBElement<StrucDocSub> createStrucDocTdSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocTd.class)
    public JAXBElement<StrucDocSup> createStrucDocTdSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocTd.class)
    public JAXBElement<StrucDocBr> createStrucDocTdBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocTd.class)
    public JAXBElement<StrucDocFootnote> createStrucDocTdFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocTd.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocTdFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocTd.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocTdRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "paragraph", scope = StrucDocTd.class)
    public JAXBElement<StrucDocParagraph> createStrucDocTdParagraph(StrucDocParagraph value) {
        return new JAXBElement<>(_StrucDocTdParagraph_QNAME, StrucDocParagraph.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "list", scope = StrucDocTd.class)
    public JAXBElement<StrucDocList> createStrucDocTdList(StrucDocList value) {
        return new JAXBElement<>(_StrucDocTdList_QNAME, StrucDocList.class, StrucDocTd.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocCaption }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocCaption }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "caption", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocCaption> createStrucDocParagraphCaption(StrucDocCaption value) {
        return new JAXBElement<>(_StrucDocParagraphCaption_QNAME, StrucDocCaption.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocContent> createStrucDocParagraphContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocParagraphLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocSub> createStrucDocParagraphSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocSup> createStrucDocParagraphSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocBr> createStrucDocParagraphBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocFootnote> createStrucDocParagraphFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocParagraphFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocParagraph.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocParagraphRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocParagraph.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocLinkHtml.class)
    public JAXBElement<StrucDocFootnote> createStrucDocLinkHtmlFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocLinkHtml.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocLinkHtml.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocLinkHtmlFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocLinkHtml.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocCaption }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocCaption }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "caption", scope = StrucDocItem.class)
    public JAXBElement<StrucDocCaption> createStrucDocItemCaption(StrucDocCaption value) {
        return new JAXBElement<>(_StrucDocParagraphCaption_QNAME, StrucDocCaption.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocItem.class)
    public JAXBElement<StrucDocContent> createStrucDocItemContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocItem.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocItemLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocItem.class)
    public JAXBElement<StrucDocSub> createStrucDocItemSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocItem.class)
    public JAXBElement<StrucDocSup> createStrucDocItemSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocItem.class)
    public JAXBElement<StrucDocBr> createStrucDocItemBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocItem.class)
    public JAXBElement<StrucDocFootnote> createStrucDocItemFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocItem.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocItemFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocItem.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocItemRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "paragraph", scope = StrucDocItem.class)
    public JAXBElement<StrucDocParagraph> createStrucDocItemParagraph(StrucDocParagraph value) {
        return new JAXBElement<>(_StrucDocTdParagraph_QNAME, StrucDocParagraph.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "list", scope = StrucDocItem.class)
    public JAXBElement<StrucDocList> createStrucDocItemList(StrucDocList value) {
        return new JAXBElement<>(_StrucDocTdList_QNAME, StrucDocList.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "table", scope = StrucDocItem.class)
    public JAXBElement<StrucDocTable> createStrucDocItemTable(StrucDocTable value) {
        return new JAXBElement<>(_StrucDocItemTable_QNAME, StrucDocTable.class, StrucDocItem.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocTitleFootnote.class)
    public JAXBElement<StrucDocTitleContent> createStrucDocTitleFootnoteContent(StrucDocTitleContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocTitleContent.class, StrucDocTitleFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocTitleFootnote.class)
    public JAXBElement<StrucDocSub> createStrucDocTitleFootnoteSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocTitleFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocTitleFootnote.class)
    public JAXBElement<StrucDocSup> createStrucDocTitleFootnoteSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocTitleFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocTitleFootnote.class)
    public JAXBElement<StrucDocBr> createStrucDocTitleFootnoteBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocTitleFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocContent> createStrucDocFootnoteContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocFootnoteLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocSub> createStrucDocFootnoteSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocSup> createStrucDocFootnoteSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocBr> createStrucDocFootnoteBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocFootnoteRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "paragraph", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocParagraph> createStrucDocFootnoteParagraph(StrucDocParagraph value) {
        return new JAXBElement<>(_StrucDocTdParagraph_QNAME, StrucDocParagraph.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "list", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocList> createStrucDocFootnoteList(StrucDocList value) {
        return new JAXBElement<>(_StrucDocTdList_QNAME, StrucDocList.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "table", scope = StrucDocFootnote.class)
    public JAXBElement<StrucDocTable> createStrucDocFootnoteTable(StrucDocTable value) {
        return new JAXBElement<>(_StrucDocItemTable_QNAME, StrucDocTable.class, StrucDocFootnote.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocTitleContent> createStrucDocTitleContentContent(StrucDocTitleContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocTitleContent.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocSub> createStrucDocTitleContentSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocSup> createStrucDocTitleContentSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocBr> createStrucDocTitleContentBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTitleFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTitleFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocTitleFootnote> createStrucDocTitleContentFootnote(StrucDocTitleFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocTitleFootnote.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocTitleContent.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocTitleContentFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocTitleContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocContent.class)
    public JAXBElement<StrucDocContent> createStrucDocContentContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocContent.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocContentLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocContent.class)
    public JAXBElement<StrucDocSub> createStrucDocContentSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocContent.class)
    public JAXBElement<StrucDocSup> createStrucDocContentSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocContent.class)
    public JAXBElement<StrucDocBr> createStrucDocContentBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocContent.class)
    public JAXBElement<StrucDocFootnote> createStrucDocContentFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocContent.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocContentFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocContent.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocContentRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocCaption.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocCaptionLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocCaption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocCaption.class)
    public JAXBElement<StrucDocSub> createStrucDocCaptionSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocCaption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocCaption.class)
    public JAXBElement<StrucDocSup> createStrucDocCaptionSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocCaption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocCaption.class)
    public JAXBElement<StrucDocFootnote> createStrucDocCaptionFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocCaption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocCaption.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocCaptionFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocCaption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTitleContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocTitleContent> createStrucDocTitleContent(StrucDocTitleContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocTitleContent.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocSub> createStrucDocTitleSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocSup> createStrucDocTitleSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocBr> createStrucDocTitleBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTitleFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTitleFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocTitleFootnote> createStrucDocTitleFootnote(StrucDocTitleFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocTitleFootnote.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocTitle.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocTitleFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocTitle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocContent }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "content", scope = StrucDocText.class)
    public JAXBElement<StrucDocContent> createStrucDocTextContent(StrucDocContent value) {
        return new JAXBElement<>(_StrucDocThContent_QNAME, StrucDocContent.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocLinkHtml }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "linkHtml", scope = StrucDocText.class)
    public JAXBElement<StrucDocLinkHtml> createStrucDocTextLinkHtml(StrucDocLinkHtml value) {
        return new JAXBElement<>(_StrucDocThLinkHtml_QNAME, StrucDocLinkHtml.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSub }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sub", scope = StrucDocText.class)
    public JAXBElement<StrucDocSub> createStrucDocTextSub(StrucDocSub value) {
        return new JAXBElement<>(_StrucDocThSub_QNAME, StrucDocSub.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocSup }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "sup", scope = StrucDocText.class)
    public JAXBElement<StrucDocSup> createStrucDocTextSup(StrucDocSup value) {
        return new JAXBElement<>(_StrucDocThSup_QNAME, StrucDocSup.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocBr }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "br", scope = StrucDocText.class)
    public JAXBElement<StrucDocBr> createStrucDocTextBr(StrucDocBr value) {
        return new JAXBElement<>(_StrucDocThBr_QNAME, StrucDocBr.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnote }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnote", scope = StrucDocText.class)
    public JAXBElement<StrucDocFootnote> createStrucDocTextFootnote(StrucDocFootnote value) {
        return new JAXBElement<>(_StrucDocThFootnote_QNAME, StrucDocFootnote.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocFootnoteRef }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "footnoteRef", scope = StrucDocText.class)
    public JAXBElement<StrucDocFootnoteRef> createStrucDocTextFootnoteRef(StrucDocFootnoteRef value) {
        return new JAXBElement<>(_StrucDocThFootnoteRef_QNAME, StrucDocFootnoteRef.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocRenderMultiMedia }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "renderMultiMedia", scope = StrucDocText.class)
    public JAXBElement<StrucDocRenderMultiMedia> createStrucDocTextRenderMultiMedia(StrucDocRenderMultiMedia value) {
        return new JAXBElement<>(_StrucDocThRenderMultiMedia_QNAME, StrucDocRenderMultiMedia.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocParagraph }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "paragraph", scope = StrucDocText.class)
    public JAXBElement<StrucDocParagraph> createStrucDocTextParagraph(StrucDocParagraph value) {
        return new JAXBElement<>(_StrucDocTdParagraph_QNAME, StrucDocParagraph.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocList }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "list", scope = StrucDocText.class)
    public JAXBElement<StrucDocList> createStrucDocTextList(StrucDocList value) {
        return new JAXBElement<>(_StrucDocTdList_QNAME, StrucDocList.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link StrucDocTable }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "table", scope = StrucDocText.class)
    public JAXBElement<StrucDocTable> createStrucDocTextTable(StrucDocTable value) {
        return new JAXBElement<>(_StrucDocItemTable_QNAME, StrucDocTable.class, StrucDocText.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLPQ.class)
    public JAXBElement<IVXBPQ> createIVLPQLow(IVXBPQ value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBPQ.class, IVLPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLPQ.class)
    public JAXBElement<PQ> createIVLPQWidth(PQ value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, PQ.class, IVLPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLPQ.class)
    public JAXBElement<IVXBPQ> createIVLPQHigh(IVXBPQ value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBPQ.class, IVLPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLPQ.class)
    public JAXBElement<PQ> createIVLPQCenter(PQ value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, PQ.class, IVLPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBMO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBMO }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLMO.class)
    public JAXBElement<IVXBMO> createIVLMOLow(IVXBMO value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBMO.class, IVLMO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MO }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLMO.class)
    public JAXBElement<MO> createIVLMOWidth(MO value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, MO.class, IVLMO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBMO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBMO }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLMO.class)
    public JAXBElement<IVXBMO> createIVLMOHigh(IVXBMO value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBMO.class, IVLMO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MO }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLMO.class)
    public JAXBElement<MO> createIVLMOCenter(MO value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, MO.class, IVLMO.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBREAL }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBREAL }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLREAL.class)
    public JAXBElement<IVXBREAL> createIVLREALLow(IVXBREAL value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBREAL.class, IVLREAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link REAL }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link REAL }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLREAL.class)
    public JAXBElement<REAL> createIVLREALWidth(REAL value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, REAL.class, IVLREAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBREAL }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBREAL }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLREAL.class)
    public JAXBElement<IVXBREAL> createIVLREALHigh(IVXBREAL value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBREAL.class, IVLREAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link REAL }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link REAL }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLREAL.class)
    public JAXBElement<REAL> createIVLREALCenter(REAL value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, REAL.class, IVLREAL.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBINT }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBINT }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLINT.class)
    public JAXBElement<IVXBINT> createIVLINTLow(IVXBINT value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBINT.class, IVLINT.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link INT }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link INT }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLINT.class)
    public JAXBElement<INT> createIVLINTWidth(INT value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, INT.class, IVLINT.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBINT }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBINT }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLINT.class)
    public JAXBElement<IVXBINT> createIVLINTHigh(IVXBINT value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBINT.class, IVLINT.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link INT }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link INT }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLINT.class)
    public JAXBElement<INT> createIVLINTCenter(INT value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, INT.class, IVLINT.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLPPDPQ.class)
    public JAXBElement<IVXBPPDPQ> createIVLPPDPQLow(IVXBPPDPQ value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBPPDPQ.class, IVLPPDPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLPPDPQ.class)
    public JAXBElement<PPDPQ> createIVLPPDPQWidth(PPDPQ value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, PPDPQ.class, IVLPPDPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPPDPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLPPDPQ.class)
    public JAXBElement<IVXBPPDPQ> createIVLPPDPQHigh(IVXBPPDPQ value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBPPDPQ.class, IVLPPDPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLPPDPQ.class)
    public JAXBElement<PPDPQ> createIVLPPDPQCenter(PPDPQ value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, PPDPQ.class, IVLPPDPQ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPPDTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPPDTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLPPDTS.class)
    public JAXBElement<IVXBPPDTS> createIVLPPDTSLow(IVXBPPDTS value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBPPDTS.class, IVLPPDTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PPDPQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLPPDTS.class)
    public JAXBElement<PPDPQ> createIVLPPDTSWidth(PPDPQ value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, PPDPQ.class, IVLPPDTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBPPDTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBPPDTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLPPDTS.class)
    public JAXBElement<IVXBPPDTS> createIVLPPDTSHigh(IVXBPPDTS value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBPPDTS.class, IVLPPDTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PPDTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PPDTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLPPDTS.class)
    public JAXBElement<PPDTS> createIVLPPDTSCenter(PPDTS value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, PPDTS.class, IVLPPDTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "low", scope = IVLTS.class)
    public JAXBElement<IVXBTS> createIVLTSLow(IVXBTS value) {
        return new JAXBElement<>(_IVLPQLow_QNAME, IVXBTS.class, IVLTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PQ }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "width", scope = IVLTS.class)
    public JAXBElement<PQ> createIVLTSWidth(PQ value) {
        return new JAXBElement<>(_IVLPQWidth_QNAME, PQ.class, IVLTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVXBTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVXBTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "high", scope = IVLTS.class)
    public JAXBElement<IVXBTS> createIVLTSHigh(IVXBTS value) {
        return new JAXBElement<>(_IVLPQHigh_QNAME, IVXBTS.class, IVLTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "center", scope = IVLTS.class)
    public JAXBElement<TS> createIVLTSCenter(TS value) {
        return new JAXBElement<>(_IVLPQCenter_QNAME, TS.class, IVLTS.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnDelimiter }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EnDelimiter }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "delimiter", scope = EN.class)
    public JAXBElement<EnDelimiter> createENDelimiter(EnDelimiter value) {
        return new JAXBElement<>(_ENDelimiter_QNAME, EnDelimiter.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnFamily }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EnFamily }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "family", scope = EN.class)
    public JAXBElement<EnFamily> createENFamily(EnFamily value) {
        return new JAXBElement<>(_ENFamily_QNAME, EnFamily.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnGiven }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EnGiven }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "given", scope = EN.class)
    public JAXBElement<EnGiven> createENGiven(EnGiven value) {
        return new JAXBElement<>(_ENGiven_QNAME, EnGiven.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnPrefix }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EnPrefix }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "prefix", scope = EN.class)
    public JAXBElement<EnPrefix> createENPrefix(EnPrefix value) {
        return new JAXBElement<>(_ENPrefix_QNAME, EnPrefix.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnSuffix }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EnSuffix }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "suffix", scope = EN.class)
    public JAXBElement<EnSuffix> createENSuffix(EnSuffix value) {
        return new JAXBElement<>(_ENSuffix_QNAME, EnSuffix.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IVLTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IVLTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "validTime", scope = EN.class)
    public JAXBElement<IVLTS> createENValidTime(IVLTS value) {
        return new JAXBElement<>(_ENValidTime_QNAME, IVLTS.class, EN.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDelimiter }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDelimiter }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "delimiter", scope = AD.class)
    public JAXBElement<AdxpDelimiter> createADDelimiter(AdxpDelimiter value) {
        return new JAXBElement<>(_ENDelimiter_QNAME, AdxpDelimiter.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpCountry }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpCountry }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "country", scope = AD.class)
    public JAXBElement<AdxpCountry> createADCountry(AdxpCountry value) {
        return new JAXBElement<>(_ADCountry_QNAME, AdxpCountry.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpState }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpState }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "state", scope = AD.class)
    public JAXBElement<AdxpState> createADState(AdxpState value) {
        return new JAXBElement<>(_ADState_QNAME, AdxpState.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpCounty }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpCounty }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "county", scope = AD.class)
    public JAXBElement<AdxpCounty> createADCounty(AdxpCounty value) {
        return new JAXBElement<>(_ADCounty_QNAME, AdxpCounty.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpCity }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpCity }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "city", scope = AD.class)
    public JAXBElement<AdxpCity> createADCity(AdxpCity value) {
        return new JAXBElement<>(_ADCity_QNAME, AdxpCity.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpTownship }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpTownship }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "township", scope = AD.class)
    public JAXBElement<AdxpTownship> createADTownship(AdxpTownship value) {
        return new JAXBElement<>(_ADTownship_QNAME, AdxpTownship.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpPostalCode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpPostalCode }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "postalCode", scope = AD.class)
    public JAXBElement<AdxpPostalCode> createADPostalCode(AdxpPostalCode value) {
        return new JAXBElement<>(_ADPostalCode_QNAME, AdxpPostalCode.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpStreetAddressLine }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpStreetAddressLine }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "streetAddressLine", scope = AD.class)
    public JAXBElement<AdxpStreetAddressLine> createADStreetAddressLine(AdxpStreetAddressLine value) {
        return new JAXBElement<>(_ADStreetAddressLine_QNAME, AdxpStreetAddressLine.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpHouseNumber }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpHouseNumber }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "houseNumber", scope = AD.class)
    public JAXBElement<AdxpHouseNumber> createADHouseNumber(AdxpHouseNumber value) {
        return new JAXBElement<>(_ADHouseNumber_QNAME, AdxpHouseNumber.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpHouseNumberNumeric }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpHouseNumberNumeric }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "houseNumberNumeric", scope = AD.class)
    public JAXBElement<AdxpHouseNumberNumeric> createADHouseNumberNumeric(AdxpHouseNumberNumeric value) {
        return new JAXBElement<>(_ADHouseNumberNumeric_QNAME, AdxpHouseNumberNumeric.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDirection }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDirection }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "direction", scope = AD.class)
    public JAXBElement<AdxpDirection> createADDirection(AdxpDirection value) {
        return new JAXBElement<>(_ADDirection_QNAME, AdxpDirection.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpStreetName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpStreetName }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "streetName", scope = AD.class)
    public JAXBElement<AdxpStreetName> createADStreetName(AdxpStreetName value) {
        return new JAXBElement<>(_ADStreetName_QNAME, AdxpStreetName.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpStreetNameBase }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpStreetNameBase }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "streetNameBase", scope = AD.class)
    public JAXBElement<AdxpStreetNameBase> createADStreetNameBase(AdxpStreetNameBase value) {
        return new JAXBElement<>(_ADStreetNameBase_QNAME, AdxpStreetNameBase.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpStreetNameType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpStreetNameType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "streetNameType", scope = AD.class)
    public JAXBElement<AdxpStreetNameType> createADStreetNameType(AdxpStreetNameType value) {
        return new JAXBElement<>(_ADStreetNameType_QNAME, AdxpStreetNameType.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpAdditionalLocator }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpAdditionalLocator }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "additionalLocator", scope = AD.class)
    public JAXBElement<AdxpAdditionalLocator> createADAdditionalLocator(AdxpAdditionalLocator value) {
        return new JAXBElement<>(_ADAdditionalLocator_QNAME, AdxpAdditionalLocator.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpUnitID }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpUnitID }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "unitID", scope = AD.class)
    public JAXBElement<AdxpUnitID> createADUnitID(AdxpUnitID value) {
        return new JAXBElement<>(_ADUnitID_QNAME, AdxpUnitID.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpUnitType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpUnitType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "unitType", scope = AD.class)
    public JAXBElement<AdxpUnitType> createADUnitType(AdxpUnitType value) {
        return new JAXBElement<>(_ADUnitType_QNAME, AdxpUnitType.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpCareOf }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpCareOf }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "careOf", scope = AD.class)
    public JAXBElement<AdxpCareOf> createADCareOf(AdxpCareOf value) {
        return new JAXBElement<>(_ADCareOf_QNAME, AdxpCareOf.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpCensusTract }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpCensusTract }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "censusTract", scope = AD.class)
    public JAXBElement<AdxpCensusTract> createADCensusTract(AdxpCensusTract value) {
        return new JAXBElement<>(_ADCensusTract_QNAME, AdxpCensusTract.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryAddressLine }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryAddressLine }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryAddressLine", scope = AD.class)
    public JAXBElement<AdxpDeliveryAddressLine> createADDeliveryAddressLine(AdxpDeliveryAddressLine value) {
        return new JAXBElement<>(_ADDeliveryAddressLine_QNAME, AdxpDeliveryAddressLine.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationType }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryInstallationType", scope = AD.class)
    public JAXBElement<AdxpDeliveryInstallationType> createADDeliveryInstallationType(AdxpDeliveryInstallationType value) {
        return new JAXBElement<>(_ADDeliveryInstallationType_QNAME, AdxpDeliveryInstallationType.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationArea }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationArea }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryInstallationArea", scope = AD.class)
    public JAXBElement<AdxpDeliveryInstallationArea> createADDeliveryInstallationArea(AdxpDeliveryInstallationArea value) {
        return new JAXBElement<>(_ADDeliveryInstallationArea_QNAME, AdxpDeliveryInstallationArea.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationQualifier }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryInstallationQualifier }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryInstallationQualifier", scope = AD.class)
    public JAXBElement<AdxpDeliveryInstallationQualifier> createADDeliveryInstallationQualifier(AdxpDeliveryInstallationQualifier value) {
        return new JAXBElement<>(_ADDeliveryInstallationQualifier_QNAME, AdxpDeliveryInstallationQualifier.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryMode }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryMode }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryMode", scope = AD.class)
    public JAXBElement<AdxpDeliveryMode> createADDeliveryMode(AdxpDeliveryMode value) {
        return new JAXBElement<>(_ADDeliveryMode_QNAME, AdxpDeliveryMode.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryModeIdentifier }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpDeliveryModeIdentifier }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "deliveryModeIdentifier", scope = AD.class)
    public JAXBElement<AdxpDeliveryModeIdentifier> createADDeliveryModeIdentifier(AdxpDeliveryModeIdentifier value) {
        return new JAXBElement<>(_ADDeliveryModeIdentifier_QNAME, AdxpDeliveryModeIdentifier.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpBuildingNumberSuffix }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpBuildingNumberSuffix }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "buildingNumberSuffix", scope = AD.class)
    public JAXBElement<AdxpBuildingNumberSuffix> createADBuildingNumberSuffix(AdxpBuildingNumberSuffix value) {
        return new JAXBElement<>(_ADBuildingNumberSuffix_QNAME, AdxpBuildingNumberSuffix.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpPostBox }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpPostBox }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "postBox", scope = AD.class)
    public JAXBElement<AdxpPostBox> createADPostBox(AdxpPostBox value) {
        return new JAXBElement<>(_ADPostBox_QNAME, AdxpPostBox.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdxpPrecinct }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AdxpPrecinct }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "precinct", scope = AD.class)
    public JAXBElement<AdxpPrecinct> createADPrecinct(AdxpPrecinct value) {
        return new JAXBElement<>(_ADPrecinct_QNAME, AdxpPrecinct.class, AD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SXCMTS }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SXCMTS }{@code >}
     */
    @XmlElementDecl(namespace = "urn:hl7-org:v3", name = "useablePeriod", scope = AD.class)
    public JAXBElement<SXCMTS> createADUseablePeriod(SXCMTS value) {
        return new JAXBElement<>(_ADUseablePeriod_QNAME, SXCMTS.class, AD.class, value);
    }

}
