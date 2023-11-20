package cn.mediinfo.grus.shujuzx.dto.shujuzxscs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SC_SC_ShouCangJMXSCJDto {

    /**
   *收藏夹ID
    */
   @Schema(description = "收藏夹ID")
   private String shouCangJID;

    /**
   *收藏夹名称
    */
   @Schema(description = "收藏夹名称")
   private String shouCangJMC;

}
