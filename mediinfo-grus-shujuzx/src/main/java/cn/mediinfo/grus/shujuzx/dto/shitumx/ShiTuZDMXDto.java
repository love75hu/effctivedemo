package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ShiTuZDMXDto {

    @Schema(description = "数据集ID")
    private String shuJuJID;
    @Schema(description = "数据集名称")
    private String shuJuJMC;
    @Schema(description = "表名")
    private String biaoMing;
    @Schema(description = "数据源ID")
    private String shuJuYID;
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "数据值类型代码")
    private String shuJuZLXDM;
    @Schema(description = "数据值类型名称")
    private String shuJuZLXMC;
    @Schema(description = "领域代码")
    private String lingYuDM;
    @Schema(description = "领域名称")
    private String lingYuMC;
    @Schema(description = "数据源类别ID")
    private String shuJuYLBID;
    @Schema(description = "数据源类别名称")
    private String shuJuYLBMC;
    @Schema(description = "值域列表")
    private List<ZhiYuDto> zhiYuList;
    @Schema(description = "字段长度")
    private String ziDuanCD;
    @Schema(description = "精度")
    private String jingDu;
    @Schema(description = "最小值")
    private String zuiXiaoZhi;
    @Schema(description = "最大值")
    private String zuiDaZhi;
    @Schema(description = "正则表达式")
    private String zhengZeBDS;
    @Schema(description = "必填标志")
    private String biTianBZ;
    @Schema(description = "是否选择")
    private String shiFouXZ;
}
