package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class B0001ShuJuJDto {

    /**
     * 城乡居民健康档案编号
     */
    @Schema(description = "城乡居民健康档案编号")
    private String jianKangDABH;
    /**
     * 本人姓名
     */
    @Schema(description = "本人姓名")
    private String xingMing;
    /**
     * 性别代码
     */
    @Schema(description = "性别代码")
    private String xingBieDM;
    /**
     * 性别名称
     */
    @Schema(description = "性别名称")
    private String xingBieMC;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "出生日期")
    private Date chuShengRQ;
    /**
     * 手术史标志
     */
    @Schema(description = "手术史标志")
    private Integer jiWangSSSBZ;
    /**
     * 输血史标志
     */
    @Schema(description = "输血史标志")
    private Integer jiWangSXSBZ;
    /**
     * 身份证件类别代码
     */
    @Schema(description = "身份证件类别代码")
    private String zhengJianLBDM;
    /**
     * 身份证件类别名称
     */
    @Schema(description = "身份证件类别名称")
    private String zhengJianLBMC;
    /**
     * 身份证件号码
     */
    @Schema(description = "身份证件号码")
    private String zhengJianHM;
    /**
     * 工作单位名称
     */
    @Schema(description = "工作单位名称")
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
     * 联系人电话号码
     */
    @Schema(description = "联系人电话号码")
    private String lianXiRDH;
    /**
     * 常住地址户籍标志
     */
    @Schema(description = "常住地址户籍标志")
    private String changZhuLXDM;
    /**
     * 民族
     */
    @Schema(description = "民族")
    private String minZuDM;
    /**
     * 民族
     */
    @Schema(description = "民族")
    private String minZuMC;
    /**
     * ABO血型代码
     */
    @Schema(description = "ABO血型代码")
    private String ABODM;
    /**
     * ABO血型名称
     */
    @Schema(description = "ABO血型名称")
    private String ABOMC;
    /**
     * Rh血型代码
     */
    @Schema(description = "Rh血型代码")
    private String RHDM;
    /**
     * Rh血型名称
     */
    @Schema(description = "Rh血型名称")
    private String RHMC;
    /**
     * 学历代码
     */
    @Schema(description = "学历代码")
    private String xueLiDM;
    /**
     * 学历名称
     */
    @Schema(description = "学历名称")
    private String xueLiMC;
    /**
     * 职业类别代码
     */
    @Schema(description = "职业类别代码")
    private String zhiYeDM;
    /**
     * 职业类别名称
     */
    @Schema(description = "职业类别名称")
    private String zhiYeMC;
    /**
     * 婚姻状况代码
     */
    @Schema(description = "婚姻状况代码")
    private String hunYinDM;
    /**
     * 婚姻状况名称
     */
    @Schema(description = "婚姻状况名称")
    private String hunYinMC;
    /**
     * 医疗费用支付方式代码
     */
    @Schema(description = "医疗费用支付方式代码")
    private String yiLiaoFYZFFSDM;
    /**
     * 医疗费用支付方式名称
     */
    @Schema(description = "医疗费用支付方式名称")
    private String yiLiaoFYZFFSMC;
    /**
     * 药物过敏史标志
     */
    @Schema(description = "药物过敏史标志")
    private String yaoWuGMSBZ;
    /**
     * 外伤史标志
     */
    @Schema(description = "外伤史标志")
    private Integer jiWangWSSBZ;

    /*
     * 过敏史
     */
    private List<B0001_GuoMinShiDto> GuoMinSList;
    /*
     * 暴露史
     */
    private  List<B0001_BaoLuShiDto>BaoLuSList;
    /*
     * 既往史
     */
    private  List<B0001_JiWangShiDto>JiWangSList;
    /*
     * 手术史
     */
    private  List<B0001_ShouShuShiDto>ShouShuSList;
    /*
     * 外伤史
     */
    private  List<B0001_WaiShangShiDto>WaiShangSList;
    /*
     * 输血史
     */
    private  List<B0001_ShuXueShiDto>ShuXueSList;
    /*
     * 家族史
     */
    private  List<B0001_JiaZuShiDto>JiaZuSList;

    /*
     * 残疾情况
     */
    private  List<B0001_CanJiQKDto>CanJiQKList;
    /**
     * 患者与本人关系代码
     */
    @Schema(description = "患者与本人关系代码")
    private String guanXiDM;
    /**
     * 患者与本人关系
     */
    @Schema(description = "患者与本人关系")
    private String guanXiDMMC;
    /**
     * 遗传性疾病史
     */
    @Schema(description = "遗传性疾病史")
    private String yiChuanBSMC;


    /**
     * 家庭厨房排风设施标识
     */
    @Schema(description = "家庭厨房排风设施标识")
    private String chuFangPFSSBS;
    /**
     * 家庭厨房排风设施类别代码
     */
    @Schema(description = "家庭厨房排风设施类别代码")
    private String chuFangPFSSLBDM;
    /**
     * 家庭厨房排风设施类别
     */
    @Schema(description = "家庭厨房排风设施类别")
    private String chuFangPFSSLBMC;
    /**
     * 家庭燃料类型类别代码
     */
    @Schema(description = "家庭燃料类型类别代码")
    private String ranLiaoLXLBDM;
    /**
     * 家庭燃料类型类别
     */
    @Schema(description = "家庭燃料类型类别")
    private String ranLiaoLXLBMC;
    /**
     * 家庭饮水类别代码
     */
    @Schema(description = "家庭饮水类别代码")
    private String yinShuiLBDM;
    /**
     * 家庭饮水类别
     */
    @Schema(description = "家庭饮水类别")
    private String yinShuiLBMC;
    /**
     * 家庭厕所类别代码
     */
    @Schema(description = "家庭厕所类别代码")
    private String jiaTingCSLBDM;
    /**
     * 家庭厕所类别
     */
    @Schema(description = "家庭厕所类别")
    private String jiaTingCSLBMC;
    /**
     * 家庭禽畜栏类别
     */
    @Schema(description = "家庭禽畜栏类别")
    private String qinChuLLBDM;
    /**
     * 家庭禽畜栏类别
     */
    @Schema(description = "家庭禽畜栏类别")
    private String qinChuLLBMC;
}
