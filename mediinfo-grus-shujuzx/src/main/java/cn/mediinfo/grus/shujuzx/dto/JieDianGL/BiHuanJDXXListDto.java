package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class BiHuanJDXXListDto {
    private String id;
    /**
     * 视图ID
     */
    @Column(columnDefinition = "视图ID")
    private String shiTuID;
    /**
     * 视图名称
     */
    @Column(columnDefinition = "视图名称")
    private String shiTuMC;
    @Schema(description = "节点id")
    private String jieDianID;
    @Schema(description = "节点名称")
    private String jieDianMC;
    @Schema(description = "节点内容")
    List<String> jieDianNR;
    @Schema(description = "关联节点")
    List<String> guanLianJD;
    @Schema(description = "启用标志")
    private Integer qiYongBZ;
}
