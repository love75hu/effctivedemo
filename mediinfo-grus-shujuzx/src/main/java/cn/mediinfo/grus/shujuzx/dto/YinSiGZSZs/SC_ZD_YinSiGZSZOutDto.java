package cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_YinSiGZSZOutDto {

    /**
   *数据元名称
    */
   @Schema(description = "数据元名称")
   private String shuJuYMC;

    /**
   *数据元列名
    */
   @Schema(description = "数据元列名")
   private String shuJuYLM;

    /**
   *脱敏方式代码【DC0001】
    */
   @Schema(description = "脱敏方式代码【DC0001】")
   private String tuoMinFSDM;

    /**
   *脱敏方式名称
    */
   @Schema(description = "脱敏方式名称")
   private String tuoMinFSMC;

    /**
   *脱敏规则
    */
   @Schema(description = "脱敏规则")
   private String tuoMinGZ;

    /**
   *脱敏说明
    */
   @Schema(description = "脱敏说明")
   private String tuoMinSM;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *是否选中
    */
   @Schema(description = "是否选中")
   private Boolean shiFouXZ;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
