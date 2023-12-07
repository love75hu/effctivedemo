package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import java.util.List;

@Data
public class LingChuangJSPZDto {
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    @Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;
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
    @Schema(description = "字段信息")
    private List<ShuJuJMXZDDto> shuJuJMXZDDtos;
}
