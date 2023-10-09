package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 字段
 */
@Getter
@Setter
@ToString
public class FieldDTO {

    /**
     * sc_cx_shitumx.id
     */
    private String id;

    /**
     * 字段编码
     */
    private String ziDuanBM;

    /**
     * 字段名称
     */
    private String ziDuanMC;

    /**
     * 表名
     */
    private String biaoMing;

    /**
     * schema
     */
    private String moShi;

    /**
     * 数据来源类型代码
     */
    private String shuJuLyLXDM;

    /**
     * 数据元值的数据类型
     */
    private String shuJuZLXDM;

    /**
     * 字段关联子条件
     */
    private List<ShiTuMXGXDTO>  shiTuMXGXList;

    /**
     * 别名
     */
    private String  alias;
}
