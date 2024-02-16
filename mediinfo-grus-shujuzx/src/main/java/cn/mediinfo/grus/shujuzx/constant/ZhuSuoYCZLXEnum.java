package cn.mediinfo.grus.shujuzx.constant;

public enum ZhuSuoYCZLXEnum {
    HEBING("1","主索引合并"),

    QUXIAOHB("2","取消合并"),
    HULUE("3","忽略"),
    XIUGAI("4","修改"),
    XINZENG("5","新增");
    private final String value;
    private final String description;

    ZhuSuoYCZLXEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }
    public String getValue(){
        return value;
    }
    public String getDescription(){
        return  description;
    }
}
