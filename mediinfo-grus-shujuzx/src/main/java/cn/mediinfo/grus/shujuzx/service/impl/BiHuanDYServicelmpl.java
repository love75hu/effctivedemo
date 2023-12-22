package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.CollectionUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.JieDianGL.KeXuanZDDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZAddDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.SC_BH_DiaoYongPZUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.bihuandy.ShiTuMXBHPZDto;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.LingChuangJSPZDto;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShuJuJMXZDDto;
import cn.mediinfo.grus.shujuzx.model.SC_BH_DiaoYongPZModel;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.LingChuangJSPZZDXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_DiaoYongPZRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanDYService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class BiHuanDYServicelmpl implements BiHuanDYService {
    private final SC_BH_ShiTuXXRepository sc_bh_shiTuXXRepository;
    private final SC_BH_ShiTuMXRepository sc_bh_shiTuMXRepository;
    private final GongYongRemoteService _gongYongRemoteService;
    private final SC_BH_DiaoYongPZRepository sc_bh_diaoYongPZRepository;
    private final LyraIdentityService _lyraIdentityService;
    private final SequenceService _sequenceService;
    private final StringGenerator _stringGenerator;

    public BiHuanDYServicelmpl(
            SC_BH_ShiTuXXRepository scbhshiTuXXRepository,
            SC_BH_ShiTuMXRepository scbhshiTuMXRepository,
            GongYongRemoteService gongYongRemoteService,
            SC_BH_DiaoYongPZRepository scbhdiaoYongPZRepository,
            LyraIdentityService lyraIdentityService,
            SequenceService sequenceService,
            StringGenerator stringGenerator) {
        this.sc_bh_shiTuXXRepository = scbhshiTuXXRepository;
        this.sc_bh_shiTuMXRepository = scbhshiTuMXRepository;
        this._gongYongRemoteService = gongYongRemoteService;
        this.sc_bh_diaoYongPZRepository = scbhdiaoYongPZRepository;
        this._lyraIdentityService = lyraIdentityService;
        this._sequenceService = sequenceService;
        this._stringGenerator = stringGenerator;
    }

    @Override
    public List<ShiTuMXBHPZDto> getShuTuMXForBHPZ(String biHuanLXDM, String likeQuery) throws YuanChengException {
        List<SC_BH_ShiTuXXDto> shiTuXXs = sc_bh_shiTuXXRepository.getShiTuXXListByBiHuanLXDM(biHuanLXDM, likeQuery);
        if (shiTuXXs.isEmpty())
            return new ArrayList<>();
        List<String> shiTuXXIDs = shiTuXXs.stream().map(SC_BH_ShiTuXXDto::getShiTuID).toList();
        List<KeXuanZDDto> shiTuMXs = sc_bh_shiTuMXRepository.getShiTuMXList(biHuanLXDM, shiTuXXIDs);
        if (shiTuMXs.isEmpty())
            return new ArrayList<>();
        List<LingChuangJSPZDto> lingChuangJSPZDtos = new ArrayList<>();
        //构建远程服务入参
        for (SC_BH_ShiTuXXDto shiTuXX : shiTuXXs) {
            LingChuangJSPZDto lingChuangJSPZDto = new LingChuangJSPZDto();
            BeanUtil.mergeProperties(shiTuXX,lingChuangJSPZDto);
//            lingChuangJSPZDto.setShuJuLYID(shiTuXX.getShuJuLYID());
//            lingChuangJSPZDto.setShuJuLYLXDM(shiTuXX.getShuJuLYLXDM());
//            lingChuangJSPZDto.setShuJuLYID(shiTuXX.getShuJuLYID());
//            lingChuangJSPZDto.setShuJuLYLXDM(shiTuXX.getShuJuLYLXDM());
            List<ShuJuJMXZDDto> shuJuJMXZDs = shiTuMXs.stream().filter(x -> Objects.equals(x.getShiTuID(), shiTuXX.getShiTuID())).map(x -> {
                ShuJuJMXZDDto shuJuJMXZDDto = new ShuJuJMXZDDto();
                shuJuJMXZDDto.setShiTuID(shiTuXX.getShiTuID());
                shuJuJMXZDDto.setShiTuID(shiTuXX.getShiTuMC());
                shuJuJMXZDDto.setZiDuanBM(x.getZiDuanBM());
                shuJuJMXZDDto.setZiDuanMC(x.getZiDuanMC());
                return shuJuJMXZDDto;
            }).toList();
            if (!shuJuJMXZDs.isEmpty()) {
                //根据视图来源ID和视图来源类型代码组装数据
                var existShiTuXXDto = lingChuangJSPZDtos.stream().filter((x -> x.getShuJuLYID().equals(shiTuXX.getShuJuLYID()) && x.getShuJuLYLXDM().equals(shiTuXX.getShuJuLYLXDM()))).findFirst().orElse(null);
                if (existShiTuXXDto != null) {
                    var existShiTuMXDtos = existShiTuXXDto.getShuJuJMXZDDtos();
                    var quanBuShiTuMXs = Stream.concat(existShiTuMXDtos.stream(), shuJuJMXZDs.stream()).toList();
                    existShiTuXXDto.setShuJuJMXZDDtos(CollectionUtil.removeDuplicateObjects(quanBuShiTuMXs, ShuJuJMXZDDto::getZiDuanBM));
                } else {
                    lingChuangJSPZDto.setShuJuJMXZDDtos(shuJuJMXZDs);
                    lingChuangJSPZDtos.add(lingChuangJSPZDto);
                }
            }
        }
        if (lingChuangJSPZDtos.isEmpty())
            return new ArrayList<>();
        List<LingChuangJSPZZDXXRso> lingChuangJSPZZDList = _gongYongRemoteService.getlingChuangJSPZZDXX(lingChuangJSPZDtos).getData("远程调用接口getlingChuangJSPZZDXX错误！");
        if (ObjectUtils.isEmpty(lingChuangJSPZZDList)) {
            return new ArrayList<>();
        }
        List<ShiTuMXBHPZDto> results = new ArrayList<>();
        for (SC_BH_ShiTuXXDto shiTuXX : shiTuXXs) {
            var cunZaiJSPZ = lingChuangJSPZZDList.stream().filter(t -> t.getShuJuLYID().equals(shiTuXX.getShuJuLYID())).findFirst().orElse(null);
            //根据视图来源ID组装数据给前端
            if (cunZaiJSPZ != null) {
                var cunZaiZDBMs = shiTuMXs.stream().filter(x -> Objects.equals(x.getShiTuID(), shiTuXX.getShiTuID())).map(KeXuanZDDto::getZiDuanBM).toList();
                List<ShuJuJMXZDDto> gongGongZDMXs = cunZaiJSPZ.getShiTuMXZDDtos().stream().filter(x -> cunZaiZDBMs.contains(x.getZiDuanBM())).toList();
                if (!gongGongZDMXs.isEmpty()) {
                    for (ShuJuJMXZDDto gongGongZDMX : gongGongZDMXs) {
                        gongGongZDMX.setShiTuID(shiTuXX.getShiTuID());
                        gongGongZDMX.setShiTuMC(shiTuXX.getShiTuMC());
                    }
                    var cunZaiDto = results.stream().filter(x -> x.getShiTuID().equals(shiTuXX.getShiTuID())).findFirst().orElse(null);
                    if (cunZaiDto == null) {
                        ShiTuMXBHPZDto shiTuMXBHPZDto = new ShiTuMXBHPZDto();
                        shiTuMXBHPZDto.setShiTuID(shiTuXX.getShiTuID());
                        shiTuMXBHPZDto.setShiTuMC(shiTuXX.getShiTuMC());
                        shiTuMXBHPZDto.setZiDuanList(gongGongZDMXs);
                        results.add(shiTuMXBHPZDto);
                    } else {
                        var cunZaiZDMXs = cunZaiDto.getZiDuanList();
                        var quanBuZDMXs = Stream.concat(cunZaiZDMXs.stream(), gongGongZDMXs.stream()).toList();
                        cunZaiDto.setZiDuanList(CollectionUtil.removeDuplicateObjects(quanBuZDMXs, ShuJuJMXZDDto::getZiDuanBM));
                    }
                }
            }
        }
        return results;
    }

    @Override
    public List<SC_BH_DiaoYongPZDto> getBiHuanPZList(String gongNengDDM) {
        return sc_bh_diaoYongPZRepository.getBiHuanPZList(_lyraIdentityService.getJiGouID(), gongNengDDM,0);
    }

    @Override
    public SC_BH_DiaoYongPZDto getBiHuanPZXXByID(String id) {
        var entity = sc_bh_diaoYongPZRepository.findById(id).orElse(null);
        return MapUtils.copyProperties(entity, SC_BH_DiaoYongPZDto::new);
    }

    @Override
    public SC_BH_DiaoYongPZDto addBiHuanPZ(SC_BH_DiaoYongPZAddDto addDto) {
        var entity = MapUtils.copyProperties(addDto, SC_BH_DiaoYongPZModel::new);
        entity.setZuZhiJGID(_lyraIdentityService.getJiGouID());
        entity.setZuZhiJGMC(_lyraIdentityService.getJiGouMC());
        sc_bh_diaoYongPZRepository.insert(entity);
        return MapUtils.copyProperties(entity, SC_BH_DiaoYongPZDto::new);
    }

    @Override
    public SC_BH_DiaoYongPZDto updateBiHuanPZ(SC_BH_DiaoYongPZUpdateDto updateDto) throws WeiZhaoDSJException {
        //QSC_BH_DiaoYongPZModel diaoYongPZ = QSC_BH_DiaoYongPZModel.sC_BH_DiaoYongPZModel;
        var entity = sc_bh_diaoYongPZRepository.findById(updateDto.getId()).orElse(null);
        if (entity == null)
            throw new WeiZhaoDSJException("调用配置数据不存在!");
        MapUtils.mergeProperties(updateDto, entity);
        var result = sc_bh_diaoYongPZRepository.save(entity);
        return MapUtils.copyProperties(entity, SC_BH_DiaoYongPZDto::new);
    }

    @Override
    public SC_BH_DiaoYongPZDto updateBiHuanPZZT(String id, Integer qiYongBZ) throws WeiZhaoDSJException {
        var entity = sc_bh_diaoYongPZRepository.findById(id).orElse(null);
        if (entity == null)
            throw new WeiZhaoDSJException("调用配置数据不存在!");
        entity.setQiYongBZ(qiYongBZ);
        var result = sc_bh_diaoYongPZRepository.save(entity);
        return MapUtils.copyProperties(entity, SC_BH_DiaoYongPZDto::new);
    }

    @Override
    public Boolean zuoFeiBiHuanPZ(String id) throws WeiZhaoDSJException {
        var entity = sc_bh_diaoYongPZRepository.findById(id).orElse(null);
        if (entity == null)
            throw new WeiZhaoDSJException("调用配置数据不存在!");
        sc_bh_diaoYongPZRepository.deleteById(id);
        return  true;
    }
}
