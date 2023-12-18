package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import cn.mediinfo.grus.shujuzx.dto.bihuansz.ZiBiHXSLDto;
import lombok.Data;

import java.util.List;

@Data
public class ZiDuanBMMC {


    /**
     * 子闭环展示列
     */
    private List<ZhanShiLList> zhanShiLLists;

    /**
     * 字段名称
     * */
    private String ziDuanMC;

    /**
     * 子闭环信息
     * */
      private List<JieDianList> jieDianXX;
}
