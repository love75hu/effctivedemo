package cn.mediinfo.grus.shujuzx.constant;

public enum ShuJuLYDMEnum {

    SHU_JU_JI("1", "数据集"),
    SHU_JU_SHI_TU("2", "数据视图");

    private final String value;
    private final String description;

    ShuJuLYDMEnum(String value, String description) {
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
