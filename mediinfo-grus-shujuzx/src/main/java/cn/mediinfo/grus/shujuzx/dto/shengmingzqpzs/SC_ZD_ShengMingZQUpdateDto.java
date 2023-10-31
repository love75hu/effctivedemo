package cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_ZD_ShengMingZQUpdateDto {
    @Schema(description = "主键")
    private String id;
    @Schema(description = "生命周期名称")
    private String shengMingZQMC;
    @Schema(description = "起始年龄")
    private Integer qiShiNL;
    @Schema(description = "结束年龄")
    private Integer jieShuNL;
    @Schema(description = "男性图片")
    private String nanXingTP;
    @Schema(description = "女性图片")
    private String nvXingTP;
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
