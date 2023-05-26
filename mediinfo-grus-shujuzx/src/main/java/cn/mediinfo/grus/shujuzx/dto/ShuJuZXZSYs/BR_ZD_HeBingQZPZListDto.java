package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class BR_ZD_HeBingQZPZListDto {

    /**
   *ID
    */
   @Schema(description = "ID")
   private String id;

    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;

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
   *分类ID
    */
   @Schema(description = "分类ID")
   private String fuLeiID;

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

    /**
   *末级标志
    */
   @Schema(description = "末级标志")
   private Integer moJiBZ;

}
