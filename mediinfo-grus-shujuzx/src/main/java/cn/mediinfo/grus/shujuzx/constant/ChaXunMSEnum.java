package cn.mediinfo.grus.shujuzx.constant;

public enum ChaXunMSEnum {
    TongYongMS("1", "通用模式"),

    MenZhenJZ("2", "门诊接诊"),
    BingQuHZXQ("3", "病区患者详情");

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
