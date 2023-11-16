//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             A name for an organization. A sequence of name parts.
 *          
 * 
 * <p>ON complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="ON">
 *   <complexContent>
 *     <restriction base="{urn:hl7-org:v3}EN">
 *       <sequence>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element name="delimiter" type="{urn:hl7-org:v3}en.delimiter"/>
 *           <element name="prefix" type="{urn:hl7-org:v3}en.prefix"/>
 *           <element name="suffix" type="{urn:hl7-org:v3}en.suffix"/>
 *         </choice>
 *         <element name="validTime" type="{urn:hl7-org:v3}IVL_TS" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="use" type="{urn:hl7-org:v3}set_EntityNameUse" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ON")
public class ON
    extends EN
{


}
