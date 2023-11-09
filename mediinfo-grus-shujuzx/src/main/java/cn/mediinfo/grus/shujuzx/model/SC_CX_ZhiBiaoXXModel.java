package cn.mediinfo.grus.shujuzx.model;

import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 指标信息*/
@Data
@Entity
@Table(name = "sc_cx_zhibiaoxx")
public class SC_CX_ZhiBiaoXXModel extends StringMTEntity {
    /**
     * 组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    /**
     * 指标类型代码;1-数据元，2-检验，3-检查，4-药品
     */
    @Column(columnDefinition = "指标类型代码;1-数据元，2-检验，3-检查，4-药品")
    private String zhiBiaoLXDM;
    /**
     * 指标类型名称
     */
    @Column(columnDefinition = "指标类型名称")
    private String zhiBiaoLXMC;
    /**
     * 指标分类ID（字段存视图id，检验项目，检查项目id，药品分类）
     */
    @Column(columnDefinition = "指标分类ID（字段存视图id，检验项目，检查项目id，药品分类）")
    private String zhiBiaoFLID;
    /**
     * 指标分类名称
     */
    @Column(columnDefinition = "指标分类名称")
    private String zhiBiaoFLMC;
    /**
     * 指标ID（字段编码，检验明细项目，检查部位，药品规格id）
     */
    @Column(columnDefinition = "指标ID（字段编码，检验明细项目，检查部位，药品规格id）")
    private String zhiBiaoID;
    /**
     * 指标名称（药品存药品名称+规格）
     */
    @Column(columnDefinition = "指标名称（药品存药品名称+规格）")
    private String zhiBiaoMC;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
}