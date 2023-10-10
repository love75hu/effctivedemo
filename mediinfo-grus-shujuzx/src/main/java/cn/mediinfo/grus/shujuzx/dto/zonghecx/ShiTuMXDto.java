package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ShiTuMXDto {

    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "条件标志")
    private String tianJianBZ;
    @Schema(description = "输出标志")
    private String shuChuBZ;
    @Schema(description = "输出必选标志")
    private String shuChuBXBZ;
    @Schema(description = "顺序号")
    private String shuXuHao;
    @Schema(description = "关联关系")
    private List<GuanLianTJZD> guanLianZDList;
}
