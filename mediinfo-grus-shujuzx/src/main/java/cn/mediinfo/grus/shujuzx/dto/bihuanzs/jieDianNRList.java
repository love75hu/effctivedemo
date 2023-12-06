package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

@Data
public class jieDianNRList {
    /**
     * 字段编码
     */
    private String ziDuanBM;
    /**
     * 字段名称
     */
    private String ziDuanMC;
    /**
     * 字段值
     */
    private String ziDuanZhi;
    /**
     * 控制时间标志
     */
    private Integer kongZhiSJBZ;
    /**
     * 允许为空标志
     */
    private Integer yunXuWKBZ;
}
