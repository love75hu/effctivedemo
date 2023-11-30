package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface JieDianXXService {
    /**
     * 根据ID获取闭环节点信息     *     * @return
     */
    SC_BH_JieDianXXDto getJieDianXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    String addBiHuanSZXX(AddBiHuanSZXXDto dto);

    List<BiHuanSZXXDto>  getBiHuanSZXXBybiHuanID( String biHuanID,String jiGouID);

    List<JieDianSXXXDto>  getJieDianSXXX(String biHuanID,String jieDianID,String jiGouID);

    BiHuanSZXXDto getBiHuanJDNRXX(String jieDianXXID) throws WeiZhaoDSJException;

    Boolean zuoFeiBiHuanJDXX(String jieDianXXID) throws WeiZhaoDSJException;

    Boolean jieDianYC(String jieDianXXID,String yinCangBZ);

}