package cn.mediinfo.grus.shujuzx.dto.ShuJuYZYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_ShuJuYZYItemDto {

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
   *顺序号
    */
   @Schema(description = "顺序号")
   private Integer shunXuHao;

}
