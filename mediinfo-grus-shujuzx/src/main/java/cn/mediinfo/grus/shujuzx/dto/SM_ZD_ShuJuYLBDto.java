package cn.mediinfo.grus.shujuzx.dto;

import jakarta.persistence.Column;

public class SM_ZD_ShuJuYLBDto {
    /**
     *组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;

    /**
     *组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;

    /**
     *数据源类别ID
     */
    @Column(columnDefinition = "数据源类别ID")
    private String shuJuYLBID;

    /**
     *数据源类别名称
     */
    @Column(columnDefinition = "数据源类别名称")
    private String shuJuYLBMC;

    /**
     *标准类型：1-国际，2-国标，3-行标，4-业务，99-平台字典
     */
    @Column(columnDefinition = "标准类型：1-国际，2-国标，3-行标，4-业务，99-平台字典")
    private String biaoZhunLX;

    /**
     *标准说明
     */
    @Column(columnDefinition = "标准说明")
    private String biaoZhunSM;

    /**
     *代码值域存储对象
     */
    @Column(columnDefinition = "代码值域存储对象")
    private String zhiYuCC;

    /**
     *数据元分类代码
     */
    @Column(columnDefinition = "数据元分类代码")
    private String shuJuYFLDM;

    /**
     *数据元分类名称
     */
    @Column(columnDefinition = "数据元分类名称")
    private String shuJuYFLMC;

    /**
     *OID
     */
    @Column(columnDefinition = "OID")
    private String oID;

    /**
     *备注
     */
    @Column(columnDefinition = "备注")
    private String beiZhu;

    /**
     *输入码1
     */
    @Column(columnDefinition = "输入码1")
    private String shuRuMa1;

    /**
     *输入码2
     */
    @Column(columnDefinition = "输入码2")
    private String shuRuMa2;

    /**
     *输入码3
     */
    @Column(columnDefinition = "输入码3")
    private String shuRuMa3;

    /**
     *顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
}
