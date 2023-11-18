package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文档生成_数据集
 */
@Data
public class WenDangSJJDto {
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
     * 数据集名称
     */
    @Schema(description = "数据集名称")
    private String shuJuJMC;

    /**
     * 数据集内容
     */
    @Schema(description = "数据集内容")
    private String shuJuJNR;
}
