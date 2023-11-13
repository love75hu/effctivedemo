package cn.mediinfo.grus.shujuzx.dto.wendangjls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SC_GW_JiLuXXSCDto {
    /**
     * mpilist
     */
    @Schema(description = "mpiList")
    private List<String> bingRenIDList;
    /**
     * 文档信息列表
     */
    @Schema(description = "文档信息列表")
    private List<SC_GW_JiLuXXCreateDto> jiLuXXList;
    /**
     * 文档类型
     */
    @Schema(description = "文档类型")
    private  String wenDangLX;

    /**
     * 数据来源代码
     */
    @Schema(description = "数据来源代码")
    private String shuJuLYDM;
}
