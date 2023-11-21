package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class BiHuanRCXXDto {
    /**
     * 闭环ID
     * */
    private String biHuanID;
    /**
     * 闭环名称
     * */
    private String biHuanMC;
    /**
     * 入参List
     */
    private List<ZiDuanBMMC> ruCanList;
}
