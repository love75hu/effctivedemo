package cn.mediinfo.grus.shujuzx.dto.shujuyzys;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_ShuJuYZYQueryDto {

    /**
   *数据源类别ID
    */
   @Schema(description = "数据源类别ID")
   private String shuJuYLBID;

    /**
   *代码级别
    */
   @Schema(description = "代码级别")
   private Integer daiMaJB;

   private Integer pageSize;

   private Integer pageIndex;

   private Integer zuoFeiBZ;

   private String likeQuery;

}
