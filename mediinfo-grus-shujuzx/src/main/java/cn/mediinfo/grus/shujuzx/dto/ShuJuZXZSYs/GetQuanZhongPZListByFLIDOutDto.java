package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class GetQuanZhongPZListByFLIDOutDto {

    /**
   *主键
    */
   @Schema(description = "主键")
   private String id;

    /**
   *代码
    */
   @Schema(description = "代码")
   private String daiMa;

    /**
   *名称
    */
   @Schema(description = "名称")
   private String mingCheng;

    /**
   *权重
    */
   @Schema(description = "权重")
   private String quanZhong;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private String shunXuHao;

}
