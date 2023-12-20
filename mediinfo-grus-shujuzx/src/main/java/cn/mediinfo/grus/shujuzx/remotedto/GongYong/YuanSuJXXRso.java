package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class YuanSuJXXRso {
    @Schema(description = "数据集ID")
    private String shuJuJID;
    @Schema(description = "数据集名称")
    private String shuJuJMC;
    @Schema(description = "表名")
    private String biaoMing;
    @Schema(description = "数据源id")
    private String shuJuYID;
    @Schema(description = "数据源名称")
    private String shuJuYMC;
    @Schema(description = "字段编码")
    private String ziDuanBM;
    @Schema(description = "字段名称")
    private String ziDuanMC;
    @Schema(description = "数据值类型代码【GY0022】")
    private String shuJuZLXDM;
    @Schema(description = "数据值类型名称[1.数字2.字符3.字典4.枚举5.时间6.日期7.日期时间8.二进制]")
    private String shuJuZLXMC;
    @Schema(description = " 领域代码【GY0023】")
    private String lingYuDM;
    @Schema(description = "领域名称")
    private String lingYuMC;
    @Schema(description = "数据源类别ID")
    private String shuJuYLBID;
    @Schema(description = "数据源类别名称")
    private String shuJuYLBMC;
    @Schema(description = "有数据值类型为枚举时才有值域列表")
    private List<zhiYuListRso> zhiYuList;
    @Schema(description = " 字段长度")
    private Integer ziDuanCD;
    @Schema(description = "精度")
    private String jingDu;
    @Schema(description = "最小值")
    private String zuiXiaoZhi;
    @Schema(description = "最大值")
    private String zuiDaZhi;
    @Schema(description = "正则表达式")
    private String zhengZeBDS;
    @Schema(description = " 必填标志")
    private String biTianBZ;
    @Schema(description = "是否选择")
    private Integer shiFouXZ;
}
