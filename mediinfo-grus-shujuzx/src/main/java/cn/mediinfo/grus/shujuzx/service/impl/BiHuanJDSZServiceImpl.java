package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDListDto;
import cn.mediinfo.grus.shujuzx.dto.BiHuanJDSZs.SC_ZD_BiHuanJDUpdateDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanJDModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanJDModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_BiHuanJDRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanJDSZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.exception.WeiZhaoDSJException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.lyra.service.SequenceService;
import cn.mediinfo.starter.base.util.MapUtils;
import cn.mediinfo.starter.base.util.PageRequestUtil;
import cn.mediinfo.starter.base.util.QueryDSLUtils;
import cn.mediinfo.starter.base.util.StringUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiHuanJDSZServiceImpl implements BiHuanJDSZService {
    private final SC_ZD_BiHuanJDRepository sc_zd_biHuanJDRepository;
    private final EntityManager entityManager;
    private final LyraIdentityService lyraIdentityService;
    private final SequenceService sequenceService;

    public BiHuanJDSZServiceImpl(
            SC_ZD_BiHuanJDRepository scZdBiHuanJDRepository,
            EntityManager entityManager,
            LyraIdentityService lyraIdentityService,
            SequenceService sequenceService) {
        this.sc_zd_biHuanJDRepository = scZdBiHuanJDRepository;
        this.entityManager = entityManager;
        this.lyraIdentityService = lyraIdentityService;
        this.sequenceService = sequenceService;
    }

    /**
     * 获取闭环节点list
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_ZD_BiHuanJDListDto> getBiHuanJDList(String biHuanLXDM, String likeQuery, Integer pageIndex, Integer pageSize) {
        QSC_ZD_BiHuanJDModel biHuanJD = QSC_ZD_BiHuanJDModel.sC_ZD_BiHuanJDModel;
        var query = new JPAQueryFactory(sc_zd_biHuanJDRepository.getEntityManager()).select(biHuanJD).from(biHuanJD);
        query.where(biHuanJD.biHuanLXDM.eq(biHuanLXDM));
        if (StringUtil.hasText(likeQuery)) {
            query.where(biHuanJD.jieDianID.contains(likeQuery).or(biHuanJD.jieDianMC.contains(likeQuery)));
        }
        query.orderBy(biHuanJD.shunXuHao.asc())
                .orderBy(biHuanJD.xiuGaiSJ.asc());
        //分页处理
        var entityList = query.offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize).fetch();
        return MapUtils.copyListProperties(entityList, SC_ZD_BiHuanJDListDto::new);
    }

    /**
     * 获取闭环节点数量
     *
     * @param biHuanLXDM
     * @param likeQuery
     * @return
     */
    @Override
    public long getBiHuanJDCount(String biHuanLXDM, String likeQuery) {
        QSC_ZD_BiHuanJDModel biHuanJD = QSC_ZD_BiHuanJDModel.sC_ZD_BiHuanJDModel;
        var query = new JPAQueryFactory(sc_zd_biHuanJDRepository.getEntityManager()).select(biHuanJD).from(biHuanJD);
        query.where(biHuanJD.biHuanLXDM.eq(biHuanLXDM))
                .where(QueryDSLUtils.whereIfHasText(likeQuery,biHuanJD.jieDianID.contains(likeQuery).or(biHuanJD.jieDianMC.contains(likeQuery))));
        return query.fetch().size();
    }

    /**
     * 新增闭环节点
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    public SC_ZD_BiHuanJDDto addBiHuanJD(SC_ZD_BiHuanJDCreateDto createDto) throws TongYongYWException {
        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var existsJieDianMC = sc_zd_biHuanJDRepository.existsByZuZhiJGIDAndBiHuanLXDMAndJieDianMC(zuZhiJGID, createDto.getBiHuanLXDM(), createDto.getJieDianMC());
        if (existsJieDianMC) {
            throw new TongYongYWException("节点名称已存在!");
        }
        createDto.setJieDianID(sequenceService.getXuHao("SC_ZD_BiHuan_JieDianID", 6));
        var existsJieDianID = sc_zd_biHuanJDRepository.existsByZuZhiJGIDAndJieDianID(zuZhiJGID, createDto.getJieDianID());
        if (existsJieDianID) {
            throw new TongYongYWException("节点ID已存在!");
        }
        var entity = MapUtils.copyProperties(createDto, SC_ZD_BiHuanJDModel::new);
        entity.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        entity.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        sc_zd_biHuanJDRepository.save(entity);
        return MapUtils.copyProperties(entity, SC_ZD_BiHuanJDDto::new);
    }

    /**
     * 更新闭环节点
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     * @throws WeiZhaoDSJException
     */
    @Override
    public SC_ZD_BiHuanJDDto updateBiHuanJD(SC_ZD_BiHuanJDUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException {
        QSC_ZD_BiHuanJDModel biHuanJD = QSC_ZD_BiHuanJDModel.sC_ZD_BiHuanJDModel;
        var biHuanJDEntity = new JPAQueryFactory(sc_zd_biHuanJDRepository.getEntityManager())
                .select(biHuanJD)
                .from(biHuanJD)
                .where(biHuanJD.id.ne(updateDto.getId())
                        .and(biHuanJD.jieDianID.eq(updateDto.getJieDianID())
                                .or(biHuanJD.biHuanLXDM.eq(updateDto.getBiHuanLXDM())
                                        .and(biHuanJD.jieDianMC.eq(updateDto.getJieDianMC())))))
                .fetchFirst();
        if (biHuanJDEntity == null) {
            throw new TongYongYWException("节点ID或节点名称已存在!");
        }
        var entity = sc_zd_biHuanJDRepository.findById(updateDto.getId()).orElse(null);
        if (entity == null) {
            throw new WeiZhaoDSJException("数据源值域不存在！");
        }
        MapUtils.mergeProperties(updateDto, entity);
        var result = sc_zd_biHuanJDRepository.save(entity);
        return MapUtils.copyProperties(entity, SC_ZD_BiHuanJDDto::new);
    }

    /**
     * 根据id获取闭环节点
     *
     * @param id
     * @return
     */
    @Override
    public SC_ZD_BiHuanJDDto getBiHuanJDByID(String id) {
        var entity = sc_zd_biHuanJDRepository.findById(id).orElse(null);
        return MapUtils.copyProperties(entity, SC_ZD_BiHuanJDDto::new);
    }

    /**
     * 作废闭环节点
     *
     * @param id
     * @return
     * @throws WeiZhaoDSJException
     */
    @Override
    public Boolean zuoFeiBiHuanJD(String id) throws WeiZhaoDSJException {
        var entity = sc_zd_biHuanJDRepository.findById(id).orElse(null);
        if (entity == null) {
            throw new WeiZhaoDSJException("闭环节点不存在！");
        }
        sc_zd_biHuanJDRepository.softDelete(entity);
        return true;
    }

    /**
     * 根据闭环类型获取闭环节点
     *
     * @param biHuanLXDM
     * @param zhuYuanSYBZ
     * @param menZhenSYBZ
     * @param jiZhenSYBZ
     * @param tiJianSYBZ
     * @return
     */
    @Override
    public List<SC_ZD_BiHuanJDListDto> getBiHuanJDByBHLX(String biHuanLXDM, Integer zhuYuanSYBZ, Integer menZhenSYBZ, Integer jiZhenSYBZ, Integer tiJianSYBZ) {
        QSC_ZD_BiHuanJDModel biHuanJD = QSC_ZD_BiHuanJDModel.sC_ZD_BiHuanJDModel;
        var query = new JPAQueryFactory(sc_zd_biHuanJDRepository.getEntityManager())
                .select(biHuanJD)
                .from(biHuanJD)
                .where(biHuanJD.biHuanLXDM.eq(biHuanLXDM)
                        .and(QueryDSLUtils.whereIf(zhuYuanSYBZ == 1, biHuanJD.zhuYuanSYBZ.eq(zhuYuanSYBZ)))
                        .and(QueryDSLUtils.whereIf(menZhenSYBZ == 1, biHuanJD.zhuYuanSYBZ.eq(menZhenSYBZ)))
                        .and(QueryDSLUtils.whereIf(jiZhenSYBZ == 1, biHuanJD.zhuYuanSYBZ.eq(jiZhenSYBZ)))
                        .and(QueryDSLUtils.whereIf(tiJianSYBZ == 1, biHuanJD.zhuYuanSYBZ.eq(tiJianSYBZ))))
                .orderBy(biHuanJD.shunXuHao.asc());
        return MapUtils.copyListProperties(query.fetch(), SC_ZD_BiHuanJDListDto::new);
    }
}
