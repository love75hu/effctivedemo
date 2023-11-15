package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案查询病人分组")
public class FangAnCXBRFZDto {
    @Schema(description = "病人ID")
    private String bingRenID;

    @Schema(description = "就诊数")
    private Integer jiuZhenCount;

    @Schema(description = "方案查询就诊分组")
    private List<FangAnCXJZFZDto> fangAnCXJZFZList;

    @Schema(description = "基本信息")
    private List<QueryResultDTO> bingRenJBXX;
}
