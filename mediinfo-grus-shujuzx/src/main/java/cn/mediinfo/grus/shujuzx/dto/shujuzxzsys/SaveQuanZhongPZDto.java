package cn.mediinfo.grus.shujuzx.dto.shujuzxzsys;

import lombok.Data;

import java.util.List;

@Data
public class SaveQuanZhongPZDto {

   private List<BR_ZD_HeBingQZPZListDto> addList;

   private List<BR_ZD_HeBingQZPZListDto> updateList;

   private List<String> zuoFeiIds;

}
