package cn.mediinfo.grus.shujuzx.common.fangan.condition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "方案条件查询值")
public class FanganConditionValue {

    @Schema(description = "查询值")
    private String val;

    @Schema(description = "查询值名称(展示)")
    private String name;
}
