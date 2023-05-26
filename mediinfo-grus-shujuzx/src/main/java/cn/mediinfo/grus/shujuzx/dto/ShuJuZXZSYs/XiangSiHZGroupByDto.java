package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class XiangSiHZGroupByDto {

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

    /**
   *数量
    */
   @Schema(description = "数量")
   private Integer count;

}
