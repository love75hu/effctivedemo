package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "结果列表")
public class QueryResultDTO {

    @Schema(description = "字段名")
    private String key;

    @Schema(description = "字段显示名称/值")
    private Object text;

    @Schema(description = "0-展示字段，1-标签,2-链接")
    private Integer type;
}
