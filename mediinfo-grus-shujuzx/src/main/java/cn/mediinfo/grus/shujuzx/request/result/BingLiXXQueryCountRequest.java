package cn.mediinfo.grus.shujuzx.request.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Schema(description = "病历详情查询")
public class BingLiXXQueryCountRequest {

    @Schema(description = "方案查询历史id")
    @NotBlank(message = "方案查询历史id不能为空")
    private String fangAnCXLSId;
}
