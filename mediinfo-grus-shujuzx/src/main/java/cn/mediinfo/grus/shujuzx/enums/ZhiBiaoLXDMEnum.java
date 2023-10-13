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
    SHU_JU_YUAN("1", "数据元"),

    JIAN_YAN("2", "检验"),

    JIAN_CHA("3", "检查"),

    YAO_PIN("4", "药品");

    private final String zhiBiaoLXDM;

    private final String zhiBiaoLXMC;


    }
