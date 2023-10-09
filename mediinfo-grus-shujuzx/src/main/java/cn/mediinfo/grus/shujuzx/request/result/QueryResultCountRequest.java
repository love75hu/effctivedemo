package cn.mediinfo.grus.shujuzx.request.result;

import cn.mediinfo.grus.shujuzx.request.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "查询结果请求参数")
public class QueryResultCountRequest {

    @Schema(description = "方案查询历史id")
    @NotBlank(message = "方案查询历史id不能为空")
    private String fangAnCXLSId;

    @Schema(description = "合并类型，1-患者，2-就诊")
    private Integer mergeType;
}
