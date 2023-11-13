package cn.mediinfo.grus.shujuzx.dto.cda.dangan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 档案_个案_基本信息
 */
@Data
public class DA_GA_JiBenXXDto {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    public String id;
    /**
     * 组织机构ID
     */
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    /**
     * 组织机构名称
     */
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    /**
     * 健康档案编号
     */
    @Schema(description = "健康档案编号")
    private String jianKangDABH;

    /**
     * 患者主索引
     */
    @Schema(description = "患者主索引")
    private String MPI;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String xingMing;

    /**
     * 姓名全拼
     */
    @Schema(description = "姓名全拼")
    private String xingMingQP;

    /**
     * 性别代码[0460]
     */
    @Schema(description = "性别代码[0460]")
    private String xingBieDM;

    /**
     * 性别名称
     */
    @Schema(description = "性别名称")
    private String xingBieMC;

    /**
     * 证件类别代码[0001]
     */
    @Schema(description = "证件类别代码[0001]")
    private String zhengJianLBDM;

    /**
     * 证件类别名称
     */
    @Schema(description = "证件类别名称")
    private String zhengJianLBMC;

    /**
     * 证件号码
     */
    @Schema(description = "证件号码")
    private String zhengJianHM;

    /**
     * 出生日期
     */
    @Schema(description = "出生日期")
    private Date chuShengRQ;

    /**
     * 工作单位
     */
    @Schema(description = "工作单位")
    private String gongZuoDW;

    /**
     * 本人电话号码
     */
    @Schema(description = "本人电话号码")
    private String dianHuaHM;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    private String lianXiRXM;

    /**
     * 联系人电话
     */
    @Schema(description = "联系人电话")
    private String lianXiRDH;

    /**
     * 常住类型代码[DA0001]
     */
    @Schema(description = "常住类型代码[DA0001]")
    private String changZhuLXDM;

    /**
     * 常住类型名称
     */
    @Schema(description = "常住类型名称")
    private String changZhuLXMC;

    /**
     * 与户主关系代码[0465]
     */
    @Schema(description = "与户主关系代码[0465]")
    private String yuHuZGXDM;

    /**
     * 与户主关系名称
     */
    @Schema(description = "与户主关系名称")
    private String yuHuZGXMC;

    /**
     * 国籍代码[0463]
     */
    @Schema(description = "国籍代码[0463]")
    private String guoJiDM;

    /**
     * 国籍名称
     */
    @Schema(description = "国籍名称")
    private String guoJiMC;

    /**
     * 民族代码[0454]
     */
    @Schema(description = "民族代码[0454]")
    private String minZuDM;

    /**
     * 民族名称
     */
    @Schema(description = "民族名称")
    private String minZuMC;

    /**
     * ABO血型代码[0085]
     */
    @Schema(description = "ABO血型代码[0085]")
    private String ABODM;

    /**
     * ABO血型信息
     */
    @Schema(description = "ABO血型信息")
    private String ABOMC;

    /**
     * RH血型代码[0100]
     */
    @Schema(description = "RH血型代码[0100]")
    private String RHDM;

    /**
     * RH血型信息
     */
    @Schema(description = "RH血型信息")
    private String RHMC;

    /**
     * 学历代码[0464]
     */
    @Schema(description = "学历代码[0464]")
    private String xueLiDM;

    /**
     * 学历名称
     */
    @Schema(description = "学历名称")
    private String xueLiMC;

    /**
     * 职业代码[0462]
     */
    @Schema(description = "职业代码[0462]")
    private String zhiYeDM;

    /**
     * 职业名称
     */
    @Schema(description = "职业名称")
    private String zhiYeMC;

    /**
     * 婚姻代码[0461]
     */
    @Schema(description = "婚姻代码[0461]")
    private String hunYinDM;

    /**
     * 婚姻名称
     */
    @Schema(description = "婚姻名称")
    private String hunYinMC;

    /**
     * 户口地址信息
     */
    @Schema(description = "户口地址信息")
    private String huKouDZXX;

    /**
     * 户口地址省份代码(国家统一编码)[0459]
     */
    @Schema(description = "户口地址省份代码(国家统一编码)[0459]")
    private String huKouDZSFDM;

    /**
     * 户口地址省份名称
     */
    @Schema(description = "户口地址省份名称")
    private String huKouDZSFMC;

    /**
     * 户口地址市地区代码(国家统一编码)[0459]
     */
    @Schema(description = "户口地址市地区代码(国家统一编码)[0459]")
    private String huKouDZSDQDM;

    /**
     * 户口地址市地区名称
     */
    @Schema(description = "户口地址市地区名称")
    private String huKouDZSDQMC;

    /**
     * 户口地址县区代码(国家统一编码)[0459]
     */
    @Schema(description = "户口地址县区代码(国家统一编码)[0459]")
    private String huKouDZXQDM;

    /**
     * 户口地址县区名称
     */
    @Schema(description = "户口地址县区名称")
    private String huKouDZXQMC;

    /**
     * 户口地址乡镇代码(标准编码)[0478]
     */
    @Schema(description = "户口地址乡镇代码(标准编码)[0478]")
    private String huKouDZXZDM;

    /**
     * 户口地址乡镇名称
     */
    @Schema(description = "户口地址乡镇名称")
    private String huKouDZXZMC;

    /**
     * 户口地址村级代码(标准编码)[0479]
     */
    @Schema(description = "户口地址村级代码(标准编码)[0479]")
    private String huKouDZCJDM;

    /**
     * 户口地址村级名称
     */
    @Schema(description = "户口地址村级名称")
    private String huKouDZCJMC;

    /**
     * 户口地址其他信息(如：门牌代码等）
     */
    @Schema(description = "户口地址其他信息(如：门牌代码等）")
    private String huKouDZQTXX;

    /**
     * 现住址信息
     */
    @Schema(description = "现住址信息")
    private String xianZhuZXX;

    /**
     * 现住址省份代码(国家统一编码)[0459]
     */
    @Schema(description = "现住址省份代码(国家统一编码)[0459]")
    private String xianZhuZSFDM;

    /**
     * 现住址省份名称
     */
    @Schema(description = "现住址省份名称")
    private String xianZhuZSFMC;

    /**
     * 现住址市地区代码(国家统一编码)[0459]
     */
    @Schema(description = "现住址市地区代码(国家统一编码)[0459]")
    private String xianZhuZSDQDM;

    /**
     * 现住址市地区名称
     */
    @Schema(description = "现住址市地区名称")
    private String xianZhuZSDQMC;

    /**
     * 现住址县区代码(国家统一编码)[0459]
     */
    @Schema(description = "现住址县区代码(国家统一编码)[0459]")
    private String xianZhuZXQDM;

    /**
     * 现住址县区名称
     */
    @Schema(description = "现住址县区名称")
    private String xianZhuZXQMC;

    /**
     * 现住址乡镇代码(标准编码)[0478]
     */
    @Schema(description = "现住址乡镇代码(标准编码)[0478]")
    private String xianZhuZXZDM;

    /**
     * 现住址乡镇名称
     */
    @Schema(description = "现住址乡镇名称")
    private String xianZhuZXZMC;

    /**
     * 现住址村级代码(标准编码)[0479]
     */
    @Schema(description = "现住址村级代码(标准编码)[0479]")
    private String xianZhuZCJDM;

    /**
     * 现住址村级名称
     */
    @Schema(description = "现住址村级名称")
    private String xianZhuZCJMC;

    /**
     * 现住址其他信息(如：门牌代码等）
     */
    @Schema(description = "现住址其他信息(如：门牌代码等）")
    private String xianZhuZQTXX;

    /**
     * 现住址邮编
     */
    @Schema(description = "现住址邮编")
    private String xianZhuZYB;

    /**
     * 家庭档案ID
     */
    @Schema(description = "家庭档案ID")
    private String jiaTingDAID;

    /**
     * 网格ID
     */
    @Schema(description = "网格ID")
    private String wangGeID;

    /**
     * 网格名称
     */
    @Schema(description = "网格名称")
    private String wangGeMC;

    /**
     * 医疗费用支付方式代码[0223] 多个用 | 分割
     */
    @Schema(description = "医疗费用支付方式代码[0223] 多个用 | 分割")
    private String yiLiaoFYZFFSDM;

    /**
     * 医疗费用支付方式名称 多个用 | 分割
     */
    @Schema(description = "医疗费用支付方式名称 多个用 | 分割")
    private String yiLiaoFYZFFSMC;

    /**
     * 医疗费用支付方式详述（方式为其他是此项才有值）
     */
    @Schema(description = "医疗费用支付方式详述（方式为其他是此项才有值）")
    private String yiLiaoFYZFFSXS;

    /**
     * 药物过敏史标志(0-无；1-有)
     */
    @Schema(description = "药物过敏史标志(0-无；1-有)")
    private Integer yaoWuGMSBZ;

    /**
     * 既往疾病史标志(0-无；1-有)
     */
    @Schema(description = "既往疾病史标志(0-无；1-有)")
    private Integer jiWangJBSBZ;

    /**
     * 既往手术史标志(0-无；1-有)
     */
    @Schema(description = "既往手术史标志(0-无；1-有)")
    private Integer jiWangSSSBZ;

    /**
     * 既往外伤史标志(0-无；1-有)
     */
    @Schema(description = "既往外伤史标志(0-无；1-有)")
    private Integer jiWangWSSBZ;

    /**
     * 既往输血史标志(0-无；1-有)
     */
    @Schema(description = "既往输血史标志(0-无；1-有)")
    private Integer jiWangSXSBZ;

    /**
     * 遗传病史标志(0-无；1-有)
     */
    @Schema(description = "遗传病史标志(0-无；1-有)")
    private Integer yiChuanBSBZ;

    /**
     * 遗传病史名称(当遗传病史标志=1时必填)
     */
    @Schema(description = "遗传病史名称(当遗传病史标志=1时必填)")
    private String yiChuanBSMC;

    /**
     * 家族史标志(0-无；1-有)
     */
    @Schema(description = "家族史标志(0-无；1-有)")
    private Integer jiaZuSBZ;

    /**
     * 暴露史标志(0-无；1-有)
     */
    @Schema(description = "暴露史标志(0-无；1-有)")
    private Integer baoLuSBZ;

    /**
     * 残疾标志(0-无；1-有)
     */
    @Schema(description = "残疾标志(0-无；1-有)")
    private Integer canJiBZ;

    /**
     * 厨房排风设施类别代码[0036]
     */
    @Schema(description = "厨房排风设施类别代码[0036]")
    private String chuFangPFSSLBDM;

    /**
     * 厨房排风设施类别名称
     */
    @Schema(description = "厨房排风设施类别名称")
    private String chuFangPFSSLBMC;

    /**
     * 燃料类型类别代码[0037]
     */
    @Schema(description = "燃料类型类别代码[0037]")
    private String ranLiaoLXLBDM;

    /**
     * 燃料类型类别名称
     */
    @Schema(description = "燃料类型类别名称")
    private String ranLiaoLXLBMC;

    /**
     * 饮水类别代码[0029]
     */
    @Schema(description = "饮水类别代码[0029]")
    private String yinShuiLBDM;

    /**
     * 饮水类别名称
     */
    @Schema(description = "饮水类别名称")
    private String yinShuiLBMC;

    /**
     * 厕所类别代码[0038]
     */
    @Schema(description = "厕所类别代码[0038]")
    private String jiaTingCLLBDM;

    /**
     * 厕所类别名称
     */
    @Schema(description = "厕所类别名称")
    private String jiaTingCSLBMC;

    /**
     * 禽畜栏类别代码[0294]
     */
    @Schema(description = "禽畜栏类别代码[0294]")
    private String qinChuLLBDM;

    /**
     * 禽畜栏类别名称
     */
    @Schema(description = "禽畜栏类别名称")
    private String qinChuLLBMC;

    /**
     * 个人状态代码[DA0002]
     */
    @Schema(description = "个人状态代码[DA0002]")
    private String geRenZTDM;

    /**
     * 个人状态名称
     */
    @Schema(description = "个人状态名称")
    private String geRenZTMC;

    /**
     * 责任医生ID
     */
    @Schema(description = "责任医生ID")
    private String zeRenYSID;

    /**
     * 责任医生姓名
     */
    @Schema(description = "责任医生姓名")
    private String zeRenYSXM;

    /**
     * 建档时间
     */
    @Schema(description = "建档时间")
    private Date jianDangSJ;

    /**
     * 建档医生ID
     */
    @Schema(description = "建档医生ID")
    private String jianDangYSID;

    /**
     * 建档医生姓名
     */
    @Schema(description = "建档医生姓名")
    private String jianDangYSXM;

    /**
     * 建档机构ID
     */
    @Schema(description = "建档机构ID")
    private String jianDangJGID;

    /**
     * 建档机构名称
     */
    @Schema(description = "建档机构名称")
    private String jianDangJGMC;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String beiZhu;

    /**
     * 数据来源[DA0003]
     */
    @Schema(description = "数据来源[DA0003]")
    private Integer shuJuLY;

    /**
     * 签约标志 0-未签约，1-已签约
     */
    @Schema(description = "签约标志 0-未签约，1-已签约")
    private Integer qianYueBZ;

    /**
     * 签约时间
     */
    @Schema(description = "签约时间")
    private Date qianYueSJ;

    /**
     * 签约医生ID
     */
    @Schema(description = "签约医生ID")
    private String qianYueYSID;

    /**
     * 签约医生姓名
     */
    @Schema(description = "签约医生姓名")
    private String qianYueYSXM;

    /**
     * 专项管理标志[每位对应值1-是 0-否，位顺序待定 老年人|高血压|高血压高危|糖尿病|糖尿病高危|精神病|脑卒中|慢阻肺|肿瘤|冠心病|残疾人康复|孕产妇|儿童|结核病督导|结核病追踪]
     */
    @Schema(description = "专项管理标志[每位对应值1-是 0-否，位顺序待定 老年人|高血压|高血压高危|糖尿病|糖尿病高危|精神病|脑卒中|慢阻肺|肿瘤|冠心病|残疾人康复|孕产妇|儿童|结核病督导|结核病追踪]")
    private String zhuanXiangGLBZ;

    /**
     * 专项管理状态标志[每位对应值1-接收管理 0-暂不管理，位顺序待定老年人|高血压|高血压高危|糖尿病|糖尿病高危|精神病|脑卒中|慢阻肺|肿瘤|冠心病|残疾人康复|孕产妇|儿童|结核病督导|结核病追踪]
     */
    @Schema(description = "专项管理状态标志[每位对应值1-接收管理 0-暂不管理，位顺序待定老年人|高血压|高血压高危|糖尿病|糖尿病高危|精神病|脑卒中|慢阻肺|肿瘤|冠心病|残疾人康复|孕产妇|儿童|结核病督导|结核病追踪]")
    private String zhuanXiangGLZT;

    /**
     * 管理状态代码[1-接受管理 2-暂不管理]
     */
    @Schema(description = "管理状态代码[1-接受管理 2-暂不管理]")
    private String guanLiZTDM;

    /**
     * 管理状态名称
     */
    @Schema(description = "管理状态名称")
    private String guanLiZTMC;

    /**
     * 档案完整度
     */
    @Schema(description = "档案完整度")
    private BigDecimal dangAnWZD;

    /**
     * 规范标志1:-规范，0-不规范, -1 未质控
     */
    @Schema(description = "规范标志1:-规范，0-不规范, -1 未质控")
    private Integer guiFanBZ;

    /**
     * 开放标志:1-开放，0-不开放
     */
    @Schema(description = "开放标志:1-开放，0-不开放")
    private Integer kaiFangBZ;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private String nianLing;

    /**
     * 三色等级代码
     */
    @Schema(description = "三色等级代码")
    private String sanSeDJDM;

    /**
     * 三色等级名称
     */
    @Schema(description = "三色等级名称")
    private String sanSeDJMC;

}
