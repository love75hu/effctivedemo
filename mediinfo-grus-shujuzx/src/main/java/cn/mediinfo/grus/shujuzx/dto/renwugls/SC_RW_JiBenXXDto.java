package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class SC_RW_JiBenXXDto  {

    @Schema(description = "主键")
    private String id;
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    @Schema(description = "任务id")
    private String renWuID;
    @Schema(description = "任务名称")
    private String renWuMC;
    @Schema(description = "任务说明")
    private String renWuSM;
    @Schema(description = "任务参数")
    private String renWuCS;
    @Schema(description = "任务地址")
    private String renWuDZ;
    @Schema(description = "分类代码[SC0017]")
    private String fenLeiDM;
    @Schema(description = "分类名称")
    private String fenLeiMC;
    @Schema(description = "执行频率代码[SC0018]")
    private String zhiXingPLDM;
    @Schema(description = "执行频率名称")
    private String zhiXingPLMC;
    @Schema(description = "执行开始时间")
    private Date zhiXingKSSJ;
    @Schema(description = "执行结束时间")
    private Date zhiXingJSSJ;
    @Schema(description = "执行耗时")
    private Integer zhiXingHS;
    @Schema(description = "执行状态代码[SC0019](最近一次执行状态)")
    private Integer zhiXingZTDM;
    @Schema(description = "执行状态名称")
    private Integer zhiXingZTMC;
    @Schema(description = "最近执行日志id")
    private String zuiJinZXRZID;
    @Schema(description = "启用标志")
    private Integer qiYongBZ;
    @Schema(description = "备注")
    private String beiZhu;


}
