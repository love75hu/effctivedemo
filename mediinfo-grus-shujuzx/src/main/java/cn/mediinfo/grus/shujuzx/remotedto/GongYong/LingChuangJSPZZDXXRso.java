package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class LingChuangJSPZZDXXRso {
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;
    @Schema(description = "数据来源ID")
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
    @Schema(description = "视图字段明细")
    private List<ShuJuJMXZDDto> shiTuMXZDDtos;
}
