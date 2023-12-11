package cn.mediinfo.grus.shujuzx.remotedto.GongYong;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
*数据源分类
*/
@Data
public class GY_ZD_ShuJuYuanTreeRso {
    @Schema(description = "主键ID")
    private String id;
    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;
    /**
     *分类名称
     */
    @Schema(description = "分类ID")
    private String fenLeiID;
    /**
   *分类名称
    */
   @Schema(description = "分类名称")
   private String fenLeiMC;
    /**
     *分类名称
     */
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    /**
   *上级分类ID
    */
   @Schema(description = "上级分类ID")
   private String shangJiFLID;

    /**
   *上级分类名称
    */
   @Schema(description = "上级分类名称")
   private String shangJiFLMC;
    /**
     * 系统名称
     */
    @Schema(description = "系统名称")
    private String xiTongMC;

    /**
     * 厂商名称
     */
    @Schema(description = "厂商名称")
    private String changShangMC;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *末级标志
    */
   @Schema(description = "末级标志")
   private Integer moJiBZ;

    /**
   *子节点
    */
   @Schema(description = "子节点")
   private List<GY_ZD_ShuJuYuanTreeRso> children;



}
