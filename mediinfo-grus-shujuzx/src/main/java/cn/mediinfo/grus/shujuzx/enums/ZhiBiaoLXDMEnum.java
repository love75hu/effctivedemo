package cn.mediinfo.grus.shujuzx.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 指标类型代码;1-数据元，2-检验，3-检查，4-药品
 */
@Getter
@AllArgsConstructor
public enum ZhiBiaoLXDMEnum {

    /**
     * 指标类型代码;1-数据元，2-检验，3-检查，4-药品
     */
    DATA_ELEMENT("1", "数据元"),

    LAB("2", "检验"),

    INSPECT("3", "检查"),

    DRUG("4", "药品");

    private final String zhiBiaoLXDM;

    private final String zhiBiaoLXMC;


    }
