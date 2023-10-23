package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BiHuanSTXXTree {

    @Schema(description = "id")
    private String id;
    @Schema(description = "闭环类型代码")
    private String biHuanLXDM;
    @Schema(description = "闭环类型名称")
    private String biHuanLXMC;
    @Schema(description = "视图信息id")
    private String shiTuID;
    @Schema(description = "视图名称")
    private String shiTuMC;
    @Schema(description = "视图类型代码")
    private String shiTuLXDM;
    private List<BiHuanSTXXTree> children;

}
