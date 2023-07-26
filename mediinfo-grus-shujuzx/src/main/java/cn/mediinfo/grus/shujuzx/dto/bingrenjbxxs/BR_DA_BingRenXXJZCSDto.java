package cn.mediinfo.grus.shujuzx.dto.bingrenjbxxs;
import java.util.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class BR_DA_BingRenXXJZCSDto {

    /**
   *主键
    */
   @Schema(description = "主键")
   private String id;

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

}
