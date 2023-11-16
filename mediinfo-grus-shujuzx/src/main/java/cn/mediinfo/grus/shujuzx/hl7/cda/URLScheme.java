//
// 此文件是由 Eclipse Implementation of JAXB v4.0.3 生成的
// 请访问 https://eclipse-ee4j.github.io/jaxb-ri 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
//


package cn.mediinfo.grus.shujuzx.hl7.cda;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>URLScheme的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <pre>{@code
 * <simpleType name="URLScheme">
 *   <restriction base="{urn:hl7-org:v3}cs">
 *     <enumeration value="fax"/>
 *     <enumeration value="file"/>
 *     <enumeration value="ftp"/>
 *     <enumeration value="http"/>
 *     <enumeration value="mailto"/>
 *     <enumeration value="mllp"/>
 *     <enumeration value="modem"/>
 *     <enumeration value="nfs"/>
 *     <enumeration value="tel"/>
 *     <enumeration value="telnet"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "URLScheme")
@XmlEnum
public enum URLScheme {

    @XmlEnumValue("fax")
    FAX("fax"),
    @XmlEnumValue("file")
    FILE("file"),
    @XmlEnumValue("ftp")
    FTP("ftp"),
    @XmlEnumValue("http")
    HTTP("http"),
    @XmlEnumValue("mailto")
    MAILTO("mailto"),
    @XmlEnumValue("mllp")
    MLLP("mllp"),
    @XmlEnumValue("modem")
    MODEM("modem"),
    @XmlEnumValue("nfs")
    NFS("nfs"),
    @XmlEnumValue("tel")
    TEL("tel"),
    @XmlEnumValue("telnet")
    TELNET("telnet");
    private final String value;

    URLScheme(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static URLScheme fromValue(String v) {
        for (URLScheme c: URLScheme.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
