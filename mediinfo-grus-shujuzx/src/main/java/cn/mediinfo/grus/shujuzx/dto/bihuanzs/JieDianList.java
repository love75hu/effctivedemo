package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class JieDianList {
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
    /**
     * 时效异常标志
     * */
    private String shiXiaoYCBZ;
    /**
     * 时效异常描述
     * */
    private String shiXiaoYCMS;
    /**
     * 未执行标志
     * */
    private String weiZhiXBZ;

}
