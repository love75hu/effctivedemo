package cn.mediinfo.grus.shujuzx.dto.bihuanjdszs;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_BiHuanJDListDto {

    /**
   *节点ID
    */
   @Schema(description = "节点ID")
   private String jieDianID;

    /**
   *节点名称
    */
   @Schema(description = "节点名称")
   private String jieDianMC;

    /**
   *备注
    */
   @Schema(description = "备注")
   private String beiZhu;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *门诊使用标志
    */
   @Schema(description = "门诊使用标志")
   private Integer menZhenSYBZ;

    /**
   *住院使用标志
    */
   @Schema(description = "住院使用标志")
   private Integer zhuYuanSYBZ;

    /**
   *急诊使用标志
    */
   @Schema(description = "急诊使用标志")
   private Integer jiZhenSYBZ;

    /**
   *体检使用标志
    */
   @Schema(description = "体检使用标志")
   private Integer tiJianSYBZ;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
