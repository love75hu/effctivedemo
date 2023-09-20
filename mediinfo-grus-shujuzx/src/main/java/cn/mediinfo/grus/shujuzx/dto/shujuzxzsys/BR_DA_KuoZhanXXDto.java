package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
*病人扩展信息
*/
@Data
public class BR_DA_KuoZhanXXDto {

    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *项目代码
    */
   @Schema(description = "项目代码")
   private String xiangMuDM;

    /**
   *项目名称
    */
   @Schema(description = "项目名称")
   private String xiangMuMC;

    /**
   *项目值代码
    */
   @Schema(description = "项目值代码")
   private String xiangMuZDM;

    /**
   *项目值名称
    */
   @Schema(description = "项目值名称")
   private String xiangMuZMC;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
