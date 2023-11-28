package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GY_ZD_ShuJuSTXXRso {
    /**
     * 分类ID
     */
    @Schema(description = "分类ID")
    private String fuLeiID;
    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String fuLeiMC;

    /**
     * 数据视图ID
     */
    @Schema(description = "数据视图ID")
    private String shuJuSTID;
    /**
     * 数据视图名称
     */
    @Schema(description = "数据视图名称")
    private String shuJuSTMC;
    /**
     * 关联方式代码[0.默认不关联 1.SQL关联2.字段关联]
     */
    @Schema(description = "关联方式代码")
    private String guanLianFSDM;
    /**
     * 关联方式名称
     */
    @Schema(description = "关联方式名称")
    private String gunaLianFSMC;
    /**
     * 关联关系(字段关联时存JSON)
     */
    @Schema(description = "关联关系")
    private String guanLianGX;
    /**
     * 关联SQL
     */
    @Schema(description = "关联SQL")
    private String guanLianSQL;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")
    private Integer shunXuHao ;
    /**
     * 数据视图分类
     */
    @Schema(description = "数据集list")
    private List<ShuJuJRsoList> shuJuJXXList;
    /**
     * 过滤条件
     */
    @Schema(description = "过滤条件")
    private String guoLvTJ;
}
