package cn.mediinfo.grus.shujuzx.dto.wenDang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class XML_JieDian {
    @Schema(description = "节点名称")
    private String jieDianName;

    @Schema(description = "节点文本")
    private String text;

    @Schema(description = "节点属性")
    private List<XML_JieDianSX> jieDianSXList;

    @Schema(description = "子级系节点集合")
    private List<XML_JieDian> jieDianList;
}