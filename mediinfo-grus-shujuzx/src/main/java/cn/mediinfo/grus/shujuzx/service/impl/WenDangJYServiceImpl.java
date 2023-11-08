package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.dto.wendangjys.VerifyXMLDto;
import cn.mediinfo.grus.shujuzx.po.shujuy.ShuJuYZYListPo;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYLBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYZYRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangJYService;
import com.querydsl.core.types.Expression;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Objects;

@Service
public class WenDangJYServiceImpl implements WenDangJYService {
    private final SC_ZD_ShuJuYLBRepository shuJuYLBRepository;
    private final SC_ZD_ShuJuYZYRepository shuJuYZYRepository;

    public WenDangJYServiceImpl(SC_ZD_ShuJuYLBRepository shuJuYLBRepository,
                                SC_ZD_ShuJuYZYRepository shuJuYZYRepository) {
        this.shuJuYLBRepository = shuJuYLBRepository;
        this.shuJuYZYRepository = shuJuYZYRepository;

    }

    /**
     * 文档效验 根据数据集效验
     *
     * @param dto
     * @return
     */
    @Override
    public String VerifyCDADoc(VerifyXMLDto dto) throws ParserConfigurationException, IOException, SAXException {
        StringBuilder result = new StringBuilder();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            // 创建DocumentBuilder实例
            DocumentBuilder builder = factory.newDocumentBuilder();
            // 将XML字符串解析为Document对象
            Document document = builder.parse(new InputSource(new StringReader(dto.getXmlstr())));
            //获取根节点
            Element rootElement = document.getDocumentElement();
            //解析内容
            NodeList childNodes = rootElement.getChildNodes();
            // 遍历子元素
            var resultList = parseElement(childNodes, result);
            if (resultList.length() > 0) {
                result.append(resultList);
            }

        } catch (Exception e) {
            var itemResult = "共享文档效验出错：" + e.getMessage();
            result.append(itemResult);
        }
        return result.toString();
    }

    private StringBuilder parseElement(NodeList nodeList, StringBuilder result) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            NodeList childNodeList = nodeList.item(i).getChildNodes();
            Node childNode = nodeList.item(i);
            //判断是否只有一个子节点 是->判断规则 否->递归解析
            if (Objects.equals(childNodeList.getLength(), 0)) {
                //判断当前节点是否为元素节点 是->规则判断 否->继续循环
                if (Objects.equals(childNode.getNodeType(), Node.ELEMENT_NODE)) {
                    Element element = (Element) childNode;
                    String nodeType = element.getAttribute("xsi:type");
                    // 处理子元素
                    if (Objects.equals(element.getTagName(), "value") && Objects.equals(nodeType, "CD")) {
                        String code = element.getAttribute("code");
                        String displayName = element.getAttribute("displayName");
                        String codeSystemName = element.getAttribute("codeSystemName");
                        String codeSystem = element.getAttribute("codeSystem");
                        var query = GetShuJuYZYListByOID(codeSystem,code);
                        if (!Objects.isNull(query)) {
                            if (!Objects.equals(query.getBiaoZhunMC(), displayName)) {
                                if (result.length() > 0) {
                                    //增加换行
                                    result.append("\r\n");
                                }
                                result.append(codeSystemName).append(" 的displayName值:").append(displayName).append("和字典值:").append(query.getBiaoZhunMC()).append("不匹配！|");
                            }
                        } else {
                            if (result.length() > 0) {
                                //增加换行
                                result.append("\r\n");
                            }
                            result.append(codeSystemName).append(" 的code值:").append(code).append("不在标准范围内！| ");
                        }
                    }
                }
            } else {
                parseElement(childNodeList, result);
            }

        }
        return result;
    }

    /**
     * 根据OID查询数据源值域
     *
     * @param oID
     * @return
     */
    public SC_ZD_ShuJuYZYDto GetShuJuYZYListByOID(String oID,String biaoZhunDM) {
        var result = shuJuYZYRepository.asQuerydsl()
                .join(shuJuYLBRepository.asQuerydsl(), (shuJuYZY, shuJuYLB) -> shuJuYZY.shuJuYLBID.eq(shuJuYLB.shuJuYLBID).and(shuJuYLB.oID.eq(oID)).and(shuJuYZY.biaoZhunDM.eq(biaoZhunDM)), ShuJuYZYListPo::new)
                .select(q -> new Expression<?>[]{
                        q.shuJuYZY().shuJuYLBID,
                        q.shuJuYZY().shuJuYLBMC,
                        q.shuJuYZY().biaoZhunDM,
                        q.shuJuYZY().biaoZhunMC
                }, SC_ZD_ShuJuYZYDto.class).fetchFirst();
        return result;
    }


}
