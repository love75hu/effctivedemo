package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class JiSuanZDDto {

    /**
   *字段名称
    */
   @Schema(description = "字段名称")
   private String ziDuanMC;

    /**
   *对比值1
    */
   @Schema(description = "对比值1")
   private Object value1;

    /**
   *对比值2
    */
   @Schema(description = "对比值2")
   private Object value2;

}
