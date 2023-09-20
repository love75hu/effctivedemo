package cn.mediinfo.grus.shujuzx.dto.bihuanjdszs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_ZD_BiHuanJDUpdateDto {

    /**
     * 闭环类型代码【DC0004】
     */
    @Schema(description = "闭环类型代码【DC0004】")
    private String biHuanLXDM;

    /**
     * 闭环类型名称
     */
    @Schema(description = "闭环类型名称")
    private String biHuanLXMC;

    /**
     * 节点ID
     */
    @Schema(description = "节点ID")
    private String jieDianID;

    /**
     * 节点名称
     */
    @Schema(description = "节点名称")
    private String jieDianMC;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String beiZhu;

    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;

    /**
     * 门诊使用标志
     */
    @Schema(description = "门诊使用标志")
    private Integer menZhenSYBZ;

    /**
     * 住院使用标志
     */
    @Schema(description = "住院使用标志")
    private Integer zhuYuanSYBZ;

    /**
     * 急诊使用标志
     */
    @Schema(description = "急诊使用标志")
    private Integer jiZhenSYBZ;

    /**
     * 体检使用标志
     */
    @Schema(description = "体检使用标志")
    private Integer tiJianSYBZ;

    /**
     * Id
     */
    @Schema(description = "Id")
    private String id;

}
