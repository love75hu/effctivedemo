package cn.mediinfo.grus.shujuzx.constant;

public enum JiuZhenLYEnum {
    MenZhen("1", "门诊"),
    JiZhen("2", "急诊"),
    ZhuYuan("3", "住院"),
    GongWei("4","公卫");

    private final String value;
    private final String description;

    JiuZhenLYEnum(String value, String description) {
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
