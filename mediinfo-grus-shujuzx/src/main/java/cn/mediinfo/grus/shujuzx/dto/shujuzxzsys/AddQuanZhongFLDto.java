package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class AddQuanZhongFLDto {

    /**
   *分类名称
    */
   @Schema(description = "分类名称")
   private String fuLeiMC;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

}
