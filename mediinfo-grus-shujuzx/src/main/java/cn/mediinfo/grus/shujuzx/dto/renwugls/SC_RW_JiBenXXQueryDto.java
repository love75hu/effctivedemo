package cn.mediinfo.grus.shujuzx.dto.renwugls;

import cn.mediinfo.grus.shujuzx.events.BaseEventDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_RW_JiBenXXQueryDto {

    @Schema(description = "分类代码[SC0017]")
    private String fenLeiDM;

    private String likeQuery;

    private Integer pageSize;

    private Integer pageIndex;

    private Integer qiYongBZ;


}
