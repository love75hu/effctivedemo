package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 视图配置的sql过滤条件
 *  gy_zd_shujustxx.guolvtj
 */
@Getter
@Setter
@ToString
public class SqlFilterCondition {

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
     * 操作符
     */
    private String operator;

    /**
     * 值
     */
    private String val;
}
