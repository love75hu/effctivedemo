package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import lombok.Data;

import java.util.List;

@Data
public class BiHuanSZXFDto {
    /**
     * 下发方式 0 全部下发 1 部分下发
     */
    private String xiaFaFS;
    /**
     * 下发机构
     */
    private List<BiHuanZZJGDto> xiaFaJG;
    /**
     * 下发项目
     */
    private List<String> biHuanID;

}
