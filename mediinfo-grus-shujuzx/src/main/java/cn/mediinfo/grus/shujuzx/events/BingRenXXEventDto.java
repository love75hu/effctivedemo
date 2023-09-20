package cn.mediinfo.grus.shujuzx.events;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 病人信息
 */
@Data
public class BingRenXXEventDto {
    /**
     *病人id
     */
    @Schema(description = "病人id")
    private String bingRenID;

    /**
     *病人姓名
     */
    @Schema(description = "病人姓名")
    private String bingRenXM;

    /**
     *证件类型代码
     */
    @Schema(description = "证件类型代码")
    private String zhengJianLXDM;

    /**
     *证件类型名称
     */
    @Schema(description = "证件类型名称")
    private String zhengJianLXMC;

    /**
     *证件号码
     */
    @Schema(description = "证件号码")
    private String zhengJianHM;

    /**
     *性别代码
     */
    @Schema(description = "性别代码")
    private String xingBieDM;

    /**
     *性别名称
     */
    @Schema(description = "性别名称")
    private String xingBieMC;

    /**
     *出生日期
     */
    @Schema(description = "出生日期")
    private String chuShengRQ;

    /**
     *姓名全拼
     */
    @Schema(description = "姓名全拼")
    private String xingMingQP;
    /**
     *建档人ID
     */
    @Schema(description = "建档人ID")
    private String jianDangRID;

    /**
     *建档人姓名
     */
    @Schema(description = "建档人姓名")
    private String jianDangRXM;

    /**
     *建档时间
     */
    @Schema(description = "建档时间")
    private String jianDangSJ;

}
