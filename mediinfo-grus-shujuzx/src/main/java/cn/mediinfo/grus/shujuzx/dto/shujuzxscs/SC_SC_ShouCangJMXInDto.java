package cn.mediinfo.grus.shujuzx.dto.shujuzxscs;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SC_SC_ShouCangJMXInDto {

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

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
