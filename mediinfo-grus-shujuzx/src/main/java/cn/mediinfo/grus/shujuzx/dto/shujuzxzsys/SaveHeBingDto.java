package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
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
