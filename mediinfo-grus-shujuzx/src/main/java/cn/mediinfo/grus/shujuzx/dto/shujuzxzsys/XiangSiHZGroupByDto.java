package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;
import lombok.Data;

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
