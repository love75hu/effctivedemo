package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案查询视图分组")
public class FangAnCXSTFZDto {
    @Schema(description = "视图代码")
    private String shiTuDM;

    @Schema(description = "视图主键数量")
    private Integer shiTuZJCount;

    @Schema(description = "视图顺序号")
    private Integer shiTuSXH;

    @Schema(description = "方案查询视图主键分组")
    private List<FangAnCXSTZJFZDto> fangAnCXSTZJFZList;
}
