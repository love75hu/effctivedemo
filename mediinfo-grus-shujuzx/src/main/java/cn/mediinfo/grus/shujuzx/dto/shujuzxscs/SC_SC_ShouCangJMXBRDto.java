package cn.mediinfo.grus.shujuzx.dto.shujuzxscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_SC_ShouCangJMXBRDto {

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *姓名
    */
   @Schema(description = "姓名")
   private String xingMing;

}
