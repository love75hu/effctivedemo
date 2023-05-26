package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*新增规则信息
*/
@Data
public class BR_ZD_HeBingGZCreateDto {

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
   *规则明细
    */
   @Schema(description = "规则明细")
   private List<GuiZeMXListDto> guiZeMXList;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
