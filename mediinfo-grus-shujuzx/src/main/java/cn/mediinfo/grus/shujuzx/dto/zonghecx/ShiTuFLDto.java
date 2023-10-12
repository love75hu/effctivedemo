package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 根据主键获取视图分类ID
 *
 */
@Data
public class ShiTuFLDto {
    private String id;
    @Schema(description = "分类名称，必填")
    private String shiTuFLMC  ;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
}
