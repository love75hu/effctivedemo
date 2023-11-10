package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddRuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.RuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_RuCanXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;

import java.util.List;

public interface RuCanXXService {
    /**
     * 根据ID获取闭环入参信息     *     * @return
     */
    SC_BH_RuCanXXDto getRuCanXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    Boolean addRuCanXX(List<AddRuCanXXDto> dto, String biHuanLXDM, String biHuanLXMC, String biHuanID, String biHuanMC);
    List<RuCanXXDto> getRuCanXXByBiHuanID(String biHuanID);
    List<SC_BH_RuCanXXModel> getRuCanXX(String biHuanID);
    public Boolean addFuZhiRCXX(List<SC_BH_RuCanXXModel> canXXModels);
}