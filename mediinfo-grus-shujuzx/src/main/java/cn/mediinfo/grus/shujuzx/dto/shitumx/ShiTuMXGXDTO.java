package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字段关联子条件
 */
@Getter
@Setter
@ToString
public class ShiTuMXGXDTO {

    /**
     * 表名
     */
    private String biaoMing;

    /**
     * schema
     */
    private String moShi;

    /**
     * 字段编码
     */
    private String ziDuanBM;

    /**
     * 字段名称
     */
    private String ziDuanMC;

}
