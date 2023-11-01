package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class JG_ZZ_JiGouXXRso {

    /**
     *组织机构ID
     */
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    /**
     *组织机构名称
     */
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    /**
     *标准编码
     */
    @Schema(description = "标准编码")
    private String biaoZhunBM;

    /**
     *简称
     */
    @Schema(description = "简称")
    private String jianChenG;

    /**
     *英文名
     */
    @Schema(description = "英文名")
    private String yingWenMing;

    /**
     *机构级数代码[5001]
     */
    @Schema(description = "机构级数代码[5001]")
    private String jiGouJSDM;

    /**
     *机构级数名称
     */
    @Schema(description = "机构级数名称")
    private String jiGouJSMC;

    /**
     *机构等级代码[5002]
     */
    @Schema(description = "机构等级代码[5002]")
    private String jiGouDJDM;

    /**
     *机构等级名称
     */
    @Schema(description = "机构等级名称")
    private String jiGouDJMC;

    /**
     *机构类别代码[0475]daimajb=0
     */
    @Schema(description = "机构类别代码[0475]daimajb=0")
    private String jiGouLBDM;

    /**
     *机构类别名称
     */
    @Schema(description = "机构类别名称")
    private String jiGouLBMC;

    /**
     *服务机构类型(1:省级管理机构，2：地市级管理机构，3：区县级管理机构，4：服务中心，5：服务站，6：医院，7：其他机构
     */
    @Schema(description = "服务机构类型(1:省级管理机构，2：地市级管理机构，3：区县级管理机构，4：服务中心，5：服务站，6：医院，7：其他机构")
    private Integer jiGouLX;

    /**
     *联系电话
     */
    @Schema(description = "联系电话")
    private String lianXiDH;

    /**
     *邮编
     */
    @Schema(description = "邮编")
    private String youBian;

    /**
     *详细地址
     */
    @Schema(description = "详细地址")
    private String xiangXiDZ;

    /**
     *地址省份代码[0459]
     */
    @Schema(description = "地址省份代码[0459]")
    private String diZhiSFDM;

    /**
     *地址省份名称
     */
    @Schema(description = "地址省份名称")
    private String diZhiSFMC;

    /**
     *地址市地区代码[0459]
     */
    @Schema(description = "地址市地区代码[0459]")
    private String diZhiSDQDM;

    /**
     *地址市地区名称
     */
    @Schema(description = "地址市地区名称")
    private String diZhiSDQMC;

    /**
     *地址县区代码[0459]
     */
    @Schema(description = "地址县区代码[0459]")
    private String diZhiXQDM;

    /**
     *地址县区名称
     */
    @Schema(description = "地址县区名称")
    private String diZhiXQMC;

    /**
     *地址乡镇街道代码[0478]
     */
    @Schema(description = "地址乡镇街道代码[0478]")
    private String diZhiXZJDDM;

    /**
     *地址乡镇街道名称
     */
    @Schema(description = "地址乡镇街道名称")
    private String diZhiXZJDMC;

    /**
     *地址其他
     */
    @Schema(description = "地址其他")
    private String diZhiQT;

    /**
     *邮箱
     */
    @Schema(description = "邮箱")
    private String youXiang;

    /**
     *网站
     */
    @Schema(description = "网站")
    private String wangZhan;

    /**
     *机构负责人姓名
     */
    @Schema(description = "机构负责人姓名")
    private String jiGouFZRXM;

    /**
     *机构负责人联系电话
     */
    @Schema(description = "机构负责人联系电话")
    private String jiGouFZRLXDH;

    /**
     *上级医疗机构代码
     */
    @Schema(description = "上级医疗机构代码")
    private String shangJiYLJGDM;

    /**
     *上级医疗机构名
     */
    @Schema(description = "上级医疗机构名")
    private String shangJiYLJGMC;

    /**
     *末级标志
     */
    @Schema(description = "末级标志")
    private Integer moJiBZ;

    /**
     *上级机构代码链,所有上级机构的代码，用‘,’ 分割  如:33,3304,330401
     */
    @Schema(description = "上级机构代码链,所有上级机构的代码，用‘,’ 分割  如:33,3304,330401")
    private String shangJiJGDML;

    /**
     *签约机构标志，1：签约机构，0 非签约机构
     */
    @Schema(description = "签约机构标志，1：签约机构，0 非签约机构")
    private Integer qianYueJGBZ;

    /**
     *体检机构标志 1：为体检机构；0：非体检机构
     */
    @Schema(description = "体检机构标志 1：为体检机构；0：非体检机构")
    private Integer tiJianJGBZ;

    /**
     *输入码1(拼音码)
     */
    @Schema(description = "输入码1(拼音码)")
    private String shuRuMa1;

    /**
     *输入码2(五笔码)
     */
    @Schema(description = "输入码2(五笔码)")
    private String shuRuMa2;

    /**
     *输入码3(自定义码)
     */
    @Schema(description = "输入码3(自定义码)")
    private String shuRuMa3;

    /**
     *顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;

    /**
     *性质属性(每一位代表一个属性,字典定义)
     */
    @Schema(description = "性质属性(每一位代表一个属性,字典定义)")
    private List<String> xingZhiSX;

    /**
     *机构社会信用代码
     */
    @Schema(description = "机构社会信用代码")
    private String jiGouSHXYDM;

    /**
     *机构社会信用名称
     */
    @Schema(description = "机构社会信用名称")
    private String jiGouSHXYMC;

    /**
     *补证机构ID
     */
    @Schema(description = "补证机构ID")
    private String buZhengJGID;

    /**
     *补证机构名称
     */
    @Schema(description = "补证机构名称")
    private String buZhengJGMC;

    /**
     *场景id
     */
    @Schema(description = "场景id")
    private String changJingId;

    /**
     *场景名称
     */
    @Schema(description = "场景名称")
    private String changJingMC;

}
