package cn.mediinfo.grus.shujuzx.events;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/// <summary>
/// 消息头
/// </summary>
@Data
public class XiaoXiTouEventDto {
    /**
     *消息类型
     */
    @Schema(description = "消息类型")
    private String xiaoXiLX;

    /**
     *消息名称
     */
    @Schema(description = "消息名称")
    private String xiaoXiMC;

    /**
     *消息id
     */
    @Schema(description = "消息id")
    private String xiaoXiID;

    /**
     *发送时间
     */
    @Schema(description = "发送时间")
    private String faSongSJ;

    /**
     *发送机构id
     */
    @Schema(description = "发送机构id")
    private String faSongJGID;

    /**
     *发送机构名称
     */
    @Schema(description = "发送机构名称")
    private String faSongJGMC;
    /**
     *发送系统简称
     */
    @Schema(description = "发送系统简称")
    private String faSongXTJC;


    /**
     *发送系统名称
     */
    @Schema(description = "发送系统名称")
    private String faSongXTMC;
}
