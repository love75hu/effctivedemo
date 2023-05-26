package cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*数据中心 - 收藏dto
*/
@Data
public class SC_SC_ShouCangJXXOutDto {

    /**
   *主键
    */
   @Schema(description = "主键")
   private String id;

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
   *患者数
    */
   @Schema(description = "患者数")
   private Integer huanZheShu;

    /**
   *备注
    */
   @Schema(description = "备注")
   private String beiZhu;

}
