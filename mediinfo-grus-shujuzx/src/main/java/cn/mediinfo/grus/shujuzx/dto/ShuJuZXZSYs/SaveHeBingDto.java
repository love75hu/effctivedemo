package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
/**
*保存合并提交信息
*/
@Data
public class SaveHeBingDto {

    /**
   *主索引病人信息
    */
   @Schema(description = "主索引病人信息")
   private ZhuSuoYBRXX zhuSuoYBRXX;

    /**
   *合并的相似病人Id列表
    */
   @Schema(description = "合并的相似病人Id列表")
   private List<String> xiangSiBRIDList;

}
