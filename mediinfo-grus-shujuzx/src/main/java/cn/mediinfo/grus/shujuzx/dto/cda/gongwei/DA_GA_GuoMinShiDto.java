package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DA_GA_GuoMinShiDto {
    /**
     * 组织机构ID
     */
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    /**
     * 组织机构名称
     */
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;

    /**
     * 健康档案ID
     */
    @Schema(description = "健康档案ID")
    private String jianKangDAID;

    /**
     * 过敏源代码[0139]
     */
    @Schema(description = "过敏源代码[0139]")
    private String guoMinYDM;

    /**
     * 过敏源名称
     */
    @Schema(description = "过敏源名称")
    private String guoMinYMC;

    /**
     * 过敏源其他(当过敏源为“其他”时，此项必填)
     */
    @Schema(description = "过敏源其他(当过敏源为“其他”时，此项必填)")
    private String guoMinYQT;

}
