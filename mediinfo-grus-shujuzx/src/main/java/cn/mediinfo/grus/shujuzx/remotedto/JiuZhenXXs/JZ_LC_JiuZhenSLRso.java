package cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class JZ_LC_JiuZhenSLRso {

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *门诊次数
    */
   @Schema(description = "门诊次数")
   private Integer menZhenJZCS;

    /**
   *住院就诊次数
    */
   @Schema(description = "住院就诊次数")
   private Integer zuYuanJZCS;

    /**
   *急诊就诊次数
    */
   @Schema(description = "急诊就诊次数")
   private Integer jiZhenJZCS;

    /**
   *公卫次数
    */
   @Schema(description = "公卫次数")
   private Integer gongWeiJZCS;

}
