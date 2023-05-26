package cn.mediinfo.grus.shujuzx.dto.ShuJuYLBs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_ShuJuYLBQueryDto {

    /**
   *标准类型
    */
   @Schema(description = "标准类型")
   private List<String> biaoZhunLXList;

    /**
   *类别ID
    */
   @Schema(description = "类别ID")
   private List<String> shuJuYLBIDList;

   private Integer pageSize;

   private Integer pageIndex;

   private Integer zuoFeiBZ;

   private String likeQuery;

}
