//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             Coded data, consists of a coded value (CV)
 *             and, optionally, coded value(s) from other coding systems
 *             that identify the same concept. Used when alternative
 *             codes may exist.
 *          
 * 
 * <p>CE complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="CE">
 *   <complexContent>
 *     <restriction base="{urn:hl7-org:v3}CD">
 *       <sequence>
 *         <element name="originalText" type="{urn:hl7-org:v3}ED" minOccurs="0"/>
 *         <element name="qualifier" type="{urn:hl7-org:v3}CR" maxOccurs="0" minOccurs="0"/>
 *         <element name="translation" type="{urn:hl7-org:v3}CD" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="code" type="{urn:hl7-org:v3}cs" />
 *       <attribute name="codeSystem" type="{urn:hl7-org:v3}uid" />
 *       <attribute name="codeSystemName" type="{urn:hl7-org:v3}st" />
 *       <attribute name="codeSystemVersion" type="{urn:hl7-org:v3}st" />
 *       <attribute name="displayName" type="{urn:hl7-org:v3}st" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CE")
@XmlSeeAlso({
    CV.class,
    EIVLEvent.class,
    HXITCE.class
})
public class CE
    extends CD
{


}
