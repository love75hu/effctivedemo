package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShiTuXXDto {
    @Schema(description = "父类ID")
    private String fuLeiID;
    @Schema(description = "父类名称")
    private String fuLeiMC;
    @Schema(description = "视图ID")
    private String shiTuID;
    @Schema(description = "视图名称")
    private String shiTuMC;
    @Schema(description = "末级标志")
    private Integer moJiBZ;
    @Schema(description = "数据来源类型代码【SC0014】")
    private String shuJuLYLXDM;
    @Schema(description = "数据来源类型名称")
    private String shuJuLYLXMC;
    @Schema(description = " 数据来源ID")
    private String shuJuLYID;
    @Schema(description = "数据来源名称")
    private String shuJuLYMC;
    @Schema(description = "门诊使用标志")
    private Integer menZhenSYBZ;
    @Schema(description = "住院使用标志")
    private Integer zhuYuanSYBZ;
    @Schema(description = "急诊使用标志")
    private Integer jiZhenSYBZ;
    @Schema(description = "公卫使用标志")
    private Integer gongWeiSYBZ;
    @Schema(description = "顺序号")
    private Integer shunXuHao;


}
