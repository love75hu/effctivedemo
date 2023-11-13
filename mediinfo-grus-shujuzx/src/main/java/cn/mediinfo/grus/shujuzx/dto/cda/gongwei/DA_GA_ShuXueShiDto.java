package cn.mediinfo.grus.shujuzx.dto.cda.gongwei;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class DA_GA_ShuXueShiDto {
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
     * 健康档案ID
     */
    @Column(columnDefinition = "健康档案ID")
    private String jianKangDAID;

    /**
     * 输血原因
     */
    @Column(columnDefinition = "输血原因")
    private String shuXueYY;

    /**
     * 输血日期
     */
    @Column(columnDefinition = "输血日期")
    private Date shuXueRQ;

}

