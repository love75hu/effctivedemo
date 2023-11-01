package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ZiBiHXXDto {
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
    /**
     * 子闭环ID
     */
    @Schema(description = "子闭环ID")
    private String ziBiHID;
    /**
     * 子闭环名称
     */
    @Schema(description = "子闭环名称")
    private String ziBiHMC;
    /**
     * 关联字段编码
     */
    @Schema(description = "关联字段编码")
    private String guanLianZDBM;
    /**
     * 关联字段名称
     */
    @Schema(description = "关联字段名称")
    private String guanLianZDMC;
}
