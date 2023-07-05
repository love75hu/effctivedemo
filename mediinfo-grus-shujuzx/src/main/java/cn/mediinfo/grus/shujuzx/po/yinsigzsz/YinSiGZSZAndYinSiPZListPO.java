package cn.mediinfo.grus.shujuzx.po.yinsigzsz;

import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiGZSZModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_YinSiPZModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class YinSiGZSZAndYinSiPZListPO {
    private SC_ZD_YinSiGZSZModel yinSiGZSZModel;
    private List<SC_ZD_YinSiPZModel> yinSiPZModels = new ArrayList<>();

    public YinSiGZSZAndYinSiPZListPO(SC_ZD_YinSiGZSZModel yinSiGZSZModel, List<SC_ZD_YinSiPZModel> yinSiPZModels) {
        this.yinSiGZSZModel = yinSiGZSZModel;
        this.yinSiPZModels = yinSiPZModels;
    }
}
