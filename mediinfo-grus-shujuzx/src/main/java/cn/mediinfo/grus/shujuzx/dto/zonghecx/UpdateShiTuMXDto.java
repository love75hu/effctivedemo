package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class UpdateShiTuMXDto {
    private String id;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "条件标志")
    private String tianJianBZ;
    @Schema(description = "关联字段列表")
    private List<GuanLianTJZD> guanLianZDList;
    @Schema(description = "输出标志")
    private String shuChuBZ;
    @Schema(description = "输出标志")
    private String shuChuBXBZ;
    @Schema(description = "顺序号")
    private String shuXuHao;

}
