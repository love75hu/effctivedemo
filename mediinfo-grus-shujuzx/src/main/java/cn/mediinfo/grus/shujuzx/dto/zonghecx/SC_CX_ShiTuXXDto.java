package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 临床检索视图信息Dto
 */
@Data
public class SC_CX_ShiTuXXDto {
    private String id;

    /**
     * 视图ID
     */
    @Schema(description = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Schema(description = "视图名称")
    private String shiTuMC;
    /**
     * 父类ID
     */
    @Schema(description = "父类ID")
    private String fuLeiID;
    /**
     * 父类名称
     */
    @Schema(description = "父类名称")
    private String fuLeiMC;
    /**
     * 末级标志
     */
    @Schema(description = "末级标志")
    private Integer moJiBZ;
    /**
     * 数据来源类型代码
     */
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    /**
     * 数据来源类型名称[1.数据集2.数据视图]
     */
    @Schema(description = "数据来源类型名称[1.数据集2.数据视图]")
    private String shuJuLYLXMC;
    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    @Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;
    /**
     * 数据来源名称
     */
    @Schema(description = "数据来源名称")
    private String shuJuLYMC;
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
     * 公卫使用标志
     */
    @Schema(description = "公卫使用标志")
    private Integer gongWeiZYBZ;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}