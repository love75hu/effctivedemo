package cn.mediinfo.grus.shujuzx.model;

import java.util.*;

import cn.mediinfo.starter.base.multitenancy.entity.StringMTEntity;
import lombok.Data;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "br_da_hebingjl")
public class BR_DA_HeBingJLModel extends StringMTEntity {
    @Column(columnDefinition = "病人ID")
    private String bingRenID;
    @Column(columnDefinition = "合并状态代码0-无合并；1-合并后主数据；2-被合并数据")
    private String heBingZTDM;
    @Column(columnDefinition = "合并状态名称")
    private String heBingZTMC;
    @Column(columnDefinition = "最大相似度(0-100)")
    private Integer zuiDaXSD;
    @Column(columnDefinition = "合并数")
    private Integer heBingShu;
    @Column(columnDefinition = "相似数")
    private Integer xiangSiShu;
    private String zuZhiJGID;
    private String zuZhiJGMC;

}
