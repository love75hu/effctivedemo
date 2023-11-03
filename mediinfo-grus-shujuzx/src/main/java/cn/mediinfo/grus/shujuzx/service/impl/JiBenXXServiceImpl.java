package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.JG_ZZ_JiGouXXRso;
import cn.mediinfo.grus.shujuzx.remoteservice.DiZuoRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环信息服务
 */
@Service
class JiBenXXServiceImpl implements JiBenXXService {
    private final SC_BH_JIBENXXRepository jIBENXXRepository;
    private final LyraIdentityService lyraIdentityService;

    private final SC_BH_JieDianXXRepository jieDianXXRepository;

    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;
    private final StringGenerator stringGenerator;
    private final DiZuoRemoteService diZuoRemoteService;

    private final RuCanXXServiceImpl ruCanXXService;


    public JiBenXXServiceImpl(SC_BH_JIBENXXRepository jIBENXXRepository, LyraIdentityService lyraIdentityService, SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository, StringGenerator stringGenerator, DiZuoRemoteService diZuoRemoteService, RuCanXXServiceImpl ruCanXXService) {
        this.jIBENXXRepository = jIBENXXRepository;
        this.lyraIdentityService = lyraIdentityService;
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
        this.stringGenerator = stringGenerator;
        this.diZuoRemoteService = diZuoRemoteService;
        this.ruCanXXService = ruCanXXService;
    }

    /**
     * 根据ID获取闭环信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JIBENXXDto getJIBENXXByID(String id) throws WeiZhaoDSJException {
        var result = jIBENXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JIBENXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取闭环信息树
     */
    @Override
    public List<BiHuanJBXXTreeDto> getBiHuanJBXXTree(String zuZhiJGID, String likeQuery) {
        List<SC_BH_JIBENXXDto> jIBENXXList = jIBENXXRepository.getJIBENXXList(zuZhiJGID,likeQuery);
        List<String> biHuanLXDMs = jIBENXXList.stream().map(x -> x.getBiHuanLXDM()).distinct().collect(Collectors.toList());
        List<BiHuanJBXXTreeDto> biHuanJBXXTreeDtos=new ArrayList<>();
         for(var a:biHuanLXDMs)
         {
             SC_BH_JIBENXXDto scBhJibenxxDto = jIBENXXList.stream().filter(x -> x.getBiHuanLXDM().equals(a)).findFirst().orElse(new SC_BH_JIBENXXDto());
             BiHuanJBXXTreeDto biHuanJBXXTreeDto = BeanUtil.copyProperties(scBhJibenxxDto, BiHuanJBXXTreeDto::new);
             biHuanJBXXTreeDto.setChildren(BeanUtil.copyListProperties(jIBENXXList.stream().filter(x -> x.getBiHuanLXDM().equals(a)).collect(Collectors.toList()),BiHuanJBXXTreeDto::new));
             biHuanJBXXTreeDtos.add(biHuanJBXXTreeDto);
         }
        return biHuanJBXXTreeDtos;
    }

    @Override
    public Boolean addBiHuanJBXX(AddBiHuanXXDto dto) {

        SC_BH_JiBenXXModel shiTuMXModel=new SC_BH_JiBenXXModel();
        BeanUtil.copyProperties(dto,shiTuMXModel);
        shiTuMXModel.setZuZhiJGMC("0");
        shiTuMXModel.setZuZhiJGID("通用");
       // shiTuMXModel.setShiTuID(stringGenerator.Create());
        ruCanXXService.addRuCanXX(dto.getRuCanXXDtoList(),dto.getBiHuanLXDM(),dto.getBiHuanLXMC(),"","");
        jIBENXXRepository.save(shiTuMXModel);
        return true;
    }
    /**
     * 闭环设置下发
     */
    @Override
    public Boolean biHuanSZXF(BiHuanSZXFDto dto) throws TongYongYWException, YuanChengException {
        List<BiHuanZZJGDto> xiaFaJGIdGList = new ArrayList<>();

        if ("0".equals(dto.getXiaFaFS()))
        {
            //全部下发 获取全部组织机构id
            List<JG_ZZ_JiGouXXRso> zuZhiJGResult = diZuoRemoteService.getJiGouXXAllList().getData("获取全部组织机构失败");

            xiaFaJGIdGList=BeanUtil.copyListProperties(zuZhiJGResult,BiHuanZZJGDto::new);
        }
        if ("1".equals(dto.getXiaFaFS()))
        {
            //部分下发
            xiaFaJGIdGList=dto.getXiaFaJG();
        }

        List<String> jiGouid=xiaFaJGIdGList.stream().map(BiHuanZZJGDto::getZuZhiJGID).collect(Collectors.toList());

            List<SC_BH_JiBenXXModel> jiBenTYX = jIBENXXRepository.jiBenTYX();
            List<SC_BH_JiBenXXModel> jiBenJGX = jIBENXXRepository.jiBenJGX(jiGouid);
            //闭环设置基本信息
            List<SC_BH_JiBenXXModel> jiBenXXList=new ArrayList<>();

            //节点信息
            List<SC_BH_JieDianXXModel> jieDianXXList = jieDianXXRepository.jieDianXXList(dto.getBiHuanID());
            List<SC_BH_JieDianXXModel> addjieDianXXList=new ArrayList<>();
            //节点失效
            var jieDianSXList=jieDianSXRepository.jieDianSXList(dto.getBiHuanID());
            List<SC_BH_JieDianSXModel> addjieDianSXList=new ArrayList<>();
            //子闭环信息
            var ziBiHXX=ziBiHXXRepository.getZiBiHXXList(dto.getBiHuanID());
            List<SC_BH_ZiBiHXXModel> addziBiHXX=new ArrayList<>();
            var ziBiHXS=  ziBiHXSLRepository.getZiBiHXXList(dto.getBiHuanID());
            List<SC_BH_ZiBiHXSLModel> addziBiHXSL=new ArrayList<>();
            for (var j:xiaFaJGIdGList)
            {
                //通用机构下有的，机构下没有的下发e
                List<String> biHuanID = jiBenJGX.stream().filter(n -> n.getZuZhiJGID().equals(j.getZuZhiJGID())).map(SC_BH_JiBenXXModel::getBiHuanID).toList();

                jiBenTYX.stream().filter(n->!biHuanID.contains(n.getBiHuanID())).forEach(a->{
                     var jiBenxx=BeanUtil.copyProperties(a,SC_BH_JiBenXXModel.class);
                    jiBenxx.setId(null);
                    String newbiHuanID=stringGenerator.Create();
                    String newbiHuanMC=a.getBiHuanMC();
                    jiBenxx.setZuZhiJGID(j.getZuZhiJGID());
                    jiBenxx.setZuZhiJGMC(j.getZuZhiJGMC());
                    jiBenxx.setBiHuanID(newbiHuanID);
                    jiBenxx.setBiHuanMC(newbiHuanMC);
                    jiBenXXList.add(jiBenxx);
                    jieDianXXList.stream().filter(n->n.getBiHuanID().equals(a.getBiHuanID())).forEach(b->{
                        b.setId(null);
                        b.setBiHuanID(newbiHuanID);
                        b.setBiHuanMC(newbiHuanMC);
                        b.setZuZhiJGID(j.getZuZhiJGID());
                        b.setZuZhiJGMC(j.getZuZhiJGMC());
                        addjieDianXXList.add(b);
                    });
                    jieDianSXList.stream().filter(n->n.getBiHuanID().equals(a.getBiHuanID())).forEach(b->{
                        b.setId(null);
                        b.setBiHuanID(newbiHuanID);
                        b.setBiHuanMC(newbiHuanMC);
                        b.setZuZhiJGID(j.getZuZhiJGID());
                        b.setZuZhiJGMC(j.getZuZhiJGMC());
                        addjieDianSXList.add(b);
                    });
                    ziBiHXX.stream().filter(n->n.getBiHuanID().equals(a.getBiHuanID())).forEach(b->{
                        b.setId(null);
                        b.setBiHuanID(newbiHuanID);
                        b.setBiHuanMC(newbiHuanMC);
                        b.setZuZhiJGID(j.getZuZhiJGID());
                        b.setZuZhiJGMC(j.getZuZhiJGMC());
                        addziBiHXX.add(b);
                    });
                    ziBiHXS.stream().filter(n->n.getBiHuanID().equals(a.getBiHuanID())).forEach(b->{
                        b.setId(null);
                        b.setBiHuanID(newbiHuanID);
                        b.setBiHuanMC(newbiHuanMC);
                        b.setZuZhiJGID(j.getZuZhiJGID());
                        b.setZuZhiJGMC(j.getZuZhiJGMC());
                        addziBiHXSL.add(b);
                    });
                });
            }

            jIBENXXRepository.insertAll(jiBenXXList);
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
    public String biHuanSZFZ(String biHuanID, String zuZhiJGID, String zuZhiJGMC) {
        SC_BH_JiBenXXModel jiBenXXModel = jIBENXXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .where(n->n.zuZhiJGMC.eq(zuZhiJGMC)).fetchFirst();
        String newbiHuanID= stringGenerator.Create();
        String newbiHuanMc =StringUtil.concat (jiBenXXModel.getBiHuanMC(),"(-副本)");
        jiBenXXModel.setId(null);
        jiBenXXModel.setZuZhiJGID(zuZhiJGID);
        jiBenXXModel.setZuZhiJGMC(zuZhiJGMC);
        jiBenXXModel.setBiHuanID(newbiHuanID);
        jiBenXXModel.setBiHuanMC(newbiHuanMc);
        jIBENXXRepository.save(jiBenXXModel);
        List<SC_BH_JieDianXXModel> jieDianXXList = jieDianXXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .where(n->n.zuZhiJGMC.eq(zuZhiJGMC)).fetch();
List<SC_BH_JieDianXXModel> addjieDianXXList=new ArrayList<>();
        jieDianXXList.forEach(a->{
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addjieDianXXList.add(a);
        });
        jieDianXXRepository.insertAll(addjieDianXXList);
        List<SC_BH_JieDianSXModel> jieDianSXList = jieDianSXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .where(n->n.zuZhiJGMC.eq(zuZhiJGMC)).fetch();
        List<SC_BH_JieDianSXModel> addjieDianSXList=new ArrayList<>();
        jieDianSXList.forEach(a->{
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addjieDianSXList.add(a);
        });
        jieDianSXRepository.insertAll(addjieDianSXList);
        List<SC_BH_ZiBiHXXModel> ziBiHXXList = ziBiHXXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .where(n->n.zuZhiJGMC.eq(zuZhiJGMC)).fetch();
        List<SC_BH_ZiBiHXXModel> addziBiHXX=new ArrayList<>();
        ziBiHXXList.forEach(a->{
            a.setId(null);
            a.setBiHuanID(newbiHuanID);
            a.setBiHuanMC(newbiHuanMc);
            a.setZuZhiJGID(jiBenXXModel.getZuZhiJGID());
            a.setZuZhiJGMC(jiBenXXModel.getZuZhiJGMC());
            addziBiHXX.add(a);
        });
        ziBiHXXRepository.insertAll(addziBiHXX);
        List<SC_BH_ZiBiHXSLModel> ziBiHXSLList = ziBiHXSLRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .where(n->n.zuZhiJGID.eq(zuZhiJGID))
                .where(n->n.zuZhiJGMC.eq(zuZhiJGMC)).fetch();
        List<SC_BH_ZiBiHXSLModel> addziBiHXSL=new ArrayList<>();
        ziBiHXSLList.forEach(a->{
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
     */
    @Override
    public Boolean biHuanSZSC(String biHuanID) {
        jIBENXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        jieDianXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        jieDianSXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        ziBiHXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        ziBiHXSLRepository.asDeleteDsl().where(n->n.biHuanID.eq(biHuanID)).execute();
        return true;
    }
    /**
     * 闭环设置启用
     */
    @Override
    public Boolean biHuanSZQY(String biHuanID,Integer qiyongBZ) {
        jIBENXXRepository.asUpdateDsl().where(n->n.biHuanID.eq(biHuanID)).set(n->n.qiYongBZ,qiyongBZ).execute();
        return true;
    }

}