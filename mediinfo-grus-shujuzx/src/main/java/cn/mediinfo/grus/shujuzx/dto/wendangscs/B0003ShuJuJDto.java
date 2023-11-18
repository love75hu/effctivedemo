package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.util.Date;

@Data
public class B0003ShuJuJDto {
    /**
     * 城乡居民健康档案编号
     */
    @Schema(description = "城乡居民健康档案编号")
    private String jianKangDABH;
    /**
     * 新生儿家庭访视记录表单编号
     */
    @Schema(description = "新生儿家庭访视记录表单编号")
    private String xinShengEJTFSJLBDBH;
    /**
     * 新生儿姓名
     */
    @Schema(description = "新生儿姓名")
    private String xingMing;
    /**
     * 新生儿性别代码
     */
    @Schema(description = "新生儿性别代码")
    private String xingBieDM;
    /**
     * 新生儿性别
     */
    @Schema(description = "新生儿性别")
    private String xingBieMC;
    /**
     * 新生儿出生日期
     */
    @Schema(description = "新生儿出生日期")
    private Date chuShengRQ;
    /**
     * 新生儿身份证件类别代码
     */
    @Schema(description = "新生儿身份证件类别代码")
    private String zhengJianLBDM;
    /**
     * 新生儿身份证件类别
     */
    @Schema(description = "新生儿身份证件类别")
    private String zhengJianLBMC;
    /**
     * 身份证件号码
     */
    @Schema(description = "身份证件号码")
    private String zhengJianHM;
    /**
     * 父亲姓名
     */
    @Schema(description = "父亲姓名")
    private String fuQinXM;
    /**
     * 父亲出生日期
     */
    @Schema(description = "父亲出生日期")
    private Date fuQinCSRQ;
    /**
     * 父亲电话号码
     */
    @Schema(description = "父亲电话号码")
    private String fuQinDH;
    /**
     * 父亲职业类别代码
     */
    @Schema(description = "父亲职业类别代码")
    private String fuQinZYLBDM;
    /**
     * 父亲职业类别
     */
    @Schema(description = "父亲职业类别")
    private String fuQinZYLBMC;
    /**
     * 母亲姓名
     */
    @Schema(description = "母亲姓名")
    private String muQinXM;
    /**
     * 母亲出生日期
     */
    @Schema(description = "母亲出生日期")
    private Date muQinCSRQ;
    /**
     * 母亲电话号码
     */
    @Schema(description = "母亲电话号码")
    private String muQinDH;
    /**
     * 母亲职业类别代码
     */
    @Schema(description = "母亲职业类别代码")
    private String muQinZYLBDM;
    /**
     * 母亲职业类别
     */
    @Schema(description = "母亲职业类别")
    private String muQinZYLBMC;
    /**
     * 现住地址-省(自治区、直辖市)
     */
    @Schema(description = "现住地址-省(自治区、直辖市)")
    private String xianZhuZSFMC;
    /**
     * 现住地址-市(地区、州)
     */
    @Schema(description = "现住地址-市(地区、州)")
    private String xianZhuZSDQMC;
    /**
     * 现住地址-县(区)
     */
    @Schema(description = "现住地址-县(区)")
    private String xianZhuZXQMC;
    /**
     * 现住地址-乡(镇、街道办事处)
     */
    @Schema(description = "现住地址-乡(镇、街道办事处)")
    private String xianZhuZXZMC;
    /**
     * 现住地址-村(街、路、弄等)
     */
    @Schema(description = "现住地址-村(街、路、弄等)")
    private String xianZhuZCJMC;
    /**
     * 现住地址-门牌号码
     */
    @Schema(description = "现住地址-门牌号码")
    private String xianZhuZQTXX;
    /**
     * 出生孕周
     */
    @Schema(description = "出生孕周")
    private Integer chuShengYZ;
    /**
     * 母亲妊娠合并症/并发症史
     */
    @Schema(description = "母亲妊娠合并症/并发症史")
    private String muQinRSQHBQKMC;
    /**
     * 助产机构名称
     */
    @Schema(description = "助产机构名称")
    private String zhuChanJGMC;
    /**
     * 末次分娩方式代码
     */
    @Schema(description = "末次分娩方式代码")
    private String chuShengQKDM;
    /**
     * 末次分娩方式
     */
    @Schema(description = "末次分娩方式")
    private String chuShengQKMC;
    /**
     * 双胞胎标志
     */
    @Schema(description = "双胞胎标志")
    private Integer shuangDuoTBZ;
    /**
     * 新生儿窒息标志
     */
    @Schema(description = "新生儿窒息标志")
    private Integer zhiXiBZ;
    /**
     * Apgar评分值(分)
     */
    @Schema(description = "Apgar评分值(分)")
    private Integer APGAR;
    /**
     * 新生儿畸形标志
     */
    @Schema(description = "新生儿畸形标志")
    private Integer jiXingBZ;
    /**
     * 新生儿畸形描述
     */
    @Schema(description = "新生儿畸形描述")
    private String jiXingXS;
    /**
     * 新生儿听力筛查情况代码
     */
    @Schema(description = "新生儿听力筛查情况代码")
    private String xinShengETLSCQK;
    /**
     * 新生儿听力筛查情况
     */
    @Schema(description = "新生儿听力筛查情况")
    private String xinShengETLSCQKMC;
    /**
     * 新生儿听力筛查结果代码
     */
    @Schema(description = "新生儿听力筛查结果代码")
    private String tingLiSCDM;
    /**
     * 新生儿听力筛查结果
     */
    @Schema(description = "新生儿听力筛查结果")
    private String tingLiSCMC;
    /**
     * 新生儿疾病筛查项目代码
     */
    @Schema(description = "新生儿疾病筛查项目代码")
    private String jiBingSCDM;
    /**
     * 新生儿疾病筛查项目
     */
    @Schema(description = "新生儿疾病筛查项目")
    private String jiBingSCMC;
    /**
     * 出生体重(g)
     */
    @Schema(description = "出生体重(g)")
    private Decimal chuShengTZ;
    /**
     * 出生身长(cm)
     */
    @Schema(description = "出生身长(cm)")
    private Decimal chuShengSC;
    /**
     * 体重(kg)
     */
    @Schema(description = "体重(kg)")
    private Decimal muQianTZ;
    /**
     * 喂养方式类别代码
     */
    @Schema(description = "喂养方式类别代码")
    private String weiYangFSDM;
    /**
     * 喂养方式类别
     */
    @Schema(description = "喂养方式类别")
    private String weiYangFSMC;
    /**
     * 每天吃奶次数
     */
    @Schema(description = "每天吃奶次数")
    private Integer chiNaiCS;
    /**
     * 每天吃奶量(mL)
     */
    @Schema(description = "每天吃奶量(mL)")
    private Integer chiNaiL;
    /**
     * 呕吐标志
     */
    @Schema(description = "呕吐标志")
    private Integer ouTuBZ;
    /**
     * 新生儿大便性状代码
     */
    @Schema(description = "新生儿大便性状代码")
    private String daBianXZDM;
    /**
     * 新生儿大便性状
     */
    @Schema(description = "新生儿大便性状")
    private String daBianXZMC;
    /**
     * 大便次数(次/d)
     */
    @Schema(description = "大便次数(次/d)")
    private Decimal daBianCS;
    /**
     * 体温(℃)
     */
    @Schema(description = "脉率(次/min)")
    private Decimal tiWen;
    /**
     * 脉率(次/min)
     */
    @Schema(description = "脉率(次/min)")
    private Integer xinLv;
    /**
     * 呼吸频率(次/min)
     */
    @Schema(description = "呼吸频率(次/min)")
    private Integer huXiPL;
    /**
     * 新生儿面色代码
     */
    @Schema(description = "新生儿面色代码")
    private String mianSeDM;
    /**
     * 新生儿面色
     */
    @Schema(description = "新生儿面色")
    private String mianSeMC;
    /**
     * 黄疸部位代码
     */
    @Schema(description = "黄疸部位代码")
    private String huangDanBWDM;
    /**
     * 黄疸部位
     */
    @Schema(description = "黄疸部位")
    private String huangDanBWMC;
    /**
     * 前囟横径(cm)
     */
    @Schema(description = "前囟横径(cm)")
    private String qianXin1;
    /**
     * 前囟纵径(cm)
     */
    @Schema(description = "前囟纵径(cm)")
    private String qianXin2;
    /**
     * 前囟张力代码
     */
    @Schema(description = "前囟张力代码")
    private String qianXinPJDM;
    /**
     * 前囟张力
     */
    @Schema(description = "前囟张力")
    private String qianXinPJMC;
    /**
     * 眼外观检查异常标志
     */
    @Schema(description = "眼外观检查异常标志")
    private String yanJingQKDM;
    /**
     * 眼外观检查异常结果描述
     */
    @Schema(description = "眼外观检查异常结果描述")
    private String yanJingYCXS;
    /**
     * 耳外观检查异常标志
     */
    @Schema(description = "耳外观检查异常标志")
    private String erWaiGQKDM;
    /**
     * 耳外观检查异常结果描述
     */
    @Schema(description = "耳外观检查异常结果描述")
    private String erWaiGYCXS;
    /**
     * 鼻检查异常标志
     */
    @Schema(description = "鼻检查异常标志")
    private String biBuQKDM;
    /**
     * 鼻检查异常结果描述
     */
    @Schema(description = "鼻检查异常结果描述")
    private String biBuYCXS;
    /**
     * 口腔检查异常标志
     */
    @Schema(description = "口腔检查异常标志")
    private String kouQiangQKDM;
    /**
     * 口腔检查异常结果描述
     */
    @Schema(description = "口腔检查异常结果描述")
    private String kouQiangYCXS;
    /**
     * 肺部听诊异常标志
     */
    @Schema(description = "肺部听诊异常标志")
    private String feiBuTZQKDM;
    /**
     * 肺部听诊异常结果描述
     */
    @Schema(description = "肺部听诊异常结果描述")
    private String feiBuTZYCXS;
    /**
     * 心脏听诊异常标志
     */
    @Schema(description = "心脏听诊异常标志")
    private String xinZangTZQKDM;
    /**
     * 心脏听诊异常结果描述
     */
    @Schema(description = "心脏听诊异常结果描述")
    private String xinZangTZYCXS;
    /**
     * 腹部触诊异常标志
     */
    @Schema(description = "腹部触诊异常标志")
    private String fuBuCZQKDM;
    /**
     * 腹部触诊异常结果描述
     */
    @Schema(description = "腹部触诊异常结果描述")
    private String fuBuCZYCXS;
    /**
     * 四肢活动度异常标志
     */
    @Schema(description = "四肢活动度异常标志")
    private String siZhiHDDQKDM;
    /**
     * 四肢活动度异常结果描述
     */
    @Schema(description = "四肢活动度异常结果描述")
    private String siZhiHDDYCXS;
    /**
     * 颈部包块标志
     */
    @Schema(description = "颈部包块标志")
    private Integer jinBuBKBZ;
    /**
     * 颈部包块结果描述
     */
    @Schema(description = "颈部包块结果描述")
    private String jinBuBKXS;
    /**
     * 皮肤检查结果代码
     */
    @Schema(description = "皮肤检查结果代码")
    private String piFuDM;
    /**
     * 皮肤检查结果
     */
    @Schema(description = "皮肤检查结果")
    private String piFuMC;
    /**
     * 肛门检查异常标志
     */
    @Schema(description = "肛门检查异常标志")
    private String gangMenQKDM;
    /**
     * 肛门检查异常结果描述
     */
    @Schema(description = "肛门检查异常结果描述")
    private String gangMenYCXS;
    /**
     * 外生殖器检查异常标志
     */
    @Schema(description = "外生殖器检查异常标志")
    private String waiShengZQQKDM;
    /**
     * 外生殖器检查异常结果描述
     */
    @Schema(description = "外生殖器检查异常结果描述")
    private String waiShengZQYCXS;
    /**
     * 脊柱检查异常标志
     */
    @Schema(description = "脊柱检查异常标志")
    private String jiZhuQKDM;
    /**
     * 脊柱检查异常结果描述
     */
    @Schema(description = "脊柱检查异常结果描述")
    private String jiZhuYCXS;
    /**
     * 脐带检查结果代码
     */
    @Schema(description = "脐带检查结果代码")
    private String qiDaiDM;
    /**
     * 脐带检查结果
     */
    @Schema(description = "脐带检查结果")
    private String qiDaiMC;
    /**
     * 转诊标志
     */
    @Schema(description = "转诊标志")
    private Integer zhuanZhenBZ;
    /**
     * 转入医疗机构名称
     */
    @Schema(description = "转入医疗机构名称")
    private String zhuanRuJGMC;
    /**
     * 转入机构科室名称
     */
    @Schema(description = "转入机构科室名称")
    private String zhuanRuKSMC;
    /**
     * 转诊原因
     */
    @Schema(description = "转诊原因")
    private String zhuanZhenYYMC;
    /**
     * 新生儿访视健康指导类别代码
     */
    @Schema(description = "新生儿访视健康指导类别代码")
    private String zhiDaoDM;
    /**
     * 新生儿访视健康指导类别
     */
    @Schema(description = "新生儿访视健康指导类别")
    private String zhiDaoMC;
    /**
     * 访视医师姓名
     */
    @Schema(description = "访视医师姓名")
    private String suiFangYSXM;
    /**
     * 本次访视日期
     */
    @Schema(description = "本次访视日期")
    private Date suiFangRQ;
    /**
     * 下次访视日期
     */
    @Schema(description = "下次访视日期")
    private Date xiaCiSFRQ;

    /**
     * 下次访视地点
     */
    @Schema(description = "下次访视地点")
    private String xiaCiSFDD;
    /**
     * 脐带脱落标志
     */
    @Schema(description = "脐带脱落标志")
    private String qiDaiTLBZ;
}
