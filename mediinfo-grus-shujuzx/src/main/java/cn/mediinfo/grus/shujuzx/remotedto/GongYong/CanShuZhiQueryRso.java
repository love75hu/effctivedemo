package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
*参数查询
*/
@Data
public class CanShuZhiQueryRso {

    /**
   *参数名称
    */
   @Schema(description = "参数名称")
   private String canShuMC;

    /**
   *参数默认值
    */
   @Schema(description = "参数默认值")
   private String canShuMRZ;

}
