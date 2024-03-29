package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.CanShuException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.JG_ZZ_JiGouXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.DiZuoRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 闭环信息服务
 */
@Service
class JiBenXXServiceImpl implements JiBenXXService {
    private final SC_BH_JiBenXXRepository jiBenXXRepository;
    private final SequenceService sequenceService;

    private final SC_BH_JieDianXXRepository jieDianXXRepository;

    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;
    private final StringGenerator stringGenerator;
    private final DiZuoRemoteService diZuoRemoteService;

    private final RuCanXXServiceImpl ruCanXXService;

    private final ShuJuYZYService shuJuYZYService;
    private final LyraIdentityService lyraIdentityService;


    public JiBenXXServiceImpl(SC_BH_JiBenXXRepository jiBenXXRepository, SequenceService sequenceService, SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository, StringGenerator stringGenerator, DiZuoRemoteService diZuoRemoteService, RuCanXXServiceImpl ruCanXXService, ShuJuYZYService shuJuYZYService, LyraIdentityService lyraIdentityService) {
        this.jiBenXXRepository = jiBenXXRepository;
        this.sequenceService = sequenceService;
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
        this.stringGenerator = stringGenerator;
        this.diZuoRemoteService = diZuoRemoteService;
        this.ruCanXXService = ruCanXXService;
        this.shuJuYZYService = shuJuYZYService;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据ID获取闭环信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JiBenXXDto getJIBENXXByID(String id) throws WeiZhaoDSJException {
        return BeanUtil.copyProperties(jiBenXXRepository.findById(id).orElseThrow(() -> new WeiZhaoDSJException("未获取到数据")), SC_BH_JiBenXXDto.class);
    }

    /**
     * 获取闭环信息树
     */
    @Override
    public List<BiHuanJBXXTreeDto> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery) {
        List<SC_ZD_ShuJuYZYDto> shuJuYZYList = shuJuYZYService.getShuJuYZYListByLBID("SC0013");//字典分类 闭环类型
        List<SC_BH_JiBenXXDto> jIBENXXList = jiBenXXRepository.getJIBENXXList(zuZhiJGID, likeQuery);
        List<BiHuanJBXXTreeDto> biHuanJBXXTreeDtos = new ArrayList<>();
        for (var a : shuJuYZYList) {
            BiHuanJBXXTreeDto biHuanJBXXTreeDto = new BiHuanJBXXTreeDto();
            biHuanJBXXTreeDto.setLabel(a.getZhiYuMC());
            biHuanJBXXTreeDto.setBiHuanID("");
            biHuanJBXXTreeDto.setBiHuanMC("");
            biHuanJBXXTreeDto.setId("");

            biHuanJBXXTreeDto.setChildren(
                    BeanUtil.copyListProperties(jIBENXXList.stream().filter(x -> x.getBiHuanLXDM().equals(a.getBiaoZhunDM())).collect(Collectors.toList()), BiHuanJBXXTreeDto::new, (p, s) -> {
                s.setLabel(s.getBiHuanMC());
            }));
            biHuanJBXXTreeDtos.add(biHuanJBXXTreeDto);
        }
        return biHuanJBXXTreeDtos;
    }

    @Override
    public String addBiHuanJBXX(AddBiHuanXXDto dto) throws WeiZhaoDSJException {
        if (StringUtil.hasText(dto.getId())) {
            SC_BH_JiBenXXModel jiBenXXModel = jiBenXXRepository.findById(dto.getId()).orElseThrow(() -> new WeiZhaoDSJException("未获取到数据"));
            ruCanXXService.addRuCanXX(dto.getRuCanXXDtoList(), dto.getZuZhiJGID(), dto.getZuZhiJGMC(), dto.getBiHuanLXDM(), dto.getBiHuanLXMC(), jiBenXXModel.getBiHuanID(), dto.getBiHuanMC());
            jiBenXXModel.setBiHuanMC(dto.getBiHuanMC());
            jiBenXXModel.setShunXuHao(Objects.isNull(dto.getShunXuHao())?jiBenXXRepository.getMaxShunXuHao(dto.getZuZhiJGID(),dto.getBiHuanLXDM()):dto.getShunXuHao());
            jiBenXXModel.setBiHuanLXDM(dto.getBiHuanLXDM());
            jiBenXXModel.setBiHuanLXMC(dto.getBiHuanLXMC());
            jiBenXXRepository.save(jiBenXXModel);
            return dto.getId();
        } else {
            var biHuanID = sequenceService.getXuHao("SC_BH_JiBenXX_BiHuanID", 6); //闭环id
            SC_BH_JiBenXXModel shiTuMXModel = new SC_BH_JiBenXXModel();
            BeanUtil.copyProperties(dto, shiTuMXModel);
            shiTuMXModel.setBiHuanID(biHuanID);
            shiTuMXModel.setShunXuHao(Objects.isNull(dto.getShunXuHao())?jiBenXXRepository.getMaxShunXuHao(dto.getZuZhiJGID(),dto.getBiHuanLXDM()):dto.getShunXuHao());
            shiTuMXModel.setZuZhiJGMC(dto.getZuZhiJGMC());
            shiTuMXModel.setZuZhiJGID(dto.getZuZhiJGID());
            ruCanXXService.addRuCanXX(dto.getRuCanXXDtoList(), dto.getZuZhiJGID(), dto.getZuZhiJGMC(), dto.getBiHuanLXDM(), dto.getBiHuanLXMC(), biHuanID, dto.getBiHuanMC());
            var data = jiBenXXRepository.save(shiTuMXModel);
            return data.getId();
        }
    }

    /**
     * 闭环设置下发
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean biHuanSZXF(BiHuanSZXFDto dto) throws YuanChengException, CanShuException {
        List<BiHuanZZJGDto> xiaFaJGIdGList = new ArrayList<>();
        List<SC_BH_JiBenXXModel> jiBenTYX = jiBenXXRepository.jiBenTYX();

        if ("0".equals(dto.getXiaFaFS())) {
            //全部下发 获取全部组织机构id
            List<JG_ZZ_JiGouXXRso> zuZhiJGResult = diZuoRemoteService.getJiGouXXAllList().getData("获取全部组织机构失败");
            xiaFaJGIdGList = BeanUtil.copyListProperties(zuZhiJGResult, BiHuanZZJGDto::new);
            if (CollectionUtils.isEmpty(dto.getBiHuanID())) {
                dto.setBiHuanID(jiBenTYX.stream().map(SC_BH_JiBenXXModel::getBiHuanID).toList());
            }
        }
        if ("1".equals(dto.getXiaFaFS())) {
            //部分下发
            xiaFaJGIdGList = dto.getXiaFaJG();
            if (CollectionUtils.isEmpty(dto.getBiHuanID())) {
                throw new CanShuException("部分下发闭环ID不能为空！");
            }
        }
        List<String> jiGouid = xiaFaJGIdGList.stream().map(BiHuanZZJGDto::getZuZhiJGID).collect(Collectors.toList());
        List<SC_BH_JiBenXXModel> jiBenJGX = jiBenXXRepository.jiBenJGX(jiGouid);

        //闭环设置基本信息
        List<SC_BH_JiBenXXModel> jiBenXXList = new ArrayList<>();



        //节点信息
        List<SC_BH_JieDianXXModel> jieDianXXList = jieDianXXRepository.jieDianXXList(dto.getBiHuanID());
        List<SC_BH_RuCanXXModel> ruCanXXModelList = ruCanXXService.getRuCanXXByBHID(dto.getBiHuanID());
        List<SC_BH_RuCanXXModel> addRuCanXX=new ArrayList<>();
        List<SC_BH_JieDianXXModel> addjieDianXXList = new ArrayList<>();
        //节点失效
        var jieDianSXList = jieDianSXRepository.jieDianSXList(dto.getBiHuanID());
        List<SC_BH_JieDianSXModel> addjieDianSXList = new ArrayList<>();
        //子闭环信息
        var ziBiHXX = ziBiHXXRepository.getZiBiHXXList(dto.getBiHuanID());
        List<SC_BH_ZiBiHXXModel> addziBiHXX = new ArrayList<>();
        var ziBiHXS = ziBiHXSLRepository.getZiBiHXXList(dto.getBiHuanID());
        List<SC_BH_ZiBiHXSLModel> addziBiHXSL = new ArrayList<>();
        for (var j : xiaFaJGIdGList) {

            //通用机构下有的，机构下没有的下发e
            List<String> biHuanID = jiBenJGX.stream()
                    .filter(n -> n.getZuZhiJGID().equals(j.getZuZhiJGID()))
                    .filter(n -> dto.getBiHuanID().contains(n.getBiHuanID()))
                    .map(SC_BH_JiBenXXModel::getBiHuanID).toList();

            jiBenTYX.stream().filter(n -> !biHuanID.contains(n.getBiHuanID()))
                    .filter(n -> dto.getBiHuanID().contains(n.getBiHuanID())).forEach(a -> {
                        var jiBenxx = BeanUtil.copyProperties(a, SC_BH_JiBenXXModel.class);
                        jiBenxx.setId(null);
                        var newbiHuanID = a.getBiHuanID(); //闭环id
                        String newbiHuanMC = a.getBiHuanMC();
                        jiBenxx.setZuZhiJGID(j.getZuZhiJGID());
                        jiBenxx.setZuZhiJGMC(j.getZuZhiJGMC());
                        jiBenxx.setBiHuanID(newbiHuanID);
                        jiBenxx.setBiHuanMC(newbiHuanMC);
                        jiBenXXList.add(jiBenxx);
                        jieDianXXList.stream().filter(n -> n.getBiHuanID().equals(a.getBiHuanID())).forEach(b -> {
                            b.setId(null);
                            b.setBiHuanID(newbiHuanID);
                            b.setBiHuanMC(newbiHuanMC);
                            b.setZuZhiJGID(j.getZuZhiJGID());
                            b.setZuZhiJGMC(j.getZuZhiJGMC());
                            addjieDianXXList.add(b);
                        });
                        ruCanXXModelList.stream().filter(n->n.getBiHuanID().equals(a.getBiHuanID())).forEach(b->{
                            b.setId(null);
                            b.setBiHuanID(newbiHuanID);
                            b.setBiHuanMC(newbiHuanMC);
                            b.setZuZhiJGID(j.getZuZhiJGID());
                            b.setZuZhiJGMC(j.getZuZhiJGMC());
                            addRuCanXX.add(b);
                        });
                        jieDianSXList.stream().filter(n -> n.getBiHuanID().equals(a.getBiHuanID())).forEach(b -> {
                            b.setId(null);
                            b.setBiHuanID(newbiHuanID);
                            b.setBiHuanMC(newbiHuanMC);
                            b.setZuZhiJGID(j.getZuZhiJGID());
                            b.setZuZhiJGMC(j.getZuZhiJGMC());
                            addjieDianSXList.add(b);
                        });
                        ziBiHXX.stream().filter(n -> n.getBiHuanID().equals(a.getBiHuanID())).forEach(b -> {
                            b.setId(null);
                            b.setBiHuanID(newbiHuanID);
                            b.setBiHuanMC(newbiHuanMC);
                            b.setZuZhiJGID(j.getZuZhiJGID());
                            b.setZuZhiJGMC(j.getZuZhiJGMC());
                            addziBiHXX.add(b);
                        });
                        ziBiHXS.stream().filter(n -> n.getBiHuanID().equals(a.getBiHuanID())).forEach(b -> {
                            b.setId(null);
                            b.setBiHuanID(newbiHuanID);
                            b.setBiHuanMC(newbiHuanMC);
                            b.setZuZhiJGID(j.getZuZhiJGID());
                            b.setZuZhiJGMC(j.getZuZhiJGMC());
                            addziBiHXSL.add(b);
                        });
                    });
        }

        jiBenXXRepository.insertAll(jiBenXXList);
        ruCanXXService.addFuZhiRCXX(addRuCanXX);
        jieDianXXRepository.insertAll(addjieDianXXList);
        jieDianSXRepository.insertAll(addjieDianSXList);
        ziBiHXXRepository.insertAll(addziBiHXX);
        ziBiHXSLRepository.insertAll(addziBiHXSL);
        return true;


    }

    /**
     * 闭环设置复制
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String biHuanSZFZ(String biHuanID, String zuZhiJGID, String zuZhiJGMC) {

        SC_BH_JiBenXXModel jiBenXXModel = jiBenXXRepository.findFirstByBiHuanIDAndZuZhiJGID(biHuanID, zuZhiJGID);
        String newbiHuanID = sequenceService.getXuHao("SC_BH_JiBenXX_BiHuanID", 6); //闭环id
        String newbiHuanMc = StringUtil.concat(jiBenXXModel.getBiHuanMC(), "(-副本)");
        jiBenXXModel.setId(null);
        jiBenXXModel.setZuZhiJGID(zuZhiJGID);
        jiBenXXModel.setZuZhiJGMC(zuZhiJGMC);
        jiBenXXModel.setBiHuanID(newbiHuanID);
        jiBenXXModel.setBiHuanMC(newbiHuanMc);
        jiBenXXRepository.save(jiBenXXModel);
        List<SC_BH_JieDianXXModel> jieDianXXList = jieDianXXRepository.findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(biHuanID, zuZhiJGID, zuZhiJGMC);
        List<SC_BH_JieDianXXModel> addjieDianXXList = new ArrayList<>();
        jieDianXXList.forEach(a -> {
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addjieDianXXList.add(a);
        });
        List<SC_BH_RuCanXXModel> ruCanXXList = ruCanXXService.getRuCanXX(biHuanID, zuZhiJGID);
        List<SC_BH_RuCanXXModel> ruCanXXModels = new ArrayList<>();
        ruCanXXList.forEach(r -> {
            r.setId(null);
            r.setBiHuanID(newbiHuanID);
            r.setBiHuanMC(newbiHuanMc);
            r.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            r.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            ruCanXXModels.add(r);
        });
        ruCanXXService.addFuZhiRCXX(ruCanXXModels);

        jieDianXXRepository.insertAll(addjieDianXXList);
        List<SC_BH_JieDianSXModel> jieDianSXList = jieDianSXRepository.findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(biHuanID, zuZhiJGID, zuZhiJGMC);
        List<SC_BH_JieDianSXModel> addjieDianSXList = new ArrayList<>();
        jieDianSXList.forEach(a -> {
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addjieDianSXList.add(a);
        });
        jieDianSXRepository.insertAll(addjieDianSXList);
        List<SC_BH_ZiBiHXXModel> ziBiHXXList = ziBiHXXRepository.findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(biHuanID, zuZhiJGID, zuZhiJGMC);
        List<SC_BH_ZiBiHXXModel> addziBiHXX = new ArrayList<>();
        ziBiHXXList.forEach(a -> {
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addziBiHXX.add(a);
        });
        ziBiHXXRepository.insertAll(addziBiHXX);
        List<SC_BH_ZiBiHXSLModel> ziBiHXSLList = ziBiHXSLRepository.findByBiHuanIDAndZuZhiJGIDAndZuZhiJGMC(biHuanID, zuZhiJGID, zuZhiJGMC);
        List<SC_BH_ZiBiHXSLModel> addziBiHXSL = new ArrayList<>();
        ziBiHXSLList.forEach(a -> {
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addziBiHXSL.add(a);
        });
        ziBiHXSLRepository.insertAll(addziBiHXSL);
        return newbiHuanID;
    }

    /**
     * 闭环设置删除
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean biHuanSZSC(String id) {
        SC_BH_JiBenXXModel jiBenXXModel = jiBenXXRepository.findById(id).orElse(null);
        if (jiBenXXModel != null) {
            String biHuanID = jiBenXXModel.getBiHuanID();
            String zuZhiJGID = jiBenXXModel.getZuZhiJGID();
            jiBenXXRepository.asDeleteDsl().where(n -> n.id.eq(id)).execute();
            ruCanXXService.delectRuCanXX(biHuanID,zuZhiJGID);
            jieDianXXRepository.asDeleteDsl().where(n -> n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID))).execute();
            jieDianSXRepository.asDeleteDsl().where(n -> n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID))).execute();
            ziBiHXXRepository.asDeleteDsl().where(n -> n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID))).execute();
            ziBiHXSLRepository.asDeleteDsl().where(n -> n.zuZhiJGID.eq(zuZhiJGID).and(n.biHuanID.eq(biHuanID))).execute();
        }
        return true;
    }

    /**
     * 闭环设置启用
     *
     * @param id       主键
     * @param qiyongBZ
     * @return
     */
    @Override
    public boolean biHuanSZQY(String id, Integer qiyongBZ) {
        jiBenXXRepository.biHuanSZQY(id, qiyongBZ);
        return true;
    }

    /**
     * 获取闭环信息
     */
    @Override
    public BiHuanXXDto getBiHuanXXBYID(String id) {
        var biHuanXX = BeanUtil.copyProperties(jiBenXXRepository.findById(id).orElse(null), BiHuanXXDto.class);
        //todo 判断空
        if (biHuanXX != null) {
            biHuanXX.setRuCanXXDtoList(ruCanXXService.getRuCanXXByBHID(biHuanXX.getBiHuanID(), biHuanXX.getZuZhiJGID()));
        }
        return biHuanXX;
    }

    /**
     * 获取闭环基本信息列表
     *
     * @param likeQuery
     * @return
     */
    @Override
    public List<SC_BH_JiBenXXDto> getBiHuanJBXXList(String likeQuery) {
        return jiBenXXRepository.getJiBenXXList(lyraIdentityService.getJiGouID(), likeQuery);
    }

}