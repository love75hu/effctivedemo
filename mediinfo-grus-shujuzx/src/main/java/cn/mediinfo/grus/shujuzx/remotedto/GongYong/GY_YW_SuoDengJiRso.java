package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
@Data
public class GY_YW_SuoDengJiRso {

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
   *业务ID
    */
   @Schema(description = "业务ID")
   private String yeWuID;

    /**
   *业务类型代码
    */
   @Schema(description = "业务类型代码")
   private String yeWuLXDM;

    /**
   *业务类型名称
    */
   @Schema(description = "业务类型名称")
   private String yeWuLXMC;

    /**
   *位置ID
    */
   @Schema(description = "位置ID")
   private String weiZhiID;

    /**
   *位置名称
    */
   @Schema(description = "位置名称")
   private String weiZhiMC;

    /**
   *修改人ID
    */
   @Schema(description = "修改人ID")
   private String xiuGaiRID;

    /**
   *修改人姓名
    */
   @Schema(description = "修改人姓名")
   private String xiuGaiRXM;

    /**
   *修改时间
    */
   @Schema(description = "修改时间")
   private Date xiuGaiSJ;

}
