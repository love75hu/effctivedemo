package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JieDianList {

    private String id;
    /**
     * 节点列表
     * */
    private List<jieDianNRList> jieDianList;
    /**
     * 子闭环标志
     * */
    private String ziBiHBZ;
    /**
     * 子闭环多次执行标志
     * */
    private String ziBiHDCZXBZ;

    /**
     * 子闭环显示列
     * */
    private List<ZiDuanBMMC> ziBiHXSLList;

    /**
     * 子闭环信息
     * */
    private JieDianList jieDianXX;
    /**
     * 逆节点标志
     * */
    private String niJieDBZ;
    /**
     * 缺失标志
     * */
    private String queShiBZ;

    private List<ShiXiaoList> shiXiaoLists;

    /**
     * 未执行标志
     * */
    private String weiZhiXBZ;

    private Integer bingXingBZ;

    private Date kongZhiSJ;

}
