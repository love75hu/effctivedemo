package cn.mediinfo.grus.shujuzx.dto.wendangscs;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;


@Data
public class B0001_ShouShuShiDto {

    /**
     * 手术史
     */
    @Schema(description = "手术史")
    private String shouShuMC;
    /**
     * 手术(操作)日期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Schema(description = "手术(操作)日期时间")
    private Date shouShuRQ;
}
