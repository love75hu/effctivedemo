package cn.mediinfo.grus.shujuzx.dto.bihuanlcs;

import lombok.Data;

@Data
public class JiuluTextDto {
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

}
