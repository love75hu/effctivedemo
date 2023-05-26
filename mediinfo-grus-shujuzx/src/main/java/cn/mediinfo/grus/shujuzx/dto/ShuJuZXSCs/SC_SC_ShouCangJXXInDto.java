package cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_SC_ShouCangJXXInDto {

    /**
   *收藏夹名称
    */
   @Schema(description = "收藏夹名称")
   private String shouCangJMC;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *备注
    */
   @Schema(description = "备注")
   private String beiZhu;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
