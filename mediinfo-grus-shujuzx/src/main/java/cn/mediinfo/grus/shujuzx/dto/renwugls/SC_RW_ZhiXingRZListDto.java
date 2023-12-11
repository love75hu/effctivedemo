package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class SC_RW_ZhiXingRZListDto {
    @Schema(description = "id")
    private String id;
    @Schema(description = "任务id")
    private String renWuID;
    @Schema(description = "任务名称")
    private String renWuMC;
    @Schema(description = "执行时间")
    private Date zhiXingSJ;
    @Schema(description = "执行开始时间")
    private Date zhiXingKSSJ;
    @Schema(description = "执行结束时间")
    private Date zhiXingJSSJ;
    @Schema(description = "执行耗时")
    private Integer zhiXingHS;

    @Schema(description = "执行方式代码[SC0020]")
    private String zhiXingFSDM;
    @Schema(description = "执行方式名称")
    private String zhiXingFSMC;

    @Schema(description = "执行状态名称")
    private String zhiXingZTMC;
    @Schema(description = "入参")
    private String ruCan;
    @Schema(description = "执行人姓名")
    private String zhiXingRXM;
    @Schema(description = "任务地址")
    private String renWuDZ;

}
