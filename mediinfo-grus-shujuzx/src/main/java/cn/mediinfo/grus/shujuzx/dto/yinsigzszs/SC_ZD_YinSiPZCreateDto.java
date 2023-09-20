package cn.mediinfo.grus.shujuzx.dto.yinsigzszs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
/**
*新增隐私配置
*/
@Data
public class SC_ZD_YinSiPZCreateDto {

    /**
   *新增
    */
   @Schema(description = "新增")
   private List<SC_ZD_YinSiPZDto> addList = new ArrayList<>();

    /**
   *作废IDs
    */
   @Schema(description = "作废IDs")
   private List<String> zuoFeiIds = new ArrayList<>();

}
