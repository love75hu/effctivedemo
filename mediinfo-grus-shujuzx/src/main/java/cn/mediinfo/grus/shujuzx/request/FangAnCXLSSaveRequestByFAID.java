package cn.mediinfo.grus.shujuzx.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "查询记录保存")
public class FangAnCXLSSaveRequestByFAID {
    @Schema(description = "方案ID")
    @NotBlank(message = "方案ID不能为空")
    private String fangAnId;

    @Schema(description = "关键字")
    private String guanJianZi;
}
