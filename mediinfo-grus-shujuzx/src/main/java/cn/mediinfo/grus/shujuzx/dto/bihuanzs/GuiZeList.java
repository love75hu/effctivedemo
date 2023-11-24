package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

/**
 * 规则list
 */
@Data
public class GuiZeList {
    /**
     * 规则ID
     */
    private String shiTuID;
    /**
     * 规则ID
     */
    private String shiTuMC;
    /**
     * 数据集ID
     */
    private String shuJuJID;
    /**
     * 数据集名称
     */
    private String shuJuJMC;
    /**
     * 表名
     */
    private String biaoMing;
    /**
     * 字段编码
     */
    private String ziDuanBM;
    /**
     * 字段名称
     */
    private String ziDuanMC;
    /**
     * 关系代码
     */
    private String guanXiDM;
    /**
     * 关系名称
     */
    private String guanXiMC;
    /**
     * 字段值
     */
    List<ZiDuanZhi> ziDuanZhi;
}
