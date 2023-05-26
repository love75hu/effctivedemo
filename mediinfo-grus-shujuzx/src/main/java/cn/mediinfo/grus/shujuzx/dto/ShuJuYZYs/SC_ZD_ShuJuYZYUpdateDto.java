package cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_ShuJuYZYUpdateDto {

    /**
   *值域ID
    */
   @Schema(description = "值域ID")
   private String zhiYuID;

    /**
   *值域名称
    */
   @Schema(description = "值域名称")
   private String zhiYuMC;

    /**
   *数据源类别ID
    */
   @Schema(description = "数据源类别ID")
   private String shuJuYLBID;

    /**
   *数据源类别名称
    */
   @Schema(description = "数据源类别名称")
   private String shuJuYLBMC;

    /**
   *标准代码
    */
   @Schema(description = "标准代码")
   private String biaoZhunDM;

    /**
   *标准名称
    */
   @Schema(description = "标准名称")
   private String biaoZhunMC;

    /**
   *代码级别
    */
   @Schema(description = "代码级别")
   private Integer daiMaJB;

    /**
   *父级代码（非父级标准代码）
    */
   @Schema(description = "父级代码（非父级标准代码）")
   private String fuJiDM;

    /**
   *末级标志
    */
   @Schema(description = "末级标志")
   private Integer moJiBZ;

    /**
   *备注说明
    */
   @Schema(description = "备注说明")
   private String beiZhuSM;

    /**
   *输入码1
    */
   @Schema(description = "输入码1")
   private String shuRuMa1;

    /**
   *输入码2
    */
   @Schema(description = "输入码2")
   private String shuRuMa2;

    /**
   *输入码3
    */
   @Schema(description = "输入码3")
   private String shuRuMa3;

    /**
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
