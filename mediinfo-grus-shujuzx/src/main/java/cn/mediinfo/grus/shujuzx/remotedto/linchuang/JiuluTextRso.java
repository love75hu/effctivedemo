package cn.mediinfo.grus.shujuzx.remotedto.linchuang;

import lombok.Data;

import java.util.Date;

@Data
public class JiuluTextRso {
    /**
     *
     */
    private String jiLuNR;
    /**
     * 病历名称
     */
    private String bingLiMC;
    /**
     * 病历时间
     */
    private String bingliSJ;
    /**
     * id
     */
    private String id;
    /**
     * 病历记录id
     */
    private String bingLiJLID;

    /**
     * 就诊id
     */
    private String jiuZhenID;

    /**
     * 就诊业务类型
     */
    private String jiuZhenYWLX;
}
