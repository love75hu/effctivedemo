package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WenDangSCQueryDto {
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
     * 主索引
     */
    @Schema(description = "主索引")
    private String mpi;
}
