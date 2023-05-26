package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*合并规则列表
*/
@Data
public class BR_ZD_HeBingGZListDto {

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
   *阀值
    */
   @Schema(description = "阀值")
   private BigDecimal faZhi;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
