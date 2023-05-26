package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*病人基本信息
*/
@Data
public class BR_DA_JiBenXXDto {

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
