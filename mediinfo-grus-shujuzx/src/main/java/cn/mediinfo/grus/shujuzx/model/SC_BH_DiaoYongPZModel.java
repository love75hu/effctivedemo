package cn.mediinfo.grus.shujuzx.model;
import lombok.Data;
import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

/*** 闭环调用配置*/
@Data
@Entity
@Table(name = "sc_bh_diaoyongpz")
public class SC_BH_DiaoYongPZModel extends StringMTEntity {
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
     * 功能点代码
     */
    @Column(columnDefinition = "功能点代码")
    private String gongNengDDM;
    /**
     * 功能点名称
     */
    @Column(columnDefinition = "功能点名称")
    private String gongNengDMC;
    /**
     * 闭环ID
     */
    @Column(columnDefinition = "闭环ID")
    private String biHuanID;
    /**
     * 闭环名称
     */
    @Column(columnDefinition = "闭环名称")
    private String biHuanMC;
    /**
     * 条件
     */
    @Column(columnDefinition = "条件")
    private String tiaoJian;
    /**
     * 条件描述
     */
    @Column(columnDefinition = "条件描述")
    private String tiaoJianMS;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
    /**
     * 启用标志[0:不启用；1：启用]
     */
    @Column(columnDefinition = "启用标志[0:不启用；1：启用]")
    private Integer qiYongBZ;
}