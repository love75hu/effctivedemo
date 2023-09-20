package cn.mediinfo.grus.shujuzx.dto.quanshengmzqs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class YinSiPZItem {

    /**
     * 数据元名称
     */
    @Schema(description = "数据元名称")
    private String shuJuYMC;

    /**
     * 数据元列名
     */
    @Schema(description = "数据元列名")
    private String shuJuYLM;

    /**
     * 脱敏方式代码【DC0001】
     */
    @Schema(description = "脱敏方式代码【DC0001】")
    private String tuoMinFSDM;

}
