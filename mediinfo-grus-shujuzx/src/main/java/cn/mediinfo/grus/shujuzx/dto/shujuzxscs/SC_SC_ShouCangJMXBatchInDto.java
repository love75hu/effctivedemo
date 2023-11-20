package cn.mediinfo.grus.shujuzx.dto.shujuzxscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SC_SC_ShouCangJMXBatchInDto {

    /**
   *病人List
    */
   @Schema(description = "病人List")
   private List<SC_SC_ShouCangJMXBRDto> bingRenIDList;


    /**
   *收藏夹List
    */
   @Schema(description = "收藏夹List")
   private List<SC_SC_ShouCangJMXSCJDto> shouCangJIDList;


}
