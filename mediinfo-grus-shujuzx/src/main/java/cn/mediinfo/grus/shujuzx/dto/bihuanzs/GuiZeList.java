package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 规则list
 */
@Data
public class GuiZeList {
    @JsonProperty("guiZeList")
    private List<ShiTu> guiZeList;

    // getters and setters

    public static class ShiTu {
        @JsonProperty("shiTuID")
        private String shiTuID;
        @JsonProperty("shiTuMC")
        private String shiTuMC;
        @JsonProperty("shuJuJID")
        private String shuJuJID;
        @JsonProperty("shuJuJMC")
        private String shuJuJMC;
        @JsonProperty("biaoMing")
        private String biaoMing;
        @JsonProperty("ziDuanBM")
        private String ziDuanBM;
        @JsonProperty("ziDuanMC")
        private String ziDuanMC;
        @JsonProperty("guanXiDM")
        private String guanXiDM;
        @JsonProperty("guanXiMC")
        private String guanXiMC;
        @JsonProperty("ziDuanZhi")
        private List<ZiDuanZhi> ziDuanZhi;

        // getters and setters
    }

    public static class ZiDuanZhi {
        @JsonProperty("key")
        private String key;
        @JsonProperty("value")
        private String value;

        // getters and setters
    }
}
