package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "方案查询结果分组")
public class FangAnCXJGFZDto {
    @Schema(description = "就诊顺序号")
    private Integer jiuZhenSXH;
    @Schema(description = "视图别名")
    private String shiTuBM;
    @Schema(description = "视图顺序号")
    private Integer shiTuSXH;
    @Schema(description = "视图主键顺序号")
    private Integer shiTuZJSXH;
    @Schema(description = "字段代码")
    private String ziDuanDM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "字段顺序号")
    private Integer ziDuanSXH;
    @Schema(description = "字段长度")
    private Integer ziDuanCD;
}
