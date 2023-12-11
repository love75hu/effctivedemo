package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_RW_TongYongPZDto {
    @Schema(description = "id")
    private String id;
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;
    @Schema(description = "组织机构名称")
    private String zuZhiJGMC;
    @Schema(description = "任务地址")
    private String renWuDZ;
    @Schema(description = "分类代码[SC0017]")
    private String fenLeiDM;
    @Schema(description = "分类名称")
    private String fenLeiMC;
    @Schema(description = "服务器IP")
    private String fuWuQIP;
}
