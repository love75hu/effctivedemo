package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DA_GA_JiaZuShiDto {
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
     * 疾病种类代码[0014]
     */
    @Schema(description = "疾病种类代码[0014]")
    private String jiBingZLDM;

    /**
     * 疾病种类名称
     */
    @Schema(description = "疾病种类名称")
    private String jiBingZLMC;

    /**
     * 与本人关系代码[DA0006]
     */
    @Schema(description = "与本人关系代码[DA0006]")
    private String yuBenRGXDM;

    /**
     * 与本人关系名称
     */
    @Schema(description = "与本人关系名称")
    private String yuBenRGXMC;

    /**
     * 家族史其他(类别选其他时必填)
     */
    @Schema(description = "家族史其他(类别选其他时必填)")
    private String jiaZuSQT;

}

