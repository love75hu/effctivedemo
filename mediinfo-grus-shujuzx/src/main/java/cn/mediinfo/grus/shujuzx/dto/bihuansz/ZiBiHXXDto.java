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
    @Schema(description = "视图id")
    private String shiTuID;

    @Schema(description = "视图名称")
    private String shiTuMC;
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
    /**
     * 闭环子表ID
     */
    @Column(columnDefinition = "闭环子表ID")
    private String ziBiHSTID;
    /**
     * 闭环子表名称
     */
    @Column(columnDefinition = "子闭环视图名称")
    private String ziBiHSTMC;
    /**
     * 闭环子表代码
     */
    @Column(columnDefinition = "闭环子表代码")
    private String ziBiHZDBM;
    /**
     * 闭环子表名称
     */
    @Column(columnDefinition = "子闭环字段名称")
    private String ziBiHZDMC;

}
