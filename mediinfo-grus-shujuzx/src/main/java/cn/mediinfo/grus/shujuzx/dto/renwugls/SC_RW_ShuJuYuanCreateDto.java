package cn.mediinfo.grus.shujuzx.dto.renwugls;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SC_RW_ShuJuYuanCreateDto {
    @Schema(description = "任务id")
    private String renWuID;

    private List<SC_RW_ShuJuYuanDto> shuJuYuanDtoList;
}
