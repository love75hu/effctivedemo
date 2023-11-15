package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Schema(description = "方案查询就诊分组")
public class FangAnCXJZFZDto {
    @Schema(description = "组织机构ID")
    private String zuZhiJGID;

    @Schema(description = "就诊ID")
    private String jiuZhenID;

    @Schema(description = "就诊日期")
    private Date jiuZhenRQ;

    @Schema(description = "顺序号")
    private Integer shunXuHao;

    @Schema(description = "方案查询视图分组")
    private List<FangAnCXSTFZDto> fangAnCXSTFZList;
}
