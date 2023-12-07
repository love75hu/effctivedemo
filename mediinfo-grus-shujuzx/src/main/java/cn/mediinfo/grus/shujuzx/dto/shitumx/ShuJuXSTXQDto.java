package cn.mediinfo.grus.shujuzx.dto.shitumx;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 表
 */
@Getter
@Setter
@ToString
public class ShuJuXSTXQDto {

    @Schema(description = "表相关信息")
    private List<TableDTO> tableDTO;

    @Schema(description = "纯字段信息")
    private List<FieldDTO> fieldDTO;


}
