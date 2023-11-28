package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShuJuJRsoList {
    @Schema(description = "数据集ID")
    private String shuJuJID;
    @Schema(description = "数据集名称")
    private String shuJuJMC;
    @Schema(description = "表名")
    private String biaoMing;
}
