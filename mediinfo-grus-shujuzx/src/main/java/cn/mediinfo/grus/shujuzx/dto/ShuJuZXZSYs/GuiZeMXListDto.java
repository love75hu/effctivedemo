package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*规则明细
*/
@Data
public class GuiZeMXListDto {

    /**
   *合并权重ID
    */
   @Schema(description = "合并权重ID")
   private String heBingQZID;

    /**
   *代码
    */
   @Schema(description = "代码")
   private String daiMa;

    /**
   *名称
    */
   @Schema(description = "名称")
   private String mingCheng;

    /**
   *权重(1-100)
    */
   @Schema(description = "权重(1-100)")
   private BigDecimal quanZhong;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
