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
public class BingLiXXQRequest {
    

    @Schema(description = "方案查询历史id")
    @NotBlank(message = "方案查询历史id不能为空")
    private String fangAnCXLSId;

    @Schema(description = "就诊id")
    @NotBlank(message="就诊id不能为空")
    private String jiuzhenId;

    @Schema(description = "就诊类型")
    @NotBlank(message="就诊类型不能为空")
    private String jiuzhenLXDM;

    @Schema(description = "病人id")
    @NotBlank(message="病人id不能为空")
    private String bingRenId;
}
