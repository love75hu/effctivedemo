package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class ShiTu {
    private String shiTuID;
    private String shiTuMC;
    private String shuJuJID;
    private String shuJuJMC;
    private String biaoMing;
    private String ziDuanBM;
    private String ziDuanMC;
    private String guanXiDM;
    private String guanXiMC;
    private List<ZiDuanZhi> ziDuanZhi;
}
