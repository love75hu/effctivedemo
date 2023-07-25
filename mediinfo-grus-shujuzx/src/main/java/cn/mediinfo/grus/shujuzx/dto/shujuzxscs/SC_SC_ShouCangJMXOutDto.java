package cn.mediinfo.grus.shujuzx.dto.shujuzxscs;
import java.util.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*数据中心 - 收藏明细dto
*/
@Data
public class SC_SC_ShouCangJMXOutDto {

    /**
   *主键
    */
   @Schema(description = "主键")
   private String id;

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *姓名
    */
   @Schema(description = "姓名")
   private String xingMing;

    /**
   *索引
    */
   @Schema(description = "索引")
   private String mPI;

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
   *出生日期
    */
   @Schema(description = "出生日期")
   private Date chuShengRQ;

    /**
   *门诊次数
    */
   @Schema(description = "门诊次数")
   private Integer menZhenJZCS;

    /**
   *住院次数
    */
   @Schema(description = "住院次数")
   private Integer zhuYuanJZCS;

    /**
   *急诊次数
    */
   @Schema(description = "急诊次数")
   private Integer jiZhenJZCS;

    /**
   *公卫次数
    */
   @Schema(description = "公卫次数")
   private Integer gongWeiJZCS;

    /**
   *收藏日期
    */
   @Schema(description = "收藏日期")
   private Date shouCangRQ;

    /**
   *收藏时间
    */
   @Schema(description = "收藏时间")
   private Date shouCangSJ;

    /**
   *收藏人姓名
    */
   @Schema(description = "收藏人姓名")
   private String shouCangRXM;

    /**
   *性别
    */
   @Schema(description = "性别")
   private String xingBieDM;

    /**
   *婴儿标志
    */
   @Schema(description = "婴儿标志")
   private Boolean yingErBZ;

}
