package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*更新规则信息
*/
@Data
public class BR_ZD_HeBingGZUpdateDto {

    /**
   *规则名称
    */
   @Schema(description = "规则名称")
   private String guiZeMC;

    /**
   *规则ID
    */
   @Schema(description = "规则ID")
   private String guiZeID;

    /**
   *阀值
    */
   @Schema(description = "阀值")
   private BigDecimal faZhi;

    /**
   *新增规则明细
    */
   @Schema(description = "新增规则明细")
   private List<GuiZeMXListDto> addGuiZeMXList;

    /**
   *更新规则明细
    */
   @Schema(description = "更新规则明细")
   private List<GuiZeMXListDto> updateGuiZeMXList;

    /**
   *删除规则明细ID
    */
   @Schema(description = "删除规则明细ID")
   private List<String> deleteIds;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
