package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
*合并规则权重配置列表
*/
@Data
public class BR_ZD_HeBingGZQZPZDto {

    /**
   *分类名称
    */
   @Schema(description = "分类名称")
   private String fenLeiMC;

    /**
   *权重配置明细
    */
   @Schema(description = "权重配置明细")
   private List<QuanZhongPZListDto> quanZhongPZList;

}
