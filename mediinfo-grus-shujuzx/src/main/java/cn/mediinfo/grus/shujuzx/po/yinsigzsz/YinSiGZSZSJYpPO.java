package cn.mediinfo.grus.shujuzx.po.yinsigzsz;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import lombok.Data;

@Data
public class YinSiGZSZSJYpPO {
    private SC_ZD_YinSiGZSZModel yinSiGZSZModel;
    private SC_ZD_YinSiPZModel yinSiPZModel;

    public YinSiGZSZSJYpPO(SC_ZD_YinSiGZSZModel yinSiGZSZModel, SC_ZD_YinSiPZModel yinSiPZModel) {
        this.yinSiGZSZModel = yinSiGZSZModel;
        this.yinSiPZModel = yinSiPZModel;
    }
}
