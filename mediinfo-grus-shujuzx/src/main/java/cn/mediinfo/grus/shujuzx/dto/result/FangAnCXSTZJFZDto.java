package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案查询视图主键分组")
public class FangAnCXSTZJFZDto {
    @Schema(description = "视图主键值")
    private String shiTuZJZ;

    @Schema(description = "字段数量")
    private Integer ziDuanCount;

    @Schema(description = "视图主键顺序号")
    private Integer shiTuZJSXH;

    @Schema(description = "方案查询字段分组")
    private List<FangAnCXZDFZDto> fangAnCXZDFZList;
}
