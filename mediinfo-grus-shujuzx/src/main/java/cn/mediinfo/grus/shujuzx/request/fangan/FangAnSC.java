package cn.mediinfo.grus.shujuzx.request.fangan;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "方案输出字段信息")
public class FangAnSC {

    @Schema(description = "指标id")
    private String zhiBiaoID;

    @Schema(description = "指标名称")
    private String zhiBiaoMC;

    @Schema(description = "指标类型代码;1-数据元，2-检验，3-检查，4-药品")
    private String zhiBiaoLXDM;

    @Schema(description = "指标id（字段编码，检验明细项目，检查部位，药品规格id）")
    private String zhiBiaoFLID;

    @Schema(description = "必须标志")
    private Integer biXuanBZ;
}
