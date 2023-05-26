package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
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
