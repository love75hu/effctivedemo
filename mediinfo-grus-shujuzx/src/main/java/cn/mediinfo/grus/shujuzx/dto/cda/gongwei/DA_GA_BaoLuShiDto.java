package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DA_GA_BaoLuShiDto {
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
     * 暴露史类别代码[0035]
     */
    @Schema(description = "暴露史类别代码[0035]")
    private String baoLuSLBDM;

    /**
     * 暴露史类别名称
     */
    @Schema(description = "暴露史类别名称")
    private String baoLuSLBMC;

    /**
     * 暴露史其他(类别选其他时必填)
     */
    @Schema(description = "暴露史其他(类别选其他时必填)")
    private String baoLuSQT;

}
