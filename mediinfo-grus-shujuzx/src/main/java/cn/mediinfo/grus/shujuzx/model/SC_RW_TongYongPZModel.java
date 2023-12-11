package cn.mediinfo.grus.shujuzx.model;

import cn.mediinfo.cyan.msf.tenant.orm.entity.StringMTEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="sc_rw_tongyongpz")
public class SC_RW_TongYongPZModel extends StringMTEntity {
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    @Column(columnDefinition = "任务地址")
    private String renWuDZ;
    @Column(columnDefinition = "分类代码[SC0017]")
    private String fenLeiDM;
    @Column(columnDefinition = "分类名称")
    private String fenLeiMC;
    @Column(columnDefinition = "服务器IP")
    private String fuWuQIP;
}
