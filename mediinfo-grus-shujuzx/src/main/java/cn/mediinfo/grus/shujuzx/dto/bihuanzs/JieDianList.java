package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JieDianList {

    private String id;

    private String jieDianMC;
    /**
     * 节点列表
     * */
    private List<jieDianNRList> jieDianNRList;
    /**
     * 子闭环标志
     * */
    private String ziBiHBZ;
    /**
     * 子闭环多次执行标志
     * */
    private String ziBiHDCZXBZ;

    /**
     * 子闭环关联字段
     * */
    private String ziBiHGLZD;

    /**
     * 子闭环关联字段值
     */
    private String ziBiHGLZDZ;


    /**
     * 子闭环显示列
     * */
    private List<ZiDuanBMMC> ziBiHXSLList;

    /**
     * 逆节点标志
     * */
    private Integer niJieDBZ;
    /**
     * 缺失标志
     * */
    private Integer queShiBZ;

    private List<ShiXiaoList> shiXiaoLists;

    /**
     * 未执行标志
     * */
    private Integer weiZhiXBZ;

    /**
     * 并行标志
     * */
    private Integer bingXingBZ;

    /**
     * 控制时间
     */
    private Date kongZhiSJ;
    /**
     * 必须标志
     */
    private Integer biXuBZ;

    private Integer xianShiBZ;

    private String ziBiHID;

}
