package cn.mediinfo.grus.shujuzx.constant;

public enum ZhuSuoYCZLXEnum {
    HEBING("HeBing","主索引合并"),

    QUXIAOHB("QuXiaoHB","取消合并"),
    HULUE("HuLue","忽略"),
    XIUGAI("XiuGai","修改"),
    XINZENG("XinZeng","新增");
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
