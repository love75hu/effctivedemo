package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;
import java.util.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_BiHuanLCOutDto {

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
   *流程描述
    */
   @Schema(description = "流程描述")
   private String liuChengMS;

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
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *启用标志
    */
   @Schema(description = "启用标志")
   private Integer qiYongBZ;

    /**
   *闭环节点集合
    */
   @Schema(description = "闭环节点集合")
   private List<SC_ZD_BiHuanLCJDDto> biHuanJDLCList = new ArrayList<>();

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
