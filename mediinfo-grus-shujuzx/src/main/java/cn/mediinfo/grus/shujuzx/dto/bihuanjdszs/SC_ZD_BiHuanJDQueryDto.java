package cn.mediinfo.grus.shujuzx.dto.bihuanjdszs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class SC_ZD_BiHuanJDQueryDto {

    /**
   *闭环类型代码【DC0004】
    */
   @Schema(description = "闭环类型代码【DC0004】")
   private String biHuanLXDM;

   private Integer pageSize;

   private Integer pageIndex;

   private Integer zuoFeiBZ;

   private String likeQuery;

}
