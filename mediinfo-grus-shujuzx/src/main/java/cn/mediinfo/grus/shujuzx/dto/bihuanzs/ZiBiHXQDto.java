package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class ZiBiHXQDto {

   /**
    * 闭环id
    */
   private String biHuanID;
   /**
    * 组织机构id
    */
   private String zuZhiJGID;

   /**
    * 子闭环多次执行标志
    * */
   private String ziBiHDCZXBZ;


   /**
    * 节点id
    */
   private String jieDianID;


   private List<ZiDuanRCDto> ruCanList;
}
