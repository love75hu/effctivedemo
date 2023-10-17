package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuXXByShiTuIDDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;

import java.util.List;
import java.util.Set;

public interface ShiTuXXService {
    /**
     * 根据ID获取临床检索视图信息     *     * @return
     */
    SC_CX_ShiTuXXDto getShiTuXXByID(String id) throws MsfResponseException, WeiZhaoDSJException;

    SC_CX_ShiTuXXDto getShiTuXXByShiTuID(String shiTuID) throws MsfResponseException, WeiZhaoDSJException;

    /**
     * 获取临床检索视图树
     */
    List<LinChuangJSSTDtoTree> getLinChuangJSSTTree(String fuLeiID, String likeQuery);

    /**
     * 新增视图分类
     */
    String addShiTuFL(ShiTuFLDto dto) throws WeiZhaoDSJException, TongYongYWException;

    /**
     * 获取视图分类列表
     */
    List<ShiTuFLDto> getShiTuFLList(String fuLeiID, String likeQuery);

    /**
     * 新增临床检索视图信息
     */
    String addShiTuXX(ShiTuXXDto dto);

    /**
     * 编辑视图信息
     */
    Boolean updateShiTuXX(SC_CX_ShiTuXXDto dto) throws TongYongYWException;

    /**
     * 根据主键获取视图分类
     */
    ShiTuFLDto getShiTuFLByID(String id) throws WeiZhaoDSJException;

    /**
     * 编辑视图分类
     */
    Boolean updateShiTuFL(ShiTuFLDto dto) throws TongYongYWException;


    /**
     * 作废视图分类
     */
    Boolean zuoFeiShiTuFL(String id) throws WeiZhaoDSJException;



    /**
     * 编辑视图字段
     */
    Boolean updateShiTuMX(UpdateShiTuMXDto updateShiTuMXDto) throws WeiZhaoDSJException;

    List<SC_CX_ShiTuXXByShiTuIDDto> getShiTuXXByIds(Set<String> ids);



}