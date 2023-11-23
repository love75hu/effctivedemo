package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import lombok.Data;

import java.util.List;

@Data
public class BiHuanGNDPZ {
    /**
     * 闭环功能点代码
     */
    private String biHuanGNDDM;
    /**
     * 入参字段
     */
    List<ZiDuanBMMC> ruCanList;
}