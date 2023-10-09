package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 表关联关系
 */
@Getter
@Setter
@ToString
public class TableRelationCondition {

    /**
     * 表名
     */
    private String biaoMing;

    /**
     * schema
     */
    private String moShi;


    /**
     *  字段
     */
    private String ziduan;

    /**
     * 关联表名
     */
    private String glBiaoMing;


    /**
     * 关联表的schema
     */
    private String glMoShi;


    /**
     * 关联字段
     */
    private String glZiDuan;
}
