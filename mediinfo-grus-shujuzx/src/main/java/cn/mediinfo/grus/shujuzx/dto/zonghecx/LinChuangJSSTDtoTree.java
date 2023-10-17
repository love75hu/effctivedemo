package cn.mediinfo.grus.shujuzx.dto.zonghecx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 获取临床检索视图树
 */
@Data
public class LinChuangJSSTDtoTree {
    private String id;

    @Schema(description = "视图ID")
    private String shiTuID ;
    @Schema(description = "视图名称")
    private String shiTuMC ;
    @Schema(description = "父类ID")
    private String fuLeiID  ;
    @Schema(description = "父类名称")
    private String fuLeiMC ;
    @Schema(description = "顺序号")
    private String shunXuHao  ;
    @Schema(description = "末级标志")
    private Integer moJiBZ ;
    private List<LinChuangJSSTDtoTree> children ;

}
