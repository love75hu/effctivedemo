package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*病人基本信息
*/
@Data
public class BR_DA_JiBenXXByHBXXDto {

    /**
   *单位名称
    */
   @Schema(description = "单位名称")
   private String danWeiMC;

    /**
   *单位电话
    */
   @Schema(description = "单位电话")
   private String danWeiDH;

    /**
   *单位邮编
    */
   @Schema(description = "单位邮编")
   private String danWeiYB;

    /**
   *联系电话
    */
   @Schema(description = "联系电话")
   private String lianXiDH;

    /**
   *联系人姓名
    */
   @Schema(description = "联系人姓名")
   private String lianXiRXM;

    /**
   *与联系人关系代码[0465]
    */
   @Schema(description = "与联系人关系代码[0465]")
   private String guanXiDM;

    /**
   *与联系人关系名称
    */
   @Schema(description = "与联系人关系名称")
   private String guanXiMC;

    /**
   *户口地址信息
    */
   @Schema(description = "户口地址信息")
   private String huKouDZXX;

    /**
   *户口地址省份代码(国家统一编码)[0459]
    */
   @Schema(description = "户口地址省份代码(国家统一编码)[0459]")
   private String huKouDZSFDM;

    /**
   *户口地址省份名称
    */
   @Schema(description = "户口地址省份名称")
   private String huKouDZSFMC;

    /**
   *户口地址市地区代码(国家统一编码)[0459]
    */
   @Schema(description = "户口地址市地区代码(国家统一编码)[0459]")
   private String huKouDZSDQDM;

    /**
   *户口地址市地区名称
    */
   @Schema(description = "户口地址市地区名称")
   private String huKouDZSDQMC;

    /**
   *户口地址县区代码(国家统一编码)[0459]
    */
   @Schema(description = "户口地址县区代码(国家统一编码)[0459]")
   private String huKouDZXQDM;

    /**
   *户口地址县区名称
    */
   @Schema(description = "户口地址县区名称")
   private String huKouDZXQMC;

    /**
   *户口地址乡镇代码(标准编码)[0478]
    */
   @Schema(description = "户口地址乡镇代码(标准编码)[0478]")
   private String huKouDZXZDM;

    /**
   *户口地址乡镇名称
    */
   @Schema(description = "户口地址乡镇名称")
   private String huKouDZXZMC;

    /**
   *户口地址村级代码(标准编码)[0479]
    */
   @Schema(description = "户口地址村级代码(标准编码)[0479]")
   private String huKouDZCJDM;

    /**
   *户口地址村级名称
    */
   @Schema(description = "户口地址村级名称")
   private String huKouDZCJMC;

    /**
   *户口地址其他信息(如：门牌代码等）
    */
   @Schema(description = "户口地址其他信息(如：门牌代码等）")
   private String huKouDZQTXX;

    /**
   *户口邮编
    */
   @Schema(description = "户口邮编")
   private String huKouYB;

    /**
   *国籍代码[0463]
    */
   @Schema(description = "国籍代码[0463]")
   private String guoJiDM;

    /**
   *国籍名称
    */
   @Schema(description = "国籍名称")
   private String guoJiMC;

    /**
   *民族代码[0454]
    */
   @Schema(description = "民族代码[0454]")
   private String minZuDM;

    /**
   *民族名称
    */
   @Schema(description = "民族名称")
   private String minZuMC;

    /**
   *婚姻代码[0461]
    */
   @Schema(description = "婚姻代码[0461]")
   private String hunYinDM;

    /**
   *婚姻名称
    */
   @Schema(description = "婚姻名称")
   private String hunYinMC;

    /**
   *相似度
    */
   @Schema(description = "相似度")
   private BigDecimal xiangSiDu;

    /**
   *联系人电话
    */
   @Schema(description = "联系人电话")
   private String lianXiRDH;

    /**
   *职业代码[0462]
    */
   @Schema(description = "职业代码[0462]")
   private String zhiYeDM;

    /**
   *职业名称
    */
   @Schema(description = "职业名称")
   private String zhiYeMC;

    /**
   *出生地信息
    */
   @Schema(description = "出生地信息")
   private String chuShengDXX;

    /**
   *出生地省份代码(国家统一编码)[0459]
    */
   @Schema(description = "出生地省份代码(国家统一编码)[0459]")
   private String chuShengDSFDM;

    /**
   *出生地省份名称
    */
   @Schema(description = "出生地省份名称")
   private String chuShengDSFMC;

    /**
   *出生地市地区代码(国家统一编码)[0459]
    */
   @Schema(description = "出生地市地区代码(国家统一编码)[0459]")
   private String chuShengDSDQDM;

    /**
   *出生地市地区名称
    */
   @Schema(description = "出生地市地区名称")
   private String chuShengDSDQMC;

    /**
   *出生地县区代码(国家统一编码)[0459]
    */
   @Schema(description = "出生地县区代码(国家统一编码)[0459]")
   private String chuShengDXQDM;

    /**
   *出生地县区名称
    */
   @Schema(description = "出生地县区名称")
   private String chuShengDXQMC;

    /**
   *籍贯地址信息
    */
   @Schema(description = "籍贯地址信息")
   private String jiGuanDZXX;

    /**
   *籍贯省份代码(国家统一编码)[0459]
    */
   @Schema(description = "籍贯省份代码(国家统一编码)[0459]")
   private String jiGuanSFDM;

    /**
   *籍贯省份名称
    */
   @Schema(description = "籍贯省份名称")
   private String jiGuanSFMC;

    /**
   *籍贯市地区代码(国家统一编码)[0459]
    */
   @Schema(description = "籍贯市地区代码(国家统一编码)[0459]")
   private String jiGuanSDQDM;

    /**
   *籍贯市地区名称
    */
   @Schema(description = "籍贯市地区名称")
   private String jiGuanSDQMC;

    /**
   *籍贯县区代码(国家统一编码)[0459]
    */
   @Schema(description = "籍贯县区代码(国家统一编码)[0459]")
   private String jiGuanXQDM;

    /**
   *籍贯县区名称
    */
   @Schema(description = "籍贯县区名称")
   private String jiGuanXQMC;

    /**
   *现住址信息
    */
   @Schema(description = "现住址信息")
   private String xianZhuZXX;

    /**
   *现住址省份代码(国家统一编码)[0459]
    */
   @Schema(description = "现住址省份代码(国家统一编码)[0459]")
   private String xianZhuZSFDM;

    /**
   *现住址省份名称
    */
   @Schema(description = "现住址省份名称")
   private String xianZhuZSFMC;

    /**
   *现住址市地区代码(国家统一编码)[0459]
    */
   @Schema(description = "现住址市地区代码(国家统一编码)[0459]")
   private String xianZhuZSDQDM;

    /**
   *现住址市地区名称
    */
   @Schema(description = "现住址市地区名称")
   private String xianZhuZSDQMC;

    /**
   *现住址县区代码(国家统一编码)[0459]
    */
   @Schema(description = "现住址县区代码(国家统一编码)[0459]")
   private String xianZhuZXQDM;

    /**
   *现住址县区名称
    */
   @Schema(description = "现住址县区名称")
   private String xianZhuZXQMC;

    /**
   *现住址乡镇代码(标准编码)[0478]
    */
   @Schema(description = "现住址乡镇代码(标准编码)[0478]")
   private String xianZhuZXZDM;

    /**
   *现住址乡镇名称
    */
   @Schema(description = "现住址乡镇名称")
   private String xianZhuZXZMC;

    /**
   *现住址村级代码(标准编码)[0479]
    */
   @Schema(description = "现住址村级代码(标准编码)[0479]")
   private String xianZhuZCJDM;

    /**
   *现住址村级名称
    */
   @Schema(description = "现住址村级名称")
   private String xianZhuZCJMC;

    /**
   *现住址其他信息(如：门牌代码等）
    */
   @Schema(description = "现住址其他信息(如：门牌代码等）")
   private String xianZhuZQTXX;

    /**
   *现住址邮编
    */
   @Schema(description = "现住址邮编")
   private String xianZhuZYB;

    /**
   *病人介质信息
    */
   @Schema(description = "病人介质信息")
   private BR_DA_JieZhiXXDto jieZhiXX;

    /**
   *病人扩展信息
    */
   @Schema(description = "病人扩展信息")
   private BR_DA_KuoZhanXXDto kuoZhanXX;

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String id;

    /**
   *居民主索引
    */
   @Schema(description = "居民主索引")
   private String mPI;

    /**
   *年龄
    */
   @Schema(description = "年龄")
   private String nianLing;

    /**
   *年龄单位
    */
   @Schema(description = "年龄单位")
   private String nianLingDW;

    /**
   *年龄1
    */
   @Schema(description = "年龄1")
   private String nianLing1;

    /**
   *年龄单位1
    */
   @Schema(description = "年龄单位1")
   private String nianLingDW1;

    /**
   *姓名
    */
   @Schema(description = "姓名")
   private String xingMing;

    /**
   *性别代码[0460]
    */
   @Schema(description = "性别代码[0460]")
   private String xingBieDM;

    /**
   *性别名称
    */
   @Schema(description = "性别名称")
   private String xingBieMC;

    /**
   *出生日期
    */
   @Schema(description = "出生日期")
   private Date chuShengRQ;

    /**
   *证件类型代码[0001]
    */
   @Schema(description = "证件类型代码[0001]")
   private String zhengJianLXDM;

    /**
   *证件类型名称
    */
   @Schema(description = "证件类型名称")
   private String zhengJianLXMC;

    /**
   *证件号码
    */
   @Schema(description = "证件号码")
   private String zhengJianHM;

    /**
   *身份证号
    */
   @Schema(description = "身份证号")
   private String shenFenZH;

}
