package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class GetQuanZhongPZListByFLIDInDto {

    /**
   *分类ID
    */
   @Schema(description = "分类ID")
   private String fuLeiID;

    /**
   *搜索条件
    */
   @Schema(description = "搜索条件")
   private String likeQuery;

}
