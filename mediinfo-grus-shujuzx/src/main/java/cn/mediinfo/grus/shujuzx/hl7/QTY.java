//
// ���ļ����� Eclipse Implementation of JAXB v4.0.3 ���ɵ�
// ����� https://eclipse-ee4j.github.io/jaxb-ri 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
//


package cn.mediinfo.grus.shujuzx.hl7;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 *             The quantity data type is an abstract generalization
 *             for all data types (1) whose value set has an order
 *             relation (less-or-equal) and (2) where difference is
 *             defined in all of the data type's totally ordered value
 *             subsets.  The quantity type abstraction is needed in
 *             defining certain other types, such as the interval and
 *             the probability distribution.
 *          
 * 
 * <p>QTY complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>{@code
 * <complexType name="QTY">
 *   <complexContent>
 *     <extension base="{urn:hl7-org:v3}ANY">
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QTY")
@XmlSeeAlso({
    RTOQTYQTY.class,
    REAL.class,
    MO.class,
    PQ.class,
    RTOPQPQ.class,
    RTOMOPQ.class,
    TS.class,
    INT.class
})
public abstract class QTY
    extends ANY
{


}
