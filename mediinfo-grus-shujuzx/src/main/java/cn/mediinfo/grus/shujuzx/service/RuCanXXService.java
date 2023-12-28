package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.AddRuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.BiHuanSTRCZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.RuCanXXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_RuCanXXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_RuCanXXModel;

import java.util.List;

public interface RuCanXXService {
    Boolean addRuCanXX(List<AddRuCanXXDto> dto, String zuZhiJGID,String zuZhiJGMC, String biHuanLXDM, String biHuanLXMC, String biHuanID, String biHuanMC);
    List<RuCanXXDto> getRuCanXXByBHID(String biHuanID, String zuZhiJGID);
    List<SC_BH_RuCanXXModel> getRuCanXX(String biHuanID, String zuZhiJGID);
    List<BiHuanSTRCZDDto> getBiHuanSTRCZD(String biHuanID, String jiGouID);
    Boolean addFuZhiRCXX(List<SC_BH_RuCanXXModel> canXXModels);
    List<SC_BH_RuCanXXModel> getRuCanXXByBHID(List<String> biHuanID);
}