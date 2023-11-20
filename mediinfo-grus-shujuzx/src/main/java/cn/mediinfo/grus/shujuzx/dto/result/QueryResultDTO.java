package cn.mediinfo.grus.shujuzx.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "结果列表")
public class QueryResultDTO {
    public QueryResultDTO(){

    }

    public QueryResultDTO(String ziDuanDM,String ziDuanMC,Object ziDuanZhi) {
        this.ziDuanDM = ziDuanDM;
        this.ziDuanMC = ziDuanMC;
        this.ziDuanZhi = ziDuanZhi;
        this.type = 0;
    }

    public QueryResultDTO(String ziDuanDM,String ziDuanMC,Object ziDuanZhi,Integer type) {
        this.ziDuanDM = ziDuanDM;
        this.ziDuanMC = ziDuanMC;
        this.ziDuanZhi = ziDuanZhi;
        this.type = type;
    }

    @Schema(description = "字段代码")
    private String ziDuanDM;

    @Schema(description = "字段名称")
    private String ziDuanMC;

    @Schema(description = "字段值")
    private Object ziDuanZhi;

    @Schema(description = "0-展示字段，1-标签,2-链接")
    private Integer type;
}
