package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class DA_GA_ShouShuShiDto {
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
     * 手术名称
     */
    @Schema(description = "手术名称")
    private String shouShuMC;

    /**
     * 手术日期
     */
    @Schema(description = "手术日期")
    private Date shouShuRQ;

}

