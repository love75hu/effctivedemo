package cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class AddFangWenRZDto {

    /**
   *访问机构ID
    */
   @Schema(description = "访问机构ID")
   private String fangWenJGID;

    /**
   *访问机构名称
    */
   @Schema(description = "访问机构名称")
   private String fangWenJGMC;

    /**
   *查询模式代码
    */
   @Schema(description = "查询模式代码")
   private String chaXunMSDM;

    /**
   *查询模式名称
    */
   @Schema(description = "查询模式名称")
   private String chaXunMSMC;

    /**
   *患者姓名
    */
   @Schema(description = "患者姓名")
   private String xingMing;

    /**
   *MPI
    */
   @Schema(description = "MPI")
   private String bingRenID;

    /**
   *功能ID
    */
   @Schema(description = "功能ID")
   private String gongNengID;

    /**
   *功能名称
    */
   @Schema(description = "功能名称")
   private String gongNengMC;

}
