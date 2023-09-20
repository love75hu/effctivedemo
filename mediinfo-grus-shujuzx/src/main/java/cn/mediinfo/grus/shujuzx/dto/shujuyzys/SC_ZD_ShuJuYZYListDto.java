package cn.mediinfo.grus.shujuzx.dto.shujuyzys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
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
