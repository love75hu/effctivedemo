package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class BiHuanXXDto {


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
    private String id;
    /**
     * 闭环类型代码
     */
    @Schema(description = "闭环类型代码")
    private String biHuanLXDM;
    /**
     * 闭环类型名称
     */
    @Schema(description = "闭环类型名称")
    private String biHuanLXMC;

    /**
     * 闭环ID
     */
    @Schema(description = "闭环ID")
    private String biHuanID;
    /**
     * 闭环名称
     */
    @Schema(description = "闭环名称")
    private String biHuanMC;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao;
    /**
     * 启用标志
     */
    @Schema(description = "启用标志")
    private Integer qiYongBZ;

    private List<RuCanXXDto> ruCanXXDtoList;
}
