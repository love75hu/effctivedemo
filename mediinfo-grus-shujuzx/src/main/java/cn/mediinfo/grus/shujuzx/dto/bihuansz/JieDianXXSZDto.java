package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;
@Data
public class JieDianXXSZDto {

    /**
     * 组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;

    @Schema(description = "视图id")
    private String shiTuID;
    @Schema(description = "视图名称")
    private String shiTuMC;

    /**
     * 节点ID
     */
    @Schema(description = "节点ID")
    private String jieDianID;
    /**
     * 节点名称
     */
    @Schema(description = "节点名称")
    private String jieDianMC;

    /**
     * 显示名称
     */
    @Schema(description = "显示名称")
    private String xianShiMC;
    /**
     * 隐藏标志
     */
    @Schema(description = "隐藏标志")
    private Integer yinCangBZ;
    /**
     * 必须节点标志
     */
    @Schema(description = "必须节点标志")
    private Integer biXuBZ;
    /**
     * 并行节点标志
     */
    @Schema(description = "并行节点标志")
    private Integer bingXingBZ;


    /**
     * 子闭环信息
     */
    AddZiBiHXXDto ziBiHXXDto;

    /**
     * 节点失效
     */
    List<AddJieDianSXDto> jieDianSXList;

    /**
     * 子闭环显示列
     */
    List<AddZiBiHXSLDto> ziBiHXSLDtoList;
}
