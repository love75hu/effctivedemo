package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class AddBiHuanSZXXDto {
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

    List<JieDianXXSZDto> jieDianXXSZDtoList;

}
