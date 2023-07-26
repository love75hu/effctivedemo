package cn.mediinfo.grus.shujuzx.dto.yinsigzszs;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*展示配置
*/
@Data
public class SC_ZD_ZhanShiPZDto {

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
   *功能ID
    */
   @Schema(description = "功能ID")
   private String gongNengID;

    /**
   *功能名称
    */
   @Schema(description = "功能名称")
   private String gongNengMC;

    /**
   *自定义名称
    */
   @Schema(description = "自定义名称")
   private String ziDingYMC;

    /**
   *配置类型代码
    */
   @Schema(description = "配置类型代码")
   private String peiZhiLXDM;

    /**
   *配置类型名称
    */
   @Schema(description = "配置类型名称")
   private String peiZhiLXMC;

    /**
   *路径类型代码
    */
   @Schema(description = "路径类型代码")
   private String luJingLXDM;

    /**
   *路径类型名称
    */
   @Schema(description = "路径类型名称")
   private String luJingLXMC;

    /**
   *路径
    */
   @Schema(description = "路径")
   private String luJing;

    /**
   *图标
    */
   @Schema(description = "图标")
   private String tuBiao;

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
