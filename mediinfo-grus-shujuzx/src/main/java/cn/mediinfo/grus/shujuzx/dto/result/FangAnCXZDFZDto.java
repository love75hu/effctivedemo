package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案查询字段分组")
public class FangAnCXZDFZDto {
    @Schema(description = "字段代码")
    private String ziDuanDM;

    @Schema(description = "字段名称")
    private String ziDuanMC;

    @Schema(description = "字段值数量")
    private Integer ziDuanZhiCount;

    @Schema(description = "字段值")
    private List<Object> ziDuanZhiList;
}
