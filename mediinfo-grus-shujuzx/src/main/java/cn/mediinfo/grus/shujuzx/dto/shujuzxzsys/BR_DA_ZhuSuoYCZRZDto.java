package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;
import java.util.*;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*主索引操作日志
*/
@Data
public class BR_DA_ZhuSuoYCZRZDto {

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
   *操作类型名称[1.合并2.取消合并3.忽略4.修改5.新增]
    */
   @Schema(description = "操作类型名称[1.合并2.取消合并3.忽略4.修改5.新增]")
   private String caoZuoLXMC;

    /**
   *操作内容
    */
   @Schema(description = "操作内容")
   private String caoZuoNR;

    /**
   *操作人姓名
    */
   @Schema(description = "操作人姓名")
   private String caoZuoRXM;

    /**
   *操作时间
    */
   @Schema(description = "操作时间")
   private Date caoZuoSJ;

    /**
   *Id
    */
   @Schema(description = "Id")
   private String id;

}
