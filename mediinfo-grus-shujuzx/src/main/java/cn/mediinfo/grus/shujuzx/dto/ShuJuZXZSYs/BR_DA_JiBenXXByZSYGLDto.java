package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*病人基本信息
*/
@Data
public class BR_DA_JiBenXXByZSYGLDto {

    /**
   *主索引病人Id
    */
   @Schema(description = "主索引病人Id")
   private String zhuSuoYBRID;

    /**
   *现住址信息
    */
   @Schema(description = "现住址信息")
   private String xianZhuZXX;

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
   *合并状态代码0-无合并；1-合并后主数据；2-被合并数据
    */
   @Schema(description = "合并状态代码0-无合并；1-合并后主数据；2-被合并数据")
   private String heBingZTDM;

    /**
   *合并状态名称
    */
   @Schema(description = "合并状态名称")
   private String heBingZTMC;

    /**
   *最大相似度(0-100)
    */
   @Schema(description = "最大相似度(0-100)")
   private Integer zuiDaXSD;

    /**
   *合并数
    */
   @Schema(description = "合并数")
   private Integer heBingShu;

    /**
   *相似数
    */
   @Schema(description = "相似数")
   private Integer xiangSiShu;

    /**
   *相似度
    */
   @Schema(description = "相似度")
   private BigDecimal xiangSiDu;

    /**
   *合并标志
    */
   @Schema(description = "合并标志")
   private Integer heBingBZ;

    /**
   *自动合并标志
    */
   @Schema(description = "自动合并标志")
   private Integer ziDongHBBZ;

    /**
   *合并人姓名
    */
   @Schema(description = "合并人姓名")
   private String heBingRXM;

    /**
   *合并时间
    */
   @Schema(description = "合并时间")
   private Date heBingSJ;

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

   private Integer jiaoChaSYCount;

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
