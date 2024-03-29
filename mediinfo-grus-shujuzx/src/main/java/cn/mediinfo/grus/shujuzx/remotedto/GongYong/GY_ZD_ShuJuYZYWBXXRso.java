package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
public class GY_ZD_ShuJuYZYWBXXRso {

    /**
   *内部值域ID
    */
   @Schema(description = "内部值域ID")
   private String neiBuZYID;

    /**
   *内部值域名称
    */
   @Schema(description = "内部值域名称")
   private String neiBuZYMC;

    /**
   *值域ID
    */
   @Schema(description = "值域ID")
   private String zhiYuID;

    /**
   *值域名称
    */
   @Schema(description = "值域名称")
   private String zhiYuMC;

}
