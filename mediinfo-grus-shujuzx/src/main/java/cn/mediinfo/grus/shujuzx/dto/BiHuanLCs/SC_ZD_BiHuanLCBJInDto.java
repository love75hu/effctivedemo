package cn.mediinfo.grus.shujuzx.dto.BiHuanLCs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_BiHuanLCBJInDto {

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
   *闭环类型代码
    */
   @Schema(description = "闭环类型代码")
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
   *新增流程节点
    */
   @Schema(description = "新增流程节点")
   private List<SC_ZD_BiHuanLCJDDto> addbiHuanJDLCList;

    /**
   *更新流程节点内容
    */
   @Schema(description = "更新流程节点内容")
   private List<SC_ZD_BiHuanLCJDDto> updatebiHuanJDLCList;

    /**
   *删除流程节点主键
    */
   @Schema(description = "删除流程节点主键")
   private List<String> deleteIds;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
