package cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs;
import java.util.*;
import lombok.Data;
import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
public class SaveQuanZhongPZDto {

   private List<BR_ZD_HeBingQZPZListDto> addList;

   private List<BR_ZD_HeBingQZPZListDto> updateList;

   private List<String> zuoFeiIds;

}
