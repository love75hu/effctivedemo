package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


@Data
public class B0001_WaiShangShiDto {
    /**
     * 外伤史标志
     */
    @Schema(description = "外伤史标志")
    private Integer jiWangWSSBZ;
    /**
     * 外伤名称
     */
    @Schema(description = "外伤名称")
    private String waiShangMC;
    /**
     * 外伤发生日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "外伤发生日期时间")
    private Date faShengRQ;
}
