package cn.mediinfo.grus.shujuzx.dto.yinsigzszs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class SC_ZD_YinSiGZSZInDto {

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
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
