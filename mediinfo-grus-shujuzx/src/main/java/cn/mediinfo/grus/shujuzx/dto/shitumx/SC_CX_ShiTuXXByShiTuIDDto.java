package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_CX_ShiTuXXByShiTuIDDto {
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
}
