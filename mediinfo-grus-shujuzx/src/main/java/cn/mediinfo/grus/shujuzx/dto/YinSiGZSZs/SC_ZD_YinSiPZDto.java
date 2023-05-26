package cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*隐私配置
*/
@Data
public class SC_ZD_YinSiPZDto {

    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;

    /**
   *查询模式代码
    */
   @Schema(description = "查询模式代码")
   private String chaXunMSDM;

    /**
   *查询模式名称
    */
   @Schema(description = "查询模式名称")
   private String chaXunMSMC;

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
   *启用标志
    */
   @Schema(description = "启用标志")
   private Integer qiYongBZ;

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
