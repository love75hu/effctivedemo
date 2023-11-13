package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DA_GA_CanJiQKDto {
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
     * 残疾情况代码[0142]
     */
    @Schema(description = "残疾情况代码[0142]")
    private String canJiQKDM;

    /**
     * 残疾情况名称
     */
    @Schema(description = "残疾情况名称")
    private String canJiQKMC;

    /**
     * 残疾情况其他(当残疾情况选择“其他残疾”时，此项必填)
     */
    @Schema(description = "残疾情况其他(当残疾情况选择“其他残疾”时，此项必填)")
    private String canJiQKQT;

}

