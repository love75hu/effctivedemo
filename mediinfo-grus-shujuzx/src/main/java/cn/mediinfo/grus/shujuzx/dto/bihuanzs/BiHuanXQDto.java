package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class BiHuanXQDto {
    /**
     * 闭环ID
     * */
    private String biHuanID;
    /**
     * 闭环名称
     * */
     private String biHuanMC;

     /**
      * 节点信息
      * */
     private List<JieDianList> jieDianList;

    private List<ZiDuanBMMC> ziBiHXSLList;




}
