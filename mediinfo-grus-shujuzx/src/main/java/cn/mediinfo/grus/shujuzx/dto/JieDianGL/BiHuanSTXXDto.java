package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BiHuanSTXXDto {

private String id;
@Schema(description = "闭环类型代码（[SC0013]）")
private String biHuanLXDM;
@Schema(description = "闭环类型名称")
private String biHuanLXMC;
@Schema(description = "视图名称")
private String shiTuMC;
@Schema(description = "视图类型代码")
private String shiTuLXDM;
@Schema(description = "视图类型名称 [1：列存储；2：行存储]")
private String shiTuLXMC;
@Schema(description = "数据来源类型代码")
private String shuJuLYLXDM;
@Schema(description = "数据来源类型名称[1.数据集2.数据视图]")
private String shuJuLYLXMC;
@Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
private String shuJuLYID;
@Schema(description = "数据来源名称")
private String shuJuLYMC;
@Schema(description = "顺序号")
private Integer shunXuHAO;

}
