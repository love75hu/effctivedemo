package cn.mediinfo.grus.shujuzx.dto.shujuylbs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
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
