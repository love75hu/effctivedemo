package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


@Data
public class B0001_ShuXueShiDto {

    /**
     * 输血原因
     */
    @Schema(description = "输血原因")
    private String shuXueYY;
    /**
     * 输血日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "输血日期时间")
    private Date shuXueRQ;
}
