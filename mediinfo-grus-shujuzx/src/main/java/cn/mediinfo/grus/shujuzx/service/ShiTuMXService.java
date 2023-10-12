package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.shitumx.DatabaseDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.AddShiTuMXDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuMXListDto;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;

import java.util.List;
import java.util.Set;

public interface ShiTuMXService {

    /**
     * 获取视图配置的字段信息
     * @param shiTuMXIds 视图明细id
     * @return 字段信息
     */
     List<FieldDTO> listFields(Set<String> shiTuMXIds) throws YuanChengException;

    /**
     * 获取视图配置的表信息
     * @param shiTuMXIds 视图明细id
     * @return 表信息
     */
     List<TableDTO> listTable(Set<String> shiTuMXIds);

    /**
     * 获取视图配置的数据库配置信息
     * @param shiTuMXIds 视图明细id
     * @return 表信息
     */
     List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds);
    /**
     * 批量新增视图字段
     *
     * @return
     */
    Boolean addShiTuMX(AddShiTuMXDto addShiTuMXDto) throws WeiZhaoDSJException, MsfResponseException;

    /**
     * 获取视图字段列表
     *
     * @return
     */
    List<ShiTuMXListDto> getShiTuMXList(String shiTuSTID, String likeQuery  ,Integer chaXunLX ,Integer pageIndex  ,Integer pageSize);

    /**
     * 获取视图字段列表数量
     *
     * @return
     */
    Integer getShiTuMXCount(String shiTuSTID, String likeQuery  ,Integer chaXunLX);

    /**
     * 获取关联条件字段
     * @param shiTuSTID
     * @return
     */
     List<GuanLianTJZD> getGuanLianTJZD(String shiTuSTID);

     /**
      * 获取关联条件字段
      * @param shiTuSTID
      * @return
      */
     List<GuanLianTJZD> getGuanLianTJList(String shiTuSTID);

     /**
      * 更新视图明细
      */
    Boolean updateShiTuMX(String shiTuSTID,String shiTuMC, List<GuanLianTJZD> guanLianTJZDS);

    Boolean zuoFeiShiTMX(String id) throws WeiZhaoDSJException;


}
