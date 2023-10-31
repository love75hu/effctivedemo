package cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs;

import cn.mediinfo.grus.shujuzx.events.BaseEventDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_ZD_ShengMingZQListDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "生命周期名称")
    private String shengMingZQMC;
    @Schema(description = "起始年龄")
    private Integer qiShiNL;
    @Schema(description = "结束年龄")
    private Integer jieShuNL;
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
