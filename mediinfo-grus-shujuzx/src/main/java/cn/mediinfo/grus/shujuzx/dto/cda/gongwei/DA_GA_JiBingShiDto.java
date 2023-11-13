package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class DA_GA_JiBingShiDto {
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
     * 确诊日期
     */
    @Schema(description = "确诊日期")
    private Date queZhenRQ;

    /**
     * 疾病史其他
     */
    @Schema(description = "疾病史其他")
    private String jiBingSQT;

}

