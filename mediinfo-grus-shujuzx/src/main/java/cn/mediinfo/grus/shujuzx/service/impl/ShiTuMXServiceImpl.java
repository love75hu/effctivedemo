package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.shitumx.DatabaseDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.FieldDTO;
import cn.mediinfo.grus.shujuzx.dto.shitumx.TableDTO;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.GuanLianTJZD;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.SC_CX_ShiTuMXDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.AddShiTuMXDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.ShiTuMXListDto;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXGXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuXXService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 临床检索视图明细服务
 */
@Service
public class ShiTuMXServiceImpl implements ShiTuMXService {
    private final SC_CX_ShiTuMXRepository shiTuMXRepository;

    private final ShiTuMXGXService shiTuMXGXService;

    public ShiTuMXServiceImpl(SC_CX_ShiTuMXRepository shiTuMXRepository, ShiTuMXGXService shiTuMXGXService) {
        this.shiTuMXRepository = shiTuMXRepository;
        this.shiTuMXGXService = shiTuMXGXService;
    }

    /**
     * 根据ID获取临床检索视图明细     *     * @param id     * @return
     */
    public SC_CX_ShiTuMXDto getShiTuMXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_CX_ShiTuMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    @Override
    public List<FieldDTO> listFields(Set<String> shiTuMXIds) {
        return null;
    }

    @Override
    public List<TableDTO> listTable(Set<String> shiTuMXIds) {
        return null;
    }

    @Override
    public List<DatabaseDTO> listDatabase(Set<String> shiTuMXIds) {
        return null;
    }

    /**
     * 批量新增视图字段
     *
     */
    @Override
    public Boolean addShiTuMX(AddShiTuMXDto addShiTuMXDto) throws WeiZhaoDSJException, MsfResponseException {
        List<SC_CX_ShiTuMXModel> shiTuMXModels=new ArrayList<>();
        addShiTuMXDto.getZiDuanXXList().forEach(s->{
            SC_CX_ShiTuMXModel shiTuMXModel=new SC_CX_ShiTuMXModel();
            shiTuMXModel.setShiTuID(addShiTuMXDto.getShiTuID());
            shiTuMXModel.setShiTuMC(addShiTuMXDto.getShiTuMC());
            BeanUtil.copyProperties(s,shiTuMXModel);
            shiTuMXModels.add(shiTuMXModel);
        });
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 根据视图id获取视图字段信息列表
     *
     */
    @Override
    public List<ShiTuMXListDto> getShiTuMXList(String shiTuSTID, String likeQuery, Integer chaXunLX, Integer pageIndex, Integer pageSize) {
        return shiTuMXRepository.getShiTuMXList(shiTuSTID,likeQuery,chaXunLX,pageIndex,pageSize);
    }

    /**
     * 获取视图字段列表数量
     *
     */
    @Override
    public Integer getShiTuMXCount(String shiTuSTID, String likeQuery, Integer chaXunLX) {
        return shiTuMXRepository.getShiTuMXCount(shiTuSTID,likeQuery,chaXunLX);
    }

    /**
     * 获取关联条件字段
     *
     */
    @Override
    public List<GuanLianTJZD> getGuanLianTJZD(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJZD(shiTuSTID);
    }

    @Override
    public  List<GuanLianTJZD> getGuanLianTJList(String shiTuSTID) {
        return shiTuMXRepository.getGuanLianTJList(shiTuSTID);
    }

    @Override
    public Boolean updateShiTuMX(String shiTuSTID,String shiTuMC, List<GuanLianTJZD> guanLianTJZDS) {
        shiTuMXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuSTID)).execute();
        List<SC_CX_ShiTuMXModel> shiTuMXModels=new ArrayList<>();
        guanLianTJZDS.forEach(s->{
            SC_CX_ShiTuMXModel shiTuMXModel=new SC_CX_ShiTuMXModel();
            shiTuMXModel.setShiTuID(shiTuSTID);
            shiTuMXModel.setShiTuMC(shiTuMC);
            BeanUtil.copyProperties(s,shiTuMXModel);
            shiTuMXModels.add(shiTuMXModel);
        });
        shiTuMXRepository.saveAll(shiTuMXModels);
        return true;
    }

    /**
     * 作废视图字段
     *
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean zuoFeiShiTMX(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuMXDto shiTuMXByID = getShiTuMXByID(id);
        shiTuMXGXService.delectShiTuMXGX(shiTuMXByID.getShiTuID());
        shiTuMXRepository.asDeleteDsl().where(n->n.id.eq(id)).execute();
        return true;

    }


}