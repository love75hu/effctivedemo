package cn.mediinfo.grus.shujuzx.controller;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.response.XiTongResponseCode;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDian;
import cn.mediinfo.grus.shujuzx.dto.wenDang.XML_JieDianSX;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.JG_ZZ_JiGouXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.DiZuoRemoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name = "JiaoHuFWController", description = "交互服务")
@RequestMapping({"api/v1.0/JiaoHuFW", "api/v1/JiaoHuFW"})
@Slf4j
@Validated
public class JiaoHuFWController {
    private final DiZuoRemoteService diZuoRemoteService;

    public JiaoHuFWController(DiZuoRemoteService diZuoRemoteService) {
        this.diZuoRemoteService = diZuoRemoteService;
    }

    /**
     * 交互服务请求
     */
    @Operation(summary = "交互服务请求")
    @GetMapping("GetJiaoHuFW")
    public MsfResponse<String> getJiaoHuFW(@RequestParam(required = false) String Action, @RequestParam(required = false) String Message) throws IOException {
        if (!StringUtils.hasText(Action) || !StringUtils.hasText(Message)) {
            return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "Action和Message参数不能为空！");
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        String actionDM = "";
        String actionMC = GetBiaoZhunMC(Action);
        List<XML_JieDian> jieDianList;
        String resul = "";
        switch (Action) {
            case "IST-MIR1":
                actionDM = "OrganizationFeed";//医疗卫生机构信息提交
                break;
            case "IST-MIR2":
                String CanShu1;
                actionDM = "OrganizationQuery";//医疗卫生机构信息查询

                //#region 解析入参XML为实体，获取参数

                //XML字符串转Document类型
                Document document = strToDoc(Message);
                //判断是否转成功
                if (document == null) {
                    return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "Message参数格式错误！");
                }
                //XML转实体
                jieDianList = xmlToDto(document.getDocumentElement());
                try {
                    //这块取参需要根据实际XML转为的实体类一级一级找到位置取出来（每个接口的不一样，根据实际情况取去，需要先调试到实体才能看出来层级）
                    CanShu1 = jieDianList.get(0).getJieDianList().get(2).getJieDianList().get(1).getJieDianList().get(1).getJieDianList().get(0).getJieDianSXList().get(0).getValue();
                } catch (Exception e) {
                    return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "Message参数格式错误！");
                }
                //#endregion

                //#region 根据参数获取业务数据信息
                JG_ZZ_JiGouXXRso dto = diZuoRemoteService.getZuZhiJGByJGDM(CanShu1).getData();
                if (dto == null) {
                    return MsfResponse.fail(XiTongResponseCode.CANSHUYC, "暂无数据！");
                }
                //#endregion

                //#region 数据信息转为XML格式出参
                String luJing = "src/main/java/cn/mediinfo/grus/shujuzx/xmls/jhfw/医疗卫生机构信息查询-T01响应.xml";
                List<String> XmlStrList = Files.readAllLines(Path.of(luJing));
                StringBuilder xmlStr = new StringBuilder();
                if (XmlStrList.get(0).contains("<?xml")) {
                    XmlStrList.remove(0);
                }
                for (String Str : XmlStrList) {
                    xmlStr.append(Str);
                }
                resul = xmlStr.toString();
                resul = resul.replace("@JiGouDJDM@", IsNull(dto.getJiGouDJDM()));
                resul = resul.replace("@JiGouDJMC@", IsNull(dto.getJiGouDJMC()));
                resul = resul.replace("@YiLiaoJGDM@", IsNull(dto.getZuZhiJGID()));
                resul = resul.replace("@YiLiaoJGMC@", IsNull(dto.getZuZhiJGMC()));
                resul = resul.replace("@XiangXiDZ@", IsNull(dto.getXiangXiDZ()));
                resul = resul.replace("@DiZhiSFDM@", IsNull(dto.getDiZhiSFDM()));
                resul = resul.replace("@DiZhiSFMC@", IsNull(dto.getDiZhiSFMC()));
                resul = resul.replace("@DiZhiSDQMC@", IsNull(dto.getDiZhiSDQMC()));
                resul = resul.replace("@DiZhiXQMC@", IsNull(dto.getDiZhiXQMC()));
                resul = resul.replace("@DiZhiXZJDMC@", IsNull(dto.getDiZhiXZJDMC()));
                resul = resul.replace("@DiZhiQT@", IsNull(dto.getDiZhiQT()));
                resul = resul.replace("@YouBian@", IsNull(dto.getYouBian()));
                resul = resul.replace("@LianXiDH@", IsNull(dto.getLianXiDH()));
                resul = resul.replace("@DateTime@", time);
                //#endregion
                break;
        }
        return MsfResponse.success(resul);
    }

    /**
     * XML字符串转Document
     */
    public static Document strToDoc(String xmlString) {
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
    public static List<XML_JieDian> xmlToDto(Node node) {
        List<XML_JieDian> Temp = new ArrayList<>();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            //转类型
            Element element = (Element) node;
            //获取属性
            NamedNodeMap attributes = element.getAttributes();
            //创建接收节点类
            XML_JieDian jieDian = new XML_JieDian();
            //节点名称赋值
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
                List<XML_JieDian> JieDianTemp = xmlToDto(childNodes.item(i));
                tempJieDian.addAll(JieDianTemp);
            }
            jieDian.setJieDianList(tempJieDian);
            Temp.add(jieDian);
        }
        return Temp;
    }

    /**
     * 标准名称转换
     */
    public static String GetBiaoZhunMC(String action) {
        String actionMC = "";
        switch (action) {
            case "IST-NA1":
                actionMC = "节点验证";
                break;
            case "IST-NA2":
                actionMC = "访问权限验证";
                break;
            case "IST-AT1":
                actionMC = "记录审计事件";
                break;
            case "IST-DSP6":
                actionMC = "通知订阅";//通知订阅
                break;
            case "IST-BN2":
                actionMC = "取消订阅";//取消订阅
                break;
            case "IST-BN3":
                actionMC = "暂停订阅";//暂停订阅
                break;
            case "IST-BN4":
                actionMC = "恢复订阅";//恢复订阅
                break;
            case "IST-BN6":
                actionMC = "通知";//通知
                break;
            case "IST-BN5":
                actionMC = "文档发布";//文档发布
                break;
            case "IST-DSP9":
                actionMC = "创建通知拉取点";//创建通知拉取点
                break;
            case "IST-DSP7":
                actionMC = "移除通知拉取点";//移除通知拉取点
                break;
            case "IST-DSP8":
                actionMC = "拉取通知";//拉取通知
                break;
            case "IST-PR1":
                actionMC = "居民信息提交";//居民信息提交
                break;
            case "IST-PR2":
                actionMC = "居民信息查询";//居民信息查询
                break;
            case "IST-PR3":
                actionMC = "居民交叉索引查询服务";//居民交叉索引查询服务
                break;
            case "IST-PR4":
                actionMC = "居民信息订阅";//居民信息订阅
                break;
            case "IST-PR5":
                actionMC = "居民信息变更通知";//居民信息变更通知
                break;
            case "IST-PR6":
                actionMC = "居民信息索引变更通知";//居民交叉索引查询服务
                break;
            case "IST-PR7":
                actionMC = "居民信息索引合并通知";//居民信息索引合并通知
                break;
            case "IST-MIR1":
                actionMC = "医疗卫生机构信息提交";//医疗卫生机构信息提交
                break;
            case "IST-MIR2":
                actionMC = "医疗卫生机构信息查询";//医疗卫生机构信息查询
                break;
            case "IST-MIR3":
                actionMC = "医疗卫生机构信息订阅";//医疗卫生机构信息订阅
                break;
            case "IST-MIR4":
                actionMC = "医疗卫生机构信息变更通知";//医疗卫生机构信息变更通知
                break;
            case "IST-MSR1":
                actionMC = "医疗卫生人员信息提交";//医疗卫生人员信息提交
                break;
            case "IST-MSR2":
                actionMC = "医疗卫生人员信息查询";//医疗卫生人员信息查询
                break;
            case "IST-MSR3":
                actionMC = "医疗卫生人员信息订阅";//医疗卫生人员信息订阅
                break;
            case "IST-MSR4":
                actionMC = "医疗卫生人员信息变更通知";//医疗卫生人员信息变更通知
                break;
            case "IST-TR1":
                actionMC = "获取值集";//获取值集
                break;
            case "IST-TR2":
                actionMC = "查询值集";//查询值集
                break;
            case "IST-TR3":
                actionMC = "获取值集映射";//获取值集映射
                break;
            case "IST-TR4":
                actionMC = "获取值集映射";//获取值集映射
                break;
            case "IST-DS1":
                actionMC = "提交健康档案";//提交健康档案
                break;
            case "IST-DS2":
                actionMC = "注册健康档案";//提交健康档案
                break;
            case "IST-DS3":
                actionMC = "更新健康档案文档元数据";//更新健康档案文档元数据
                break;
            case "IST-DS5":
                actionMC = "获取健康档案";//获取健康档案
                break;
            case "TST-DS4":
            case "IST-DS4":
                actionMC = "检索健康档案";//检索健康档案  //检索健康档案索引
                break;
            case "IST-DSP1":
                actionMC = "文档订阅";//文档订阅
                break;
            case "IST-DSP2":
                actionMC = "取消文档订阅";//取消文档订阅
                break;
            case "IST-DSP3":
                actionMC = "暂停文档订阅";//暂停文档订阅
                break;
            case "IST-DSP4":
                actionMC = "恢复文档订阅";//恢复文档订阅
                break;
            case "IST-DSP5":
                actionMC = "文档发布";//文档发布
                break;
            case "IST-ARS2":
                actionMC = "预约排班信息提交"; //预约排班信息提交
                break;
            case "IST-ARS3":
                actionMC = "预约排班信息更新"; //预约排班信息更新
                break;
            case "IST-ARS4":
                actionMC = "预约排班信息删除"; //预约排班信息删除
                break;
            case "IST-ARS5":
                actionMC = "预约排班信息查询"; //预约排班信息查询
                break;
            case "IST-ARS6":
                actionMC = "预约申请"; //预约申请
                break;
            case "IST-ARS11":
                actionMC = "预约申请通知"; //预约申请通知
                break;
            case "IST-ARS12":
                actionMC = "预约取消通知"; //预约取消通知
                break;
            case "IST-ARS7":
                actionMC = "取消预约申请"; //取消预约申请
                break;
            case "IST-ARS8":
                actionMC = "预约申请查询"; //预约申请查询
                break;
            case "IST-ARS9":
                actionMC = "预约排班信息更新通知"; //预约排班信息更新通知
                break;
            case "IST-ARS10":
                actionMC = "预约排班信息删除通知"; //预约排班信息删除通知
                break;
            case "IST-TRS1":
                actionMC = "双向转诊申请"; //双向转诊申请
                break;
            case "IST-TRS2":
                actionMC = "双向转诊取消"; //双向转诊取消
                break;
            case "IST-TRS3":
                actionMC = "双向转诊查询"; //双向转诊查询
                break;
            case "IST-TRS4":
                actionMC = "双向转诊回执"; //双向转诊回执
                break;
            case "IST-TRS6":
                actionMC = "双向转诊回执通知"; //双向转诊回执通知
                break;
            case "IST-TRS7":
                actionMC = "双向转诊申请通知"; //双向转诊申请通知
                break;
            case "IST-TRS8":
                actionMC = "取消双向转诊申请通知"; //取消双向转诊申请通知
                break;
            case "IST-CS1":
                actionMC = "签约团队注册"; //签约团队注册
                break;
            case "IST-CS2":
                actionMC = "签约团队查询"; //签约团队查询
                break;
            case "IST-CS3":
                actionMC = "签约医生注册"; //签约医生注册
                break;
            case "IST-CS4":
                actionMC = "签约医生查询"; //签约医生查询
                break;
            case "IST-CS5":
                actionMC = "签约项目注册"; //签约项目注册
                break;
            case "IST-CS6":
                actionMC = "签约项目查询"; //签约项目查询
                break;
            case "IST-CS7":
                actionMC = "签约记录注册"; //签约记录注册
                break;
            case "IST-CS8":
                actionMC = "签约记录查询"; //签约记录查询
                break;
            default:
                break;
        }
        return actionMC;
    }

    /**
     * 为空转义 
     */
    public static String IsNull(String obj) {
        if (!StringUtils.hasText(obj)) {
            return "";
        }
        return obj;
    }
}