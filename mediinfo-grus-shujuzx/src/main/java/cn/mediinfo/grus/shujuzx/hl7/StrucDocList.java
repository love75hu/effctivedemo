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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>StrucDoc.List complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>{@code
 * <complexType name="StrucDoc.List">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="caption" type="{urn:hl7-org:v3}StrucDoc.Caption" minOccurs="0"/>
 *         <element name="item" type="{urn:hl7-org:v3}StrucDoc.Item" maxOccurs="unbounded"/>
 *       </sequence>
 *       <attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       <attribute name="language" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       <attribute name="styleCode" type="{http://www.w3.org/2001/XMLSchema}NMTOKENS" />
 *       <attribute name="listType" default="unordered">
 *         <simpleType>
 *           <restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             <enumeration value="ordered"/>
 *             <enumeration value="unordered"/>
 *           </restriction>
 *         </simpleType>
 *       </attribute>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrucDoc.List", propOrder = {
    "caption",
    "item"
})
public class StrucDocList {

    protected StrucDocCaption caption;
    @XmlElement(required = true)
    protected List<StrucDocItem> item;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String language;
    @XmlAttribute(name = "styleCode")
    @XmlSchemaType(name = "NMTOKENS")
    protected List<String> styleCode;
    @XmlAttribute(name = "listType")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String listType;

    /**
     * 获取caption属性的值。
     * 
     * @return
     *     possible object is
     *     {@link StrucDocCaption }
     *     
     */
    public StrucDocCaption getCaption() {
        return caption;
    }

    /**
     * 设置caption属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link StrucDocCaption }
     *     
     */
    public void setCaption(StrucDocCaption value) {
        this.caption = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StrucDocItem }
     * 
     * 
     * @return
     *     The value of the item property.
     */
    public List<StrucDocItem> getItem() {
        if (item == null) {
            item = new ArrayList<>();
        }
        return this.item;
    }

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * 获取language属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置language属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the styleCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the styleCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStyleCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     * @return
     *     The value of the styleCode property.
     */
    public List<String> getStyleCode() {
        if (styleCode == null) {
            styleCode = new ArrayList<>();
        }
        return this.styleCode;
    }

    /**
     * 获取listType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListType() {
        if (listType == null) {
            return "unordered";
        } else {
            return listType;
        }
    }

    /**
     * 设置listType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListType(String value) {
        this.listType = value;
    }

}
