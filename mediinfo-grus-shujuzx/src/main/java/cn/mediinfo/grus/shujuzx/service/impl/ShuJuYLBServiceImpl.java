package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBDto;
import cn.mediinfo.grus.shujuzx.dto.shujuylbs.SC_ZD_ShuJuYLBUpdateDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYLBModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYLBModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYLBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShuJuYZYRepository;
import cn.mediinfo.grus.shujuzx.service.ShuJuYLBService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ShuJuYLBServiceImpl implements ShuJuYLBService {
    private final SC_ZD_ShuJuYLBRepository sc_zd_shuJuYLBRepository;
    private final SC_ZD_ShuJuYZYRepository sc_zd_shuJuYZYRepository;
    private final LyraIdentityService lyraIdentityService;
    private final EntityManager entityManager;

    public ShuJuYLBServiceImpl(
            SC_ZD_ShuJuYLBRepository sc_zd_shuJuYLBRepository,
            SC_ZD_ShuJuYZYRepository sc_zd_shuJuYZYRepository,
            LyraIdentityService lyraIdentityService,
            EntityManager entityManager) {
        this.sc_zd_shuJuYLBRepository = sc_zd_shuJuYLBRepository;
        this.sc_zd_shuJuYZYRepository = sc_zd_shuJuYZYRepository;
        this.lyraIdentityService = lyraIdentityService;
        this.entityManager = entityManager;
    }

    /**
     * 新增数据源类别
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addShuJuYLB(SC_ZD_ShuJuYLBCreateDto createDto) throws TongYongYWException {
        //查询是否存在
        var shiFouCZ = sc_zd_shuJuYLBRepository.existsByShuJuYLBID(createDto.getShuJuYLBID());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("数据源类别已存在!");
        }

        //构造
        var entity = MapUtils.copyProperties(createDto, SC_ZD_ShuJuYLBModel::new, (s, t) -> {
            if (t.getShunXuHao() == null) {
                t.setShunXuHao(0);
            }
            t.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            t.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        });

        //保存
        var result = sc_zd_shuJuYLBRepository.save(entity);
        return result.getId();
    }

    /**
     * 修改数据源类别
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String updateShuJuYLB(SC_ZD_ShuJuYLBUpdateDto updateDto) throws TongYongYWException, WeiZhaoDSJException {
        var shiFouCZ = sc_zd_shuJuYLBRepository.existsByIdIsNotAndShuJuYLBID(updateDto.getId(), updateDto.getShuJuYLBID());
        if (shiFouCZ) {
            throw new TongYongYWException("数据源类别已存在!");
        }
        var entity = sc_zd_shuJuYLBRepository.findById(updateDto.getId()).orElse(null);
        if (null == entity) {
            throw new WeiZhaoDSJException("该数据源类别不存在!");
        }

        //修改数据源值域
        if (!Objects.equals(entity.getShuJuYLBID(), updateDto.getShuJuYLBID()) || !Objects.equals(entity.getShuJuYLBMC(), updateDto.getShuJuYLBMC())) {
            var shuJuZYList = sc_zd_shuJuYZYRepository.findByZuZhiJGIDAndShuJuYLBID(entity.getZuZhiJGID(), entity.getShuJuYLBID());
            for (var item : shuJuZYList) {
                item.setShuJuYLBID(entity.getShuJuYLBID());
                item.setShuJuYLBMC(entity.getShuJuYLBMC());
            }
            sc_zd_shuJuYZYRepository.saveAll(shuJuZYList);
        }
        //把dto赋值到entity
        MapUtils.mergeProperties(updateDto, entity);
        if (null == entity.getShunXuHao()) {
            entity.setShunXuHao(0);
        }
        var result = sc_zd_shuJuYLBRepository.save(entity);
        return result.getId();
    }

    /**
     * 根据ID获取数据源类别
     *
     * @param id
     * @return
     * @throws
     */
    @Override
    public SC_ZD_ShuJuYLBDto getShuJuYLBByID(String id) {
        var shuJuYLB = sc_zd_shuJuYLBRepository.findById(id).orElse(null);
        return MapUtils.copyProperties(shuJuYLB, SC_ZD_ShuJuYLBDto::new);
    }

    /**
     * 作废数据源类别
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiShuJuYLB(String id) throws WeiZhaoDSJException {
        var entity = sc_zd_shuJuYLBRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new WeiZhaoDSJException("该数据源类别不存在!");
        }
        //删除数据源类别
        sc_zd_shuJuYLBRepository.deleteById(id);
        //删除数据源值域
        QSC_ZD_ShuJuYZYModel shuJuYZY = QSC_ZD_ShuJuYZYModel.sC_ZD_ShuJuYZYModel;
        sc_zd_shuJuYZYRepository.delete(shuJuYZY.zuZhiJGID.eq(entity.getZuZhiJGID()).and(shuJuYZY.shuJuYLBID.eq(entity.getShuJuYLBID())));
        return true;
    }

    /**
     * 查询数据源类别list
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_ZD_ShuJuYLBDto> getShuJuYLBList(String zuZhiJGID, String likeQuery, Integer pageIndex, Integer pageSize) {

        List<SC_ZD_ShuJuYLBModel> entityList = sc_zd_shuJuYLBRepository.asQuerydsl()
                .whereIf(StringUtils.hasText(zuZhiJGID), x -> x.zuZhiJGID.eq(zuZhiJGID))
                .whereIf(StringUtils.hasText(likeQuery),
                        x -> x.shuJuYLBMC.contains(likeQuery)
                                .or(x.shuJuYLBID.contains(likeQuery))
                                .or(x.shuRuMa1.toLowerCase().contains(likeQuery.toLowerCase()))
                                .or(x.shuRuMa2.toLowerCase().contains(likeQuery.toLowerCase()))
                                .or(x.shuRuMa3.toLowerCase().contains(likeQuery.toLowerCase())))
                .orderBy(x -> x.shuJuYLBID.asc()).fetchPage(pageIndex,pageSize);

        return MapUtils.copyListProperties(entityList, SC_ZD_ShuJuYLBDto::new);
    }

    /**
     * 获取数据源类别总行数
     *
     * @param zuZhiJGID
     * @param likeQuery
     * @return
     */
    @Override
    public long getShuJuYLBCount(String zuZhiJGID, String likeQuery) {
        //获取输入码类型
        var shuRuMLX = lyraIdentityService.getShuRuMLX();
        QSC_ZD_ShuJuYLBModel shuJuYLB = QSC_ZD_ShuJuYLBModel.sC_ZD_ShuJuYLBModel;
        var factory = new JPAQueryFactory(sc_zd_shuJuYLBRepository.getEntityManager());
        var query = factory.select(shuJuYLB).from(shuJuYLB);

        //组织机构过滤
        if (StringUtils.hasText(zuZhiJGID)) {
            query.where(shuJuYLB.zuZhiJGID.eq(zuZhiJGID));
        }
        //输入码过滤
        if (StringUtils.hasText(likeQuery)) {
            query.where(shuJuYLB.shuJuYLBID.like("%" + likeQuery + "%")
                    .or(shuJuYLB.shuJuYLBMC.like("%" + likeQuery + "%")));
            if ("1".equals(shuRuMLX)) {
                query.where(shuJuYLB.shuRuMa1.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else if ("2".equals(shuRuMLX)) {
                query.where(shuJuYLB.shuRuMa2.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            } else {
                query.where(shuJuYLB.shuRuMa3.toLowerCase().like("%" + likeQuery.toLowerCase() + "%"));
            }
        }
        return query.fetch().size();
    }

}
