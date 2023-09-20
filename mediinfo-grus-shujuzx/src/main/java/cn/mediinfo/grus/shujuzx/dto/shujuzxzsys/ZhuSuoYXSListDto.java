package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import lombok.Data;

import java.util.List;

/**
*主索引详情
*/
@Data
public class ZhuSuoYXSListDto {

   private List<BR_DA_JiBenXXDto> bingRenXXList;

   private List<JieZhiXXDto> guanLianYXXList;

}
