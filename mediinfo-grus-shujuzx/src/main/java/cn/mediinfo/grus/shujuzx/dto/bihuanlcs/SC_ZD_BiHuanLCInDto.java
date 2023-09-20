package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class SC_ZD_BiHuanLCInDto {

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
   *闭环类型代码【DC0004】
    */
   @Schema(description = "闭环类型代码【DC0004】")
   private String biHuanLXDM;

    /**
   *闭环类型名称
    */
   @Schema(description = "闭环类型名称")
   private String biHuanLXMC;

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
