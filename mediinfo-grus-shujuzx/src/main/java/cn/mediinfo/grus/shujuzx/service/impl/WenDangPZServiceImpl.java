package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangMBDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangUpDateDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangMBModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangMBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class WenDangPZServiceImpl implements WenDangPZService {

    private final LyraIdentityService lyraIdentityService;
    private final SC_ZD_WenDangRepository sc_zd_wenDangRepository;
    private final SC_ZD_WenDangMBRepository sc_zd_wenDangMBRepository;

    public WenDangPZServiceImpl(LyraIdentityService lyraIdentityService,
                                SC_ZD_WenDangRepository sc_zd_wenDangRepository, SC_ZD_WenDangMBRepository sc_zd_wenDangMBRepository) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_zd_wenDangRepository = sc_zd_wenDangRepository;
        this.sc_zd_wenDangMBRepository = sc_zd_wenDangMBRepository;
    }

    /**
     * 获取文档配置列表
     */
    @Override
    public List<SC_ZD_WenDangDto> getWenDangPZList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        List<SC_ZD_WenDangModel> list = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .orderBy(sjModel.shunXuHao.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : MapUtils.copyListProperties(list, SC_ZD_WenDangDto::new);
    }

    /**
     * 获取文档配置数量
     */
    @Override
    public long getWenDangPZCount(String leiBieDM, String likeQuery) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        return new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .fetch().size();
    }

    /**
     * 新增文档配置
     */
    @Override
    public String addWenDangPZ(SC_ZD_WenDangGreaterDto wenDangGreaterDto) throws TongYongYWException {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        var Count = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(wenDangGreaterDto.getWenDangID()), () -> sjModel.wenDangID.eq(wenDangGreaterDto.getWenDangID())))
                .fetch().size();

        if (Count > 0) {
            throw new TongYongYWException("该文档已配置,请重新确认!");
        }

        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var jiGouMC = lyraIdentityService.getJiGouMC();
        var WenDang = new SC_ZD_WenDangModel();
        WenDang.setZuZhiJGID(zuZhiJGID);
        WenDang.setZuZhiJGMC(jiGouMC);
        MapUtils.mergeProperties(wenDangGreaterDto, WenDang, true);
        sc_zd_wenDangRepository.save(WenDang);

        var WenDangMB = new SC_ZD_WenDangMBModel();
        WenDangMB.setZuZhiJGID(zuZhiJGID);
        WenDangMB.setZuZhiJGMC(jiGouMC);
        MapUtils.mergeProperties(wenDangGreaterDto, WenDangMB, true);
        sc_zd_wenDangMBRepository.save(WenDangMB);
        return WenDang.getId();
    }


    /**
     * 修改文档配置
     */
    @Override
    public Boolean UpDateWenDangPZ(SC_ZD_WenDangUpDateDto wenDangUpDateDto) throws TongYongYWException {

        var wenDangPZ = sc_zd_wenDangRepository.findById(wenDangUpDateDto.getId()).orElseGet(() -> null);
        if (wenDangPZ == null) {
            throw new TongYongYWException("查无此数据！");
        }
        MapUtils.mergeProperties(wenDangUpDateDto, wenDangPZ, true);
        sc_zd_wenDangRepository.save(wenDangPZ);

        var wenDangPZMB = sc_zd_wenDangMBRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangPZ.getWenDangID())).select(SC_ZD_WenDangMBModel.class).fetchFirst();

        MapUtils.mergeProperties(wenDangUpDateDto, wenDangPZMB, true);
        sc_zd_wenDangMBRepository.save(wenDangPZMB);
        return true;
    }

    /**
     * 根据文档ID获取模板内容
     */
    @Override
    public SC_ZD_WenDangMBDto getWenDangMBXX(String wenDangID) throws TongYongYWException {
        var wenDangPZMB = sc_zd_wenDangMBRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangID)).select(SC_ZD_WenDangMBDto.class).fetchFirst();
        if (wenDangPZMB == null) {
            throw new TongYongYWException("查无此数据！");
        }
        return wenDangPZMB;
    }

    /**
     * 修改模板内容（文档配置编辑提交）
     */
    @Override
    public Boolean UpDateWenDangMBXX(String id, String xmlStr) throws TongYongYWException {
        var wenDangMB = sc_zd_wenDangMBRepository.findById(id).orElseGet(() -> null);
        if (wenDangMB == null) {
            throw new TongYongYWException("查无此数据！");
        }
        wenDangMB.setMuBanNR(xmlStr);
        sc_zd_wenDangMBRepository.save(wenDangMB);
        return true;
    }

    /**
     * 根据id移除单个指标
     */
    @Override
    public Boolean zuoFeiWenDangPZ(String id) throws TongYongYWException {
        var wenDangPZ = sc_zd_wenDangRepository.findById(id).orElseGet(() -> null);
        if (wenDangPZ == null) {
            throw new TongYongYWException("查无此数据！");
        }
        sc_zd_wenDangRepository.deleteById(id);

        var wenDangPZMB = sc_zd_wenDangMBRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangPZ.getWenDangID())).select(SC_ZD_WenDangMBModel.class).fetchFirst();
        if (wenDangPZMB == null) {
            throw new TongYongYWException("查无此数据！");
        }
        sc_zd_wenDangMBRepository.deleteById(wenDangPZMB.getId());
        return true;
    }

    /**
     * 根据文档ID获取模板内容
     */
    @Override
    public SC_ZD_WenDangDto getWenDangXXByWDID(String wenDangID) throws TongYongYWException {
        var wenDangXX = sc_zd_wenDangRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangID)).select(SC_ZD_WenDangDto.class).fetchFirst();
        if (wenDangXX == null) {
            throw new TongYongYWException("暂未找到该文档！");
        }
        return wenDangXX;
    }

}
