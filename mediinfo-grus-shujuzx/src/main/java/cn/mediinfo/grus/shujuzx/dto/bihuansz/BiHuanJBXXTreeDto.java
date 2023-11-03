package cn.mediinfo.grus.shujuzx.dto.bihuansz;

import lombok.Data;

import java.util.List;

@Data
public class BiHuanJBXXTreeDto {
    private String id;
    private String biHuanLXDM;
    private String biHuanLXMC;
    private String biHuanID;
    private String qiYongBZ;
    private String biHuanMC;
    private List<BiHuanJBXXTreeDto> children;
}
