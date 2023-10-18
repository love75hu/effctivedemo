package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.AddBiHuanSTJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTZDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.SaveBiHuanSTJDMXDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuMXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTMXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环视图明细服务
 */
@Service
public class BiHuanSTMXServiceImpl implements BiHuanSTMXService {
    private final SC_BH_ShiTuMXRepository shiTuMXRepository;

    private final LyraIdentityService lyraIdentityService;

    public BiHuanSTMXServiceImpl(SC_BH_ShiTuMXRepository shiTuMXRepository, LyraIdentityService lyraIdentityService) {
        this.shiTuMXRepository = shiTuMXRepository;
        this.lyraIdentityService = lyraIdentityService;
    }
    @Override
    public SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException {
        var result= shiTuMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环视图字段
     *
     * @return
     */
    @Override
    public List<BiHuanSTZDDto> getBiHuanSTZD(String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM, Integer pageIndex, Integer pageSize) {

        return shiTuMXRepository.getBiHuanSTZD(ziDuanMC,biHuanLXDM,chaXunLXDM,pageIndex,pageSize);
    }
    /**
     * 获取闭环视图字段数量
     *
     * @return
     */
    @Override
    public Integer getBiHuanSTZDCount(String ziDuanMC, String biHuanLXDM, Integer chaXunLXDM) {
        return shiTuMXRepository.getBiHuanSTZDCount(ziDuanMC,biHuanLXDM,chaXunLXDM);
    }
    @Override
    public Boolean delectBiHuanSTZDByID(String id)
    {
        shiTuMXRepository.deleteById(id);
        return true;
    }
    @Override
    public Boolean delectBiHuanSTZDByShiTuID(String shiTuID)
    {
        shiTuMXRepository.asDeleteDsl().where(n->n.shiTuID.eq(shiTuID)).execute();
        return true;
    }

    @Override
    public Boolean addBiHuanSTJDMX(List<AddBiHuanSTJDMXDto> dto) {
        List<SC_BH_ShiTuMXModel> shiTuMXList = shiTuMXRepository.asQuerydsl().where(n -> n.shiTuID.eq(dto.get(0).getShiTuID())).fetch();
        List<String> ziDuanBM = shiTuMXList.stream().map(SC_BH_ShiTuMXModel::getZiDuanBM).toList();
        List<SC_BH_ShiTuMXModel> scBhShiTuMXModels = BeanUtil.copyListProperties(dto.stream()
                .filter(s->!ziDuanBM.contains(s.getZiDuanBM())).collect(Collectors.toList()), SC_BH_ShiTuMXModel::new, (d, s) -> {
            s.setZuZhiJGID(lyraIdentityService.getJiGouID());
            s.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        });
        shiTuMXRepository.saveAll(scBhShiTuMXModels);
        return true;
    }

    @Override
    public Boolean saveBiHuanSTJDMX(SaveBiHuanSTJDMXDto dto) throws WeiZhaoDSJException {
        SC_BH_ShiTuMXModel scBhShiTuMXModel = shiTuMXRepository.findById(dto.getId()).orElse(null);
        AssertUtil.checkWeiZhaoDSJ(scBhShiTuMXModel != null, "未获取到数据");
        BeanUtil.copyProperties(dto,scBhShiTuMXModel);
        shiTuMXRepository.save(scBhShiTuMXModel);
        return true;
    }

    @Override
    public List<KeXuanZDDto> getShiTUZDXX(String shiTUID) {
        return shiTuMXRepository.getShiTUZDXX(shiTUID);
    }
}
