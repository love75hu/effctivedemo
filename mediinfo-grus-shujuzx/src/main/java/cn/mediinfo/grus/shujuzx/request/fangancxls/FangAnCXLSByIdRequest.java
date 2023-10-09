package cn.mediinfo.grus.shujuzx.request.fangancxls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Schema(description = "方案历史查询")
public class FangAnCXLSByIdRequest {

    @Schema(description = "方案查询历史id")
    private String fangAnCXLSId;
}
