package cn.mediinfo.grus.shujuzx.dto.wenDang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class XML_JieDianSX {
    @Schema(description = "节点属性名称")
    private String name;

    @Schema(description = "节点属性值")
    private String value;
}