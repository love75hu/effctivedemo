package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data

public class SC_RW_JiBenXXListDto {

    @Schema(description = "主键")
    private String id;
    @Column(columnDefinition = "任务id")
    private String renWuID;
    @Column(columnDefinition = "任务名称")
    private String renWuMC;
    @Column(columnDefinition = "任务说明")
    private String renWuSM;
    @Column(columnDefinition = "任务地址")
    private String renWuDZ;
    @Column(columnDefinition = "分类名称")
    private String fenLeiMC;
    @Column(columnDefinition = "执行频率名称")
    private String zhiXingPLMC;
    @Column(columnDefinition = "最近执行日志id")
    private String zuiJinZXRZID;
    @Schema(description = "执行耗时")
    private Integer zhiXingHS;
    @Schema(description = "执行状态代码")
    private String zhiXingZTDM;
    @Schema(description = "执行状态名称")
    private String zhiXingZTMC;
    @Column(columnDefinition = "启用标志")
    private Integer qiYongBZ;
    @Column(columnDefinition = "备注")
    private String beiZhu;
    @Schema(description = "设计器地址")
    private String sheJiQDZ;
    @Schema(description = "api地址")
    private String apiDZ;
    @Schema(description = "最近执行时间")
    private Date zuiJinZXSJ;

}
