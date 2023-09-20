package cn.mediinfo.grus.shujuzx.remotedto.GongYong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class YiShangSuoQueryRso {

    /**
   *组织机构ID
    */
   @Schema(description = "组织机构ID")
   private String zuZhiJGID;

    /**
   *位置ID
    */
   @Schema(description = "位置ID")
   private String weiZhiID;

    /**
   *操作人ID
    */
   @Schema(description = "操作人ID")
   private String caoZuoRID;

    /**
   *业务ID列表
    */
   @Schema(description = "业务ID列表")
   private List<String> yeWuIDList;

   private Integer pageIndex;

   private Integer pageSize;

}
