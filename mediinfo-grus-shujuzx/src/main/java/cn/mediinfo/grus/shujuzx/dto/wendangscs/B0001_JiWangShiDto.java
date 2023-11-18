package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


@Data
public class B0001_JiWangShiDto {
    /**
     * 既往患病种类代码
     */
    @Schema(description = "既往患病种类代码")
    private String jiBingZLDM;
    /**
     * 既往患病种类名称
     */
    @Schema(description = "既往患病种类名称")
    private String jiBingZLMC;
    /**
     * 既往患病确诊日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "既往患病确诊日期")
    private Date queZhenRQ;
}
