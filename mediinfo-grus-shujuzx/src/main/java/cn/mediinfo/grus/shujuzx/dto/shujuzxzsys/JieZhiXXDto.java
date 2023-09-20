package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
/**
*介质信息
*/
@Data
public class JieZhiXXDto {

    /**
   *病人ID
    */
   @Schema(description = "病人ID")
   private String bingRenID;

    /**
   *第三方病人ID
    */
   @Schema(description = "第三方病人ID")
   private String diSanFBRID;

    /**
   *类型名称
    */
   @Schema(description = "类型名称")
   private String leiXingMC;

    /**
   *建档时间
    */
   @Schema(description = "建档时间")
   private Date chuangJianSJ;

    /**
   *组织机构名称
    */
   @Schema(description = "组织机构名称")
   private String zuZhiJGMC;

    /**
   *创建系统Id
    */
   @Schema(description = "创建系统Id")
   private String chuangJianXTID;

    /**
   *介质号
    */
   @Schema(description = "介质号")
   private String jieZhiHao;

}
