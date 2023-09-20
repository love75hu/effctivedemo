package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJMXInDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJMXOutDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJXXInDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxscs.SC_SC_ShouCangJXXOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.SC_LC_BingRenYLSJRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXWDSCService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 数据中心 - 我的收藏
 */
@Service
public class ShuJuZXWDSCServiceImpl implements ShuJuZXWDSCService {
    private final LyraIdentityService lyraIdentityService;

    private final SC_SC_ShouCangJXXRepository sc_sc_shouCangJXXRepository;
    private final SC_SC_ShouCangJMXRepository sc_sc_shouCangJMXRepository;
    private final SC_LC_BingRenYLSJRepository sc_lc_bingRenYLSJRepository;
    private final EntityManager entityManager;



    private record XMModelAndXXModelPO(String id,String shouCangJMC,String beiZhu,Integer shunXuHao,SC_SC_ShouCangJMXModel mxModel) {}
    private record GroupXMModelAndXXModelPO(String id,String shouCangJMC,String beiZhu,Integer shunXuHao) {}



    public ShuJuZXWDSCServiceImpl(LyraIdentityService lyraIdentityService, SC_SC_ShouCangJXXRepository scScShouCangJXXRepository, SC_SC_ShouCangJMXRepository scScShouCangJMXRepository, SC_LC_BingRenYLSJRepository scLcBingRenYLSJRepository, EntityManager entityManager) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_sc_shouCangJXXRepository = scScShouCangJXXRepository;
        this.sc_sc_shouCangJMXRepository = scScShouCangJMXRepository;
        this.sc_lc_bingRenYLSJRepository = scLcBingRenYLSJRepository;
        this.entityManager = entityManager;
    }
    @Override
    public Integer  addShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        if (sc_sc_shouCangJXXRepository.existsByShouCangJMCAndYongHuID(shouCangJInDto.getShouCangJMC(),lyraIdentityService.getYongHuId())) {
            throw new TongYongYWException("收藏夹名称已存在,请重新确认!");
        }

        SC_SC_ShouCangJXXModel addModel = new SC_SC_ShouCangJXXModel();
        MapUtils.mergeProperties(shouCangJInDto, addModel,false);
        if (Objects.isNull(addModel.getShunXuHao())){
            addModel.setShunXuHao(sc_sc_shouCangJXXRepository.getMaxShunXuHao(lyraIdentityService.getYongHuId()) + 1);
        }
        addModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        addModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        addModel.setYongHuID(lyraIdentityService.getYongHuId());
        addModel.setYongHuXM(lyraIdentityService.getUserName());
        sc_sc_shouCangJXXRepository.save(addModel);
        return 1;
    }

    @Override
    public Integer addShouCangJMX(SC_SC_ShouCangJMXInDto shouCangJMXInDto) {
        SC_SC_ShouCangJMXModel addModel = new SC_SC_ShouCangJMXModel();
        MapUtils.mergeProperties(shouCangJMXInDto, addModel,false);
        addModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        addModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        addModel.setShouCangRID(lyraIdentityService.getYongHuId());
        addModel.setShouCangRXM(lyraIdentityService.getUserName());
        addModel.setShouCangSJ(new Date());
        sc_sc_shouCangJMXRepository.save(addModel);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        SC_SC_ShouCangJXXModel updateModel = sc_sc_shouCangJXXRepository.findById(shouCangJInDto.getId()).orElse(null);
        if (updateModel == null) {
            throw new TongYongYWException("未找到相关可修改的信息!");

        }
        List<SC_SC_ShouCangJMXModel> shouCangJMXList = sc_sc_shouCangJMXRepository.findByShouCangJID(shouCangJInDto.getId());
        MapUtils.mergeProperties(shouCangJInDto,updateModel,true);
        sc_sc_shouCangJXXRepository.save(updateModel);
        shouCangJMXList.forEach(s->s.setShouCangJMC(shouCangJInDto.getShouCangJMC()));
        sc_sc_shouCangJMXRepository.saveAll(shouCangJMXList);
        return 1;
    }

    @Override
    public Integer yiChuShouCangJMX(String id){
        sc_sc_shouCangJMXRepository.deleteById( id);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer zuoFeiShouCangJia(String id) throws TongYongYWException {
        if (!sc_sc_shouCangJXXRepository.existsById(id)) {
            throw new TongYongYWException("未找到相关可作废的信息!");
        }
        QSC_SC_ShouCangJMXModel JMXModel = QSC_SC_ShouCangJMXModel.sC_SC_ShouCangJMXModel;
        sc_sc_shouCangJXXRepository.deleteById(id);
        sc_sc_shouCangJMXRepository.delete(JMXModel.shouCangJID.eq(id));
        return 1;
    }

    @Override
    public List<SC_SC_ShouCangJXXOutDto> getShouCangJiaList(String likeQuery) {
        QSC_SC_ShouCangJXXModel xxModel = QSC_SC_ShouCangJXXModel.sC_SC_ShouCangJXXModel;
        QSC_SC_ShouCangJMXModel mxModel = QSC_SC_ShouCangJMXModel.sC_SC_ShouCangJMXModel;
        var list = new JPAQueryFactory(sc_sc_shouCangJXXRepository.getEntityManager())
                .select(
                        QueryDSLUtils.record(XMModelAndXXModelPO.class,
                                xxModel.id,
                                xxModel.shouCangJMC,
                                xxModel.beiZhu,
                                xxModel.shunXuHao,
                                mxModel
                        )
                )
                .from(xxModel)
                .leftJoin(mxModel)
                .on(xxModel.id.eq(mxModel.shouCangJID)
                        .and(xxModel.zuZhiJGID.eq(mxModel.zuZhiJGID)))
                .where(xxModel.zuZhiJGID.eq(lyraIdentityService.getJiGouID())
                        .and(xxModel.yongHuID.eq(lyraIdentityService.getYongHuId())))
                .where(QueryDSLUtils.whereIf(Objects.nonNull(likeQuery), ()->xxModel.shouCangJMC.contains(likeQuery)))
                .fetch();

        return list
                .stream()
                .collect(
                        Collectors.groupingBy(o -> new GroupXMModelAndXXModelPO(
                                o.id(),
                                o.shouCangJMC(),
                                o.beiZhu(),
                                o.shunXuHao()
                        )))
                .entrySet()
                .stream()
                .map(s -> {
                            SC_SC_ShouCangJXXOutDto dto = new SC_SC_ShouCangJXXOutDto();
                            dto.setId(s.getKey().id);
                            dto.setShouCangJMC(s.getKey().shouCangJMC);
                            dto.setBeiZhu(s.getKey().beiZhu);
                            dto.setShunXuHao(s.getKey().shunXuHao);
                            // 患者数量
                            long count = s.getValue().stream().filter(a -> a.mxModel() != null && Objects.equals(a.mxModel().getShouCangJID(), s.getKey().id)).count();
                            dto.setHuanZheShu((int) count);
                            return dto;
                        }
                )
                .sorted(Comparator.comparing(SC_SC_ShouCangJXXOutDto::getShunXuHao, Comparator.nullsFirst(Comparator.naturalOrder())))
                .toList();
    }

    @Override
    public Integer getShouCangJMXCount(String likeQuery, String shouCangJID) {
        //根据收藏夹id 获取明细
        List<SC_SC_ShouCangJMXModel> shouCangMXList= sc_sc_shouCangJMXRepository.findByShouCangJIDAndZuZhiJGID(shouCangJID,lyraIdentityService.getJiGouID());
        //通过明细的病人id 获取病人信息
        List<String> bingRenIDList = shouCangMXList.stream().map(SC_SC_ShouCangJMXModel::getBingRenID).distinct().toList();
        QSC_LC_BingRenYLSJModel sjModel=QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;
        return new JPAQueryFactory(sc_lc_bingRenYLSJRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(sjModel.bingRenID.in(bingRenIDList))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(likeQuery),()->
                        sjModel.xingMing.contains(likeQuery)
                                .or(sjModel.id.contains(likeQuery)
                                        .or(sjModel.zhengJianHM.contains(likeQuery)))))
                .fetch().size();
    }

    @Override
    public List<SC_SC_ShouCangJMXOutDto> getShouCangJMXList(String likeQuery, String shouCangJID, Integer pageIndex, Integer pageSize) {
        //根据收藏夹id 获取明细
        List<SC_SC_ShouCangJMXModel> shouCangMXs = sc_sc_shouCangJMXRepository.findByShouCangJIDAndZuZhiJGID(shouCangJID, lyraIdentityService.getJiGouID());
        List<SC_SC_ShouCangJMXOutDto> shouCangMXList = MapUtils.copyListProperties(shouCangMXs, SC_SC_ShouCangJMXOutDto::new);
        //通过明细的病人id 获取病人信息
        List<String> bingRenIDList = shouCangMXList.stream().map(SC_SC_ShouCangJMXOutDto::getBingRenID).distinct().toList();
        QSC_LC_BingRenYLSJModel sjModel = QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;

        List<SC_LC_BingRenYLSJModel> bingRenXXList = new JPAQueryFactory(sc_lc_bingRenYLSJRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(sjModel.bingRenID.in(bingRenIDList))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(likeQuery),()->
                        sjModel.xingMing.contains(likeQuery)
                                .or(sjModel.id.contains(likeQuery)
                                        .or(sjModel.zhengJianHM.contains(likeQuery)))))
                .orderBy(sjModel.chuShengRQ.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();


        //过滤没有查询到的病人
        shouCangMXList = shouCangMXList.stream().filter(s -> bingRenXXList.stream().map(SC_LC_BingRenYLSJModel::getBingRenID).toList().contains(s.getBingRenID())).toList();

        for (SC_SC_ShouCangJMXOutDto shouCangMX : shouCangMXList) {

            //病人信息
            SC_LC_BingRenYLSJModel bingRenXX = bingRenXXList.stream().filter(s -> Objects.equals(s.getBingRenID(), shouCangMX.getBingRenID())).findFirst().orElse(null);
            if (bingRenXX != null) {
                shouCangMX.setXingMing(bingRenXX.getXingMing());
                shouCangMX.setMPI(bingRenXX.getId());
                shouCangMX.setChuShengRQ(bingRenXX.getChuShengRQ());
                shouCangMX.setZhengJianLXMC(bingRenXX.getZhengJianLXMC());
                shouCangMX.setZhengJianHM(bingRenXX.getZhengJianHM());
                shouCangMX.setMenZhenJZCS(bingRenXX.getMenZhenCS());
                shouCangMX.setZhuYuanJZCS(bingRenXX.getZhuYuanCS());
                shouCangMX.setJiZhenJZCS(bingRenXX.getJiZhenCS());
                shouCangMX.setGongWeiJZCS(bingRenXX.getGongWeiCS());
                shouCangMX.setXingBieDM(bingRenXX.getXingBieDM());
            }
            shouCangMX.setShouCangRQ(shouCangMX.getShouCangSJ());
        }
        return shouCangMXList;
    }
}
