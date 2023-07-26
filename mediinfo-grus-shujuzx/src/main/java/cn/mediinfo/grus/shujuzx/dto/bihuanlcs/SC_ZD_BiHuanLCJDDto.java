package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_BiHuanLCJDDto {

    /**
   *流程ID
    */
   @Schema(description = "流程ID")
   private String liuChengID;

    /**
   *流程名称
    */
   @Schema(description = "流程名称")
   private String liuChengMC;

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
   *节点自定义名称
    */
   @Schema(description = "节点自定义名称")
   private String jieDianZDYMC;

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
