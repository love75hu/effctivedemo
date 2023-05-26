package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class GuiZePZDto {

    /**
   *规则ID
    */
   @Schema(description = "规则ID")
   private String guiZeID;

    /**
   *规则名称
    */
   @Schema(description = "规则名称")
   private String guiZeMC;

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
   *权重
    */
   @Schema(description = "权重")
   private BigDecimal quanZhong;

}
