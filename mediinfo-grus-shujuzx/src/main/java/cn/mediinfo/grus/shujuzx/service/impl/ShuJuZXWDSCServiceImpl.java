package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJMXInDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJMXOutDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJXXInDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXSCs.SC_SC_ShouCangJXXOutDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_SC_ShouCangJXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuZXWDSCService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.util.MapUtils;
import cn.mediinfo.starter.base.util.PageRequestUtil;
import cn.mediinfo.starter.base.util.QueryDSLUtils;
import cn.mediinfo.starter.base.util.StringUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    private final EntityManager entityManager;

    private record XMModelAndXXModelPO(String id,String shouCangJMC,String beiZhu,Integer shunXuHao,SC_SC_ShouCangJMXModel mxModel) {}



    public ShuJuZXWDSCServiceImpl(LyraIdentityService lyraIdentityService, SC_SC_ShouCangJXXRepository scScShouCangJXXRepository, SC_SC_ShouCangJMXRepository scScShouCangJMXRepository, EntityManager entityManager) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_sc_shouCangJXXRepository = scScShouCangJXXRepository;
        this.sc_sc_shouCangJMXRepository = scScShouCangJMXRepository;
        this.entityManager = entityManager;
    }
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        if (sc_sc_shouCangJXXRepository.existsByShouCangJMC(shouCangJInDto.getShouCangJMC())) {
            throw new TongYongYWException("收藏夹名称已存在,请重新确认!");
        }
        SC_SC_ShouCangJXXModel addModel = new SC_SC_ShouCangJXXModel();
        MapUtils.mergeProperties(shouCangJInDto, addModel);
        addModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        addModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        addModel.setYongHuID(lyraIdentityService.getYongHuId());
        addModel.setYongHuXM(lyraIdentityService.getUserName());
        sc_sc_shouCangJXXRepository.save(addModel);
        // todo：操作数
        return 1;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addShouCangJMX(SC_SC_ShouCangJMXInDto shouCangJMXInDto) {
        // todo:无值的时候相当于copy？ new Date() 源merge中false  ID前端传入？
        SC_SC_ShouCangJMXModel addModel = new SC_SC_ShouCangJMXModel();
        MapUtils.mergeProperties(shouCangJMXInDto, addModel);
        addModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        addModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        addModel.setShouCangRID(lyraIdentityService.getYongHuId());
        addModel.setShouCangRXM(lyraIdentityService.getUserName());
        addModel.setShouCangSJ(new Date());
        sc_sc_shouCangJMXRepository.save(addModel);
        return 1;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer updateShouCangJia(SC_SC_ShouCangJXXInDto shouCangJInDto) throws TongYongYWException {
        SC_SC_ShouCangJXXModel updateModel = sc_sc_shouCangJXXRepository.findById(shouCangJInDto.getId()).orElse(null);
        if (updateModel == null) {
            throw new TongYongYWException("未找到相关可修改的信息!");

        }
        List<SC_SC_ShouCangJMXModel> shouCangJMXList = sc_sc_shouCangJMXRepository.findByShouCangJID(shouCangJInDto.getId());
        MapUtils.mergeProperties(shouCangJInDto,updateModel);
        sc_sc_shouCangJXXRepository.save(updateModel);
        shouCangJMXList.forEach(s->s.setShouCangJMC(shouCangJInDto.getShouCangJMC()));
        sc_sc_shouCangJMXRepository.saveAll(shouCangJMXList);
        return 1;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer yiChuShouCangJMX(String id) throws TongYongYWException {
        // todo：软删前要校验？
        if (!sc_sc_shouCangJMXRepository.existsById(id)) {
            throw new TongYongYWException("未找到相关可作废的信息!");
        }
        sc_sc_shouCangJMXRepository.softDelete(id);
        return 1;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer zuoFeiShouCangJia(String id) throws TongYongYWException {
        if (!sc_sc_shouCangJXXRepository.existsById(id)) {
            throw new TongYongYWException("未找到相关可作废的信息!");
        }
        QSC_SC_ShouCangJMXModel JMXModel = QSC_SC_ShouCangJMXModel.sC_SC_ShouCangJMXModel;
        sc_sc_shouCangJXXRepository.softDelete(id);
        sc_sc_shouCangJMXRepository.softDelete(JMXModel.shouCangJID.eq(id));
        return 1;
    }

    @Override
    public List<SC_SC_ShouCangJXXOutDto> getShouCangJiaList(String likeQuery) {
        QSC_SC_ShouCangJXXModel xxModel = QSC_SC_ShouCangJXXModel.sC_SC_ShouCangJXXModel;
        QSC_SC_ShouCangJMXModel mxModel = QSC_SC_ShouCangJMXModel.sC_SC_ShouCangJMXModel;
        List<XMModelAndXXModelPO> list = new JPAQueryFactory(entityManager)
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
                // todo:error 为空字符串可以，为null报错
                .where(QueryDSLUtils.whereIf(Objects.nonNull(likeQuery), xxModel.shouCangJMC.contains(likeQuery)))
                .fetch();

        return list
                .stream()
                .collect(
                        Collectors.groupingBy(o -> List.of(
                                o.id(),
                                o.shouCangJMC(),
                                o.beiZhu(),
                                o.shunXuHao()
                        )))
                .entrySet()
                .stream()
                .map(s -> {
                            SC_SC_ShouCangJXXOutDto dto = new SC_SC_ShouCangJXXOutDto();
                            dto.setId(String.valueOf(s.getKey().get(0)));
                            dto.setShouCangJMC(String.valueOf(s.getKey().get(1)));
                            dto.setBeiZhu((String) s.getKey().get(2));
                            dto.setShunXuHao((Integer) s.getKey().get(3));
                            // 患者数量
                            long count = s.getValue().stream().filter(a -> Objects.equals(a.mxModel().getShouCangJID(), s.getKey().get(0))).count();
                            dto.setHuanZheShu((int) count);
                            return dto;
                        }
                )
                .sorted(Comparator.comparingInt(SC_SC_ShouCangJXXOutDto::getShunXuHao))
                .toList();
    }

    @Override
    public Integer getShouCangJMXCount(String likeQuery, String shouCangJID) {
        //根据收藏夹id 获取明细
        List<SC_SC_ShouCangJMXModel> shouCangMXList= sc_sc_shouCangJMXRepository.findByShouCangJIDAndZuZhiJGID(shouCangJID,lyraIdentityService.getJiGouID());
        //通过明细的病人id 获取病人信息
        List<String> bingRenIDList = shouCangMXList.stream().map(SC_SC_ShouCangJMXModel::getBingRenID).distinct().toList();
        QSC_LC_BingRenYLSJModel sjModel=QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;
        return new JPAQueryFactory(entityManager)
                .select(sjModel)
                .from(sjModel)
                // todo:重复
                .where(sjModel.bingRenID.in(bingRenIDList))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(likeQuery),
                        sjModel.xingMing.contains(likeQuery)
                                .or(sjModel.id.contains(likeQuery)
                                        .or(sjModel.zhengJianHM.contains(likeQuery)))))
                .fetch().size();
    }

    @Override
    public List<SC_SC_ShouCangJMXOutDto> getShouCangJMXList(String likeQuery, String shouCangJID, Integer pageIndex, Integer pageSize) {
        // todo:分页是否有意义？对bingRenXXList进行分页，得到的是个 shouCangMXList 数量
        pageIndex = pageIndex == null ? 1 : pageIndex;
        pageSize = pageSize == null ? 15 : pageSize;

        //根据收藏夹id 获取明细
        List<SC_SC_ShouCangJMXModel> shouCangMXs = sc_sc_shouCangJMXRepository.findByShouCangJIDAndZuZhiJGID(shouCangJID, lyraIdentityService.getJiGouID());
        List<SC_SC_ShouCangJMXOutDto> shouCangMXList = MapUtils.copyListProperties(shouCangMXs, SC_SC_ShouCangJMXOutDto::new);
        //通过明细的病人id 获取病人信息
        List<String> bingRenIDList = shouCangMXList.stream().map(SC_SC_ShouCangJMXOutDto::getBingRenID).distinct().toList();
        QSC_LC_BingRenYLSJModel sjModel = QSC_LC_BingRenYLSJModel.sC_LC_BingRenYLSJModel;

        List<SC_LC_BingRenYLSJModel> bingRenXXList = new JPAQueryFactory(entityManager)
                .select(sjModel)
                .from(sjModel)
                .where(sjModel.bingRenID.in(bingRenIDList))
                .where(QueryDSLUtils.whereIf(StringUtil.hasText(likeQuery),
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
