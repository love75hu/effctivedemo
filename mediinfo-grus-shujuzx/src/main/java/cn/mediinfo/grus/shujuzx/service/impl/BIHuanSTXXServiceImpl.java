package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXDto;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.BiHuanSTXXTree;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDGXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BIHuanSTXXService;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环视图信息服务
 */
@Service
public class BIHuanSTXXServiceImpl implements BIHuanSTXXService {
    private final StringGenerator stringGenerator;
    private final SC_BH_ShiTuXXRepository shiTuXXRepository;
    private final LyraIdentityService lyraIdentityService;
    private final  BiHuanSTMXServiceImpl biHuanSTMXService;
    private final SC_BH_ShiTuJDXXRepository biHuanSTJDXXRepository;
    private final SC_BH_ShiTuJDMXRepository biHuanSTJDMXRepository;
    private final SC_BH_ShiTuJDGXRepository biHuanSTJDGXRepository;
    private final ShuJuYZYService shuJuYZYService;
    private final SequenceService sequenceService;

    public BIHuanSTXXServiceImpl(StringGenerator stringGenerator, SC_BH_ShiTuXXRepository shiTuXXRepository, LyraIdentityService lyraIdentityService, BiHuanSTMXServiceImpl biHuanSTMXService,  SC_BH_ShiTuJDXXRepository biHuanSTJDXXRepository, SC_BH_ShiTuJDMXRepository biHuanSTJDMXRepository, SC_BH_ShiTuJDGXRepository biHuanSTJDGXRepository, ShuJuYZYService shuJuYZYService, SequenceService sequenceService) {
        this.stringGenerator = stringGenerator;
        this.shiTuXXRepository = shiTuXXRepository;
        this.lyraIdentityService = lyraIdentityService;
        this.biHuanSTMXService = biHuanSTMXService;
        this.biHuanSTJDXXRepository = biHuanSTJDXXRepository;
        this.biHuanSTJDMXRepository = biHuanSTJDMXRepository;
        this.biHuanSTJDGXRepository = biHuanSTJDGXRepository;
        this.shuJuYZYService = shuJuYZYService;
        this.sequenceService = sequenceService;
    }

    /**
     * 根据ID获取闭环视图信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_ShiTuXXDto getShiTuXXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环视图信息树
     */
    @Override
    public List<BiHuanSTXXTree> getBiHuanSTXXTree(String likeQuery) {

        List<SC_ZD_ShuJuYZYDto> shuJuYZYList = shuJuYZYService.getShuJuYZYListByLBID("SC0013");

        List<SC_BH_ShiTuXXDto> shiTuXXList = shiTuXXRepository.getShiTuXXList(likeQuery);
        List<BiHuanSTXXTree> biHuanSTXXTrees = new ArrayList<>();
        for (var s:shuJuYZYList)
        {
            BiHuanSTXXTree biHuanSTXXTree=new BiHuanSTXXTree();
            biHuanSTXXTree.setBiHuanLXDM(s.getBiaoZhunDM());
            biHuanSTXXTree.setBiHuanLXMC(s.getZhiYuMC());
            biHuanSTXXTree.setChildren(BeanUtil.copyListProperties(shiTuXXList.stream()
                    .filter(x->x.getBiHuanLXDM().equals(s.getBiaoZhunDM())).collect(Collectors.toList()),BiHuanSTXXTree::new));
           biHuanSTXXTrees.add(biHuanSTXXTree);
        }
        return biHuanSTXXTrees;
    }

    /**
     * 添加闭环视图信息
     */
    @Override
    public String addBiHuanSTXX(BiHuanSTXXDto dto) {
        SC_BH_ShiTuXXModel scBhShiTuXXModel = BeanUtil.copyProperties(dto, SC_BH_ShiTuXXModel::new);
        scBhShiTuXXModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        scBhShiTuXXModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        scBhShiTuXXModel.setShiTuID(sequenceService.getXuHao("SC_BH_ShiTuXX_ShiTuID", 6));
       scBhShiTuXXModel.setShunXuHao(dto.getShunXuHAO()==null?shiTuXXRepository.getMaxShunXuHao()+1:dto.getShunXuHAO());
       var model= shiTuXXRepository.save(scBhShiTuXXModel);
        return model.getId();
    }

    @Override
    public Boolean updateBiHuanSTXX(BiHuanSTXXDto dto) throws WeiZhaoDSJException {
        var scCxShiTuXXModel = shiTuXXRepository.findById(dto.getId()).orElse(null);
        if (scCxShiTuXXModel==null)
        {
            throw new WeiZhaoDSJException("未获取到数据");
        }
        BeanUtil.copyProperties(dto,scCxShiTuXXModel);
        shiTuXXRepository.save(scCxShiTuXXModel);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean zuoFeiBHSTXX(String id) throws WeiZhaoDSJException {
        var  shiTuxx = shiTuXXRepository.findById(id).orElse(null);
        if (shiTuxx==null)
        {
            throw new WeiZhaoDSJException("未获取到数据");
        }
        shiTuXXRepository.asDeleteDsl().where(n->n.id.eq(id)).execute();
        biHuanSTMXService.delectBiHuanSTZDByShiTuID(shiTuxx.getShiTuID());
        biHuanSTJDXXRepository.asDeleteDsl().where(x->x.shiTuID.eq(shiTuxx.getShiTuID())).execute();
        biHuanSTJDMXRepository.asDeleteDsl().where(x->x.shiTuID.eq(shiTuxx.getShiTuID())).execute();
        biHuanSTJDGXRepository.asDeleteDsl().where(x->x.shiTuID.eq(shiTuxx.getShiTuID())).execute();
        return true;
    }


}