package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ShuJuLYDto {
    /**
     * 数据来源ID[1.数据集ID2.数据视图ID]
     */
    @Schema(description = "数据来源ID[1.数据集ID2.数据视图ID]")
    private String shuJuLYID;

    /**
     * 数据来源类型代码
     */
    @Schema(description = "数据来源类型代码")
    private String shuJuLYLXDM;



}
