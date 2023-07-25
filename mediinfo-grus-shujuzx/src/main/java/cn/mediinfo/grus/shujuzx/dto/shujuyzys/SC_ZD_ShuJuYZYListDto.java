package cn.mediinfo.grus.shujuzx.dto.shujuyzys;
import java.util.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_ZD_ShuJuYZYListDto {

    /**
   *数据源类别ID
    */
   @Schema(description = "数据源类别ID")
   private String shuJuYLBID;

    /**
   *值域List
    */
   @Schema(description = "值域List")
   private List<SC_ZD_ShuJuYZYItemDto> zhiYuList;

}
