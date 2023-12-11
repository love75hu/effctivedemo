package cn.mediinfo.grus.shujuzx.dto.renwugls;

import cn.hutool.core.date.DateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class SC_RW_ZhiXingRZQueryDto {
    @Schema(description = "任务id")
    private String renWuID;
    /**
     * 执行开始时间
     */
    @Schema(description = "执行开始时间")
    private Date zhiXingKSSJ;
    /**
     * 执行结束时间
     */
    @Schema(description = "任务结束时间")
    private Date zhiXingJSSJ;

    private Integer pageSize;

    private Integer pageIndex;


}
