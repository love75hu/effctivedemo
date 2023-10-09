package cn.mediinfo.grus.shujuzx.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *  方案输出
 */
@Getter
@Setter
@ToString
public class RelatedFangAnBO {

    @Schema(description = "方案id")
    private String fangAnId;

    @Schema(description = "方案输出id")
    private String fangAnSCId;
}
