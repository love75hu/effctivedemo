package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文档生成_文档数量
 */
@Data
public class WenDangSLDto {
    /**
     * 文档类型代码
     */
    @Schema(description = "文档类型代码")
    private String wenDangLXDM;
    /**
     * 文档类型名称
     */
    @Schema(description = "文档类型名称")
    private String wenDangLXMC;

    /**
     * 业务数量
     */
    @Schema(description = "业务数量")
    private Integer yeWuSL;
    /**
     * 主索引
     */
    @Schema(description = "主索引")
    private String mPI;
    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String xingMing;
}
