package cn.mediinfo.grus.shujuzx.dto.bihuandy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class SC_BH_DiaoYongPZAddDto {
    /**
     * 功能点代码
     */
    @Schema(description = "功能点代码")

    private String gongNengDDM;
    /**
     * 功能点名称
     */
    @Schema(description = "功能点名称")

    private String gongNengDMC;
    /**
     * 闭环ID
     */
    @Schema(description = "闭环ID")

    private String biHuanID;
    /**
     * 闭环名称
     */
    @Schema(description = "闭环名称")

    private String biHuanMC;
    /**
     * 条件
     */
    @Schema(description = "条件")

    private String tiaoJian;
    /**
     * 条件描述
     */
    @Schema(description = "条件描述")

    private String tiaoJianMS;
    /**
     * 顺序号
     */
    @Schema(description = "顺序号")

    private Integer shunXuHao;
}
