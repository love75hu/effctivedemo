package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.response.XiTongResponseCode;
import cn.mediinfo.grus.shujuzx.dto.wenDang.*;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "WenDangPZController", description = "文档配置")
@RequestMapping({"api/v1.0/WenDangPZ", "api/v1/WenDangPZ"})
@Slf4j
@Validated
public class WenDangPZController {

    private final WenDangPZService wenDangPZService;

    public WenDangPZController(WenDangPZService wenDangPZService) {
        this.wenDangPZService = wenDangPZService;
    }

    /**
     * 获取文档配置列表
     */
    @Operation(summary = "获取文档配置列表")
    @GetMapping("GetWenDangPZList")
    public MsfResponse<List<SC_ZD_WenDangDto>> getWenDangPZList(@RequestParam(required = false) String leiBieDM, @RequestParam(required = false) String likeQuery, @RequestParam(required = false, defaultValue = "1") Integer PageIndex, @RequestParam(required = false, defaultValue = "10") Integer PageSize) {
        return MsfResponse.success(wenDangPZService.getWenDangPZList(leiBieDM, likeQuery, PageIndex, PageSize));
    }

    /**
     * 获取文档配置数量
     */
    @Operation(summary = "获取文档配置数量")
    @GetMapping("GetWenDangPZCount")
    public MsfResponse<Long> getWenDangPZCount(@RequestParam(required = false) String leiBieDM, @RequestParam(required = false) String likeQuery) {
        return MsfResponse.success(wenDangPZService.getWenDangPZCount(leiBieDM, likeQuery));
    }

    /**
     * 新增文档配置
     */
    @Operation(summary = "添加文档配置")
    @PostMapping("AddWenDangPZ")
    public MsfResponse<String> addWenDangPZ(@RequestBody SC_ZD_WenDangGreaterDto wenDangGreaterDto) throws TongYongYWException {
        if (!StringUtils.hasText(wenDangGreaterDto.getWenDangID()) || !StringUtils.hasText(wenDangGreaterDto.getWenDangMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "文档ID和文档名称不可为空！");
        }
        return MsfResponse.success(wenDangPZService.addWenDangPZ(wenDangGreaterDto));
    }

    /**
     * 编辑文档配置
     */
    @Operation(summary = "编辑文档配置")
    @PostMapping("UpDateWenDangPZ")
    public MsfResponse<Boolean> UpDateWenDangPZ(@RequestBody SC_ZD_WenDangUpDateDto wenDangUpDateDto) throws TongYongYWException {
        if (!StringUtils.hasText(wenDangUpDateDto.getId()) || !StringUtils.hasText(wenDangUpDateDto.getWenDangID()) || !StringUtils.hasText(wenDangUpDateDto.getWenDangMC())) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "主键ID，文档ID，文档名称不可为空！");
        }
        return MsfResponse.success(wenDangPZService.UpDateWenDangPZ(wenDangUpDateDto));
    }

    /**
     * 根据文档ID获取模板内容
     */
    @Operation(summary = "根据文档ID获取模板内容")
    @GetMapping("GetWenDangMBXX")
    public MsfResponse<SC_ZD_WenDangMBXMLDto> getWenDangMBXX(@RequestParam(required = false) String wenDangID) throws TongYongYWException {
        SC_ZD_WenDangMBDto Dto = wenDangPZService.getWenDangMBXX(wenDangID);
        //Xml字符串清空换行符
        String XmlStr = Dto.getMuBanNR().replaceAll("\\r|\\n", "");
        //XML字符串转Document类型
        Document document = parseXMLString(XmlStr);
        //判断是否转成功
        if (document == null) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "xml格式错误！");
        }
        //XML转实体
        List<XML_JieDian> jieDianList = traverseNodes(document.getDocumentElement());
        //定义返回类型
        SC_ZD_WenDangMBXMLDto xmlDto = new SC_ZD_WenDangMBXMLDto();
        xmlDto.setId(Dto.getId());
        xmlDto.setXmlText(jieDianList);
        return MsfResponse.success(xmlDto);
    }

    /**
     * XML字符串转Document
     */
    public static Document parseXMLString(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * XML转换成实体类
     */
    public static List<XML_JieDian> traverseNodes(Node node) {
        List<XML_JieDian> Temp = new ArrayList<>();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            //转类型
            Element element = (Element) node;
            //获取属性
            NamedNodeMap attributes = element.getAttributes();
            //创建接收节点类
            XML_JieDian jieDian = new XML_JieDian();
            //节点名称赋值
            String Name = element.getNodeName();
            jieDian.setJieDianName(element.getNodeName());
            //创建节点属性集合类
            List<XML_JieDianSX> jieDianList = new ArrayList<>();
            //遍历节点属性集合，添加到集合类
            for (int i = 0; i < attributes.getLength(); i++) {
                Node attribute = attributes.item(i);
                XML_JieDianSX shuXing = new XML_JieDianSX();
                shuXing.setName(attribute.getNodeName());
                shuXing.setValue(attribute.getNodeValue());
                jieDianList.add(shuXing);
            }
            //节点集合加入的节点类里
            jieDian.setJieDianSXList(jieDianList);
            //获取节点文本
            if (element.hasChildNodes()) {
                NodeList children = element.getChildNodes();
                Integer iii = children.getLength();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    if (child.getNodeType() == Node.TEXT_NODE) {
                        if (!child.getNodeValue().contains("\n")) {
                            String str = child.getNodeValue();
                            jieDian.setText(str);
                        }
                    }
                }
            }
            //获取所有子节点遍历
            NodeList childNodes = element.getChildNodes();
            List<XML_JieDian> tempJieDian = new ArrayList<>();
            for (int i = 0; i < childNodes.getLength(); i++) {
                List<XML_JieDian> JieDianTemp = traverseNodes(childNodes.item(i));
                tempJieDian.addAll(JieDianTemp);
            }
            jieDian.setJieDianList(tempJieDian);
            Temp.add(jieDian);
        }
        return Temp;
    }
}