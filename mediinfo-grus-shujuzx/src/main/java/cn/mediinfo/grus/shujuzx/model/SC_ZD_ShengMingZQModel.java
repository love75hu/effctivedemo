package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_zd_shengmingzq")
public class SC_ZD_ShengMingZQModel extends StringMTEntity {
    @Column(columnDefinition = "生命周期ID")
    private String shengMingZQID;
    @Column(columnDefinition = "生命周期名称")
    private String shengMingZQMC;
    @Column(columnDefinition = "起始年龄")
    private Integer qiShiNL;
    @Column(columnDefinition = "结束年龄")
    private Integer jieShuNL;
    @Column(columnDefinition = "男性图片")
    private String nanXingTP;
    @Column(columnDefinition = "女性图片")
    private String nvXingTP;
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
    @Column(columnDefinition = "组织机构id")
    private String zuZhiJGID;
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
}
