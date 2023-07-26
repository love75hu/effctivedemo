package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*权重配置列表
*/
@Data
public class QuanZhongPZListDto {

    /**
   *分类名称
    */
   @Schema(description = "分类名称")
   private String fenLeiMC;

    /**
   *合并权重ID
    */
   @Schema(description = "合并权重ID")
   private String heBingQZID;

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
   *权重(1-100)
    */
   @Schema(description = "权重(1-100)")
   private BigDecimal quanZhong;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

}
