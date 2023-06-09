package cn.mediinfo.grus.shujuzx.constant;

public enum ChaXunMSEnum {
    TONG_YONG_MO_SHI("1", "通用模式"),
    MEN_ZHEN_JIE_ZHEN("2", "门诊接诊"),
    BING_QU_HUAN_ZHE_XIANG_QING("3", "病区患者详情");

    private final String value;
    private final String description;

    ChaXunMSEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
