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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             A character string that may have a type-tag signifying its
 *             role in the address. Typical parts that exist in about
 *             every address are street, house number, or post box,
 *             postal code, city, country but other roles may be defined
 *             regionally, nationally, or on an enterprise level (e.g. in
 *             military addresses). Addresses are usually broken up into
 *             lines, which are indicated by special line-breaking
 *             delimiter elements (e.g., DEL).
 *          
 * 
 * <p>ADXP complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="ADXP">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ST">
 *       <attribute name="partType" type="{urn:hl7-org:v3}AddressPartType" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADXP")
@XmlSeeAlso({
    AdxpDelimiter.class,
    AdxpCountry.class,
    AdxpState.class,
    AdxpCounty.class,
    AdxpCity.class,
    AdxpTownship.class,
    AdxpPostalCode.class,
    AdxpStreetAddressLine.class,
    AdxpHouseNumber.class,
    AdxpHouseNumberNumeric.class,
    AdxpDirection.class,
    AdxpStreetName.class,
    AdxpStreetNameBase.class,
    AdxpStreetNameType.class,
    AdxpAdditionalLocator.class,
    AdxpUnitID.class,
    AdxpUnitType.class,
    AdxpCareOf.class,
    AdxpCensusTract.class,
    AdxpDeliveryAddressLine.class,
    AdxpDeliveryInstallationType.class,
    AdxpDeliveryInstallationArea.class,
    AdxpDeliveryInstallationQualifier.class,
    AdxpDeliveryMode.class,
    AdxpDeliveryModeIdentifier.class,
    AdxpBuildingNumberSuffix.class,
    AdxpPostBox.class,
    AdxpPrecinct.class
})
public class ADXP
    extends ST
{

    @XmlAttribute(name = "partType")
    protected List<String> partType;

    /**
     * Gets the value of the partType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the partType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the partType property.
     */
    public List<String> getPartType() {
        if (partType == null) {
            partType = new ArrayList<>();
        }
        return this.partType;
    }

}
