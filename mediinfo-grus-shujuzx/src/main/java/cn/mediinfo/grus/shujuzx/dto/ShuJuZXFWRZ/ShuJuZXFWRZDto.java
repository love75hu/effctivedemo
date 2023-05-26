package cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class ShuJuZXFWRZDto {

    /**
   *访问机构名称
    */
   @Schema(description = "访问机构名称")
   private String fangWenJGMC;

    /**
   *访问人
    */
   @Schema(description = "访问人")
   private String fangWenRXM;

    /**
   *访问时间
    */
   @Schema(description = "访问时间")
   private Date fangWenSJ;

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
   *功能名称
    */
   @Schema(description = "功能名称")
   private String gongNengMC;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
