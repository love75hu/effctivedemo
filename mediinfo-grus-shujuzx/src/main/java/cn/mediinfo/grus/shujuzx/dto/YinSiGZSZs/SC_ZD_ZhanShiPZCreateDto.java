package cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*展示配置新增
*/
@Data
public class SC_ZD_ZhanShiPZCreateDto {

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
   *查询模式代码【DC0002】
    */
   @Schema(description = "查询模式代码【DC0002】")
   private String chaXunMSDM;

    /**
   *查询模式名称
    */
   @Schema(description = "查询模式名称")
   private String chaXunMSMC;

    /**
   *新增
    */
   @Schema(description = "新增")
   private List<SC_ZD_ZhanShiPZDto> addList = new ArrayList<>();

    /**
   *修改
    */
   @Schema(description = "修改")
   private List<SC_ZD_ZhanShiPZDto> updateList = new ArrayList<>();

    /**
   *作废IDs
    */
   @Schema(description = "作废IDs")
   private List<String> zuoFeiIds = new ArrayList<>();

}
