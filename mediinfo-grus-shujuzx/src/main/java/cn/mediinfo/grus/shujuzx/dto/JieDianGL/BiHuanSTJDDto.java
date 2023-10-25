package cn.mediinfo.grus.shujuzx.dto.JieDianGL;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BiHuanSTJDDto {
    private String id;
    @Schema(description = "节点id")
    private String jieDianID;
    @Schema(description = "节点名称")
    private String jieDianMC;
    @Schema(description = "顺序号")
    private String shunXuHAO;
    @Schema(description = "事件字段编码")
    private String shiJianZDBM;
    @Schema(description = "事件字段名称")
    private String shiJianZDMC;
    @Schema(description = "事件代码")
    private String shiJianDM;
    @Schema(description = "事件名称")
    private String shiJianMC;
    @Schema(description = "关联关系")
    private List<GuanLianJDDto> guanLianJD;
    @Schema(description = "节点内容")
    private List<JieDianNRDto> jieDianNR;

}
