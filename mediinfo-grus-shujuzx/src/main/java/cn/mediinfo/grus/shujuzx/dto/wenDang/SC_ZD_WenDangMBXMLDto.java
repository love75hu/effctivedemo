package cn.mediinfo.grus.shujuzx.dto.wenDang;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SC_ZD_WenDangMBXMLDto {
    @Schema(description = "主键id")
    private String id;

    @Schema(description = "模板内容转实体格式返回")
    private List<XML_JieDian> xmlText;
}