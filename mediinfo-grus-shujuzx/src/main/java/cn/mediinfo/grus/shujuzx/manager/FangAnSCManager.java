package cn.mediinfo.grus.shujuzx.manager;

import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnSCModel;

import java.util.List;
import java.util.Set;

public interface FangAnSCManager {
    List<SC_CX_FangAnSCModel> listByIds(Set<String> fangAnSCIds);
}
