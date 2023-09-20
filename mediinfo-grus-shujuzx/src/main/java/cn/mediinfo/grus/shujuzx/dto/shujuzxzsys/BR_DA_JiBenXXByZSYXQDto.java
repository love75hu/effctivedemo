package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
/**
*病人基本信息
*/
@Data
public class BR_DA_JiBenXXByZSYXQDto {

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
   *单位名称
    */
   @Schema(description = "单位名称")
   private String danWeiMC;

    /**
   *联系人姓名
    */
   @Schema(description = "联系人姓名")
   private String lianXiRXM;

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
   *现住址信息
    */
   @Schema(description = "现住址信息")
   private String xianZhuZXX;

    /**
   *现住址省份名称
    */
   @Schema(description = "现住址省份名称")
   private String xianZhuZSFMC;

    /**
   *现住址市地区名称
    */
   @Schema(description = "现住址市地区名称")
   private String xianZhuZSDQMC;

    /**
   *现住址乡镇名称
    */
   @Schema(description = "现住址乡镇名称")
   private String xianZhuZXZMC;

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
   *建档时间
    */
   @Schema(description = "建档时间")
   private Date jianDangSJ;

    /**
   *创建时间
    */
   @Schema(description = "创建时间")
   private Date chuangJianSJ;

    /**
   *创建系统Id
    */
   @Schema(description = "创建系统Id")
   private String chuangJianXTID;

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

    /**
   *联系电话
    */
   @Schema(description = "联系电话")
   private String lianXiDH;

}
