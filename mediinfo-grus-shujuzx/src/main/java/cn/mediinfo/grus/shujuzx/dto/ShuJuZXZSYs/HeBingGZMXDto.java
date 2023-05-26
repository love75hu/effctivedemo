package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*合并规则明细
*/
@Data
public class HeBingGZMXDto {

    /**
   *规则名称
    */
   @Schema(description = "规则名称")
   private String guiZeMC;

    /**
   *阀值
    */
   @Schema(description = "阀值")
   private BigDecimal faZhi;

    /**
   *权重配置明细
    */
   @Schema(description = "权重配置明细")
   private List<GuiZeMXListDto> guiZeMXList;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
