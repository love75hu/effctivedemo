package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class ZiDuanBMMC {
    /**
     * 字段编码
     */
    private String ziDuanBM;
    /**
     * 字段值
     */
    private String ziDuanZhi;

    /**
     * 字段名称
     * */
    private String ziDuanMC;

    /**
     * 子闭环信息
     * */
      private List<JieDianList> jieDianXX;
}
