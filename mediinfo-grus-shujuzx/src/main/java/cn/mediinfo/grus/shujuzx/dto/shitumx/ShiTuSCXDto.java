package cn.mediinfo.grus.shujuzx.dto.shitumx;

import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/***
 * 输出项
 */
@Data
public class ShiTuSCXDto {
    @Schema(description = "输出")
    public List<ShiTuMXZHCXDto> shuChuDto;
    @Schema(description = "条件")
    public List<ShiTuMXZHCXDto> tiaoJianDto;
    @Schema(description = "输出必选")
    public List<SC_CX_ShiTuMXModel> shuChuBXDto;
}
