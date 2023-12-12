package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.impl.MsfQuerydslExpression;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.renwugl.TongYongPZPO;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.RenWuGLService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import com.querydsl.core.types.Expression;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RenWuGLServiceImpl implements RenWuGLService {
    private final SequenceService sequenceService;
    private final SC_RW_JiBenXXRepository jiBenXXRepository;
    private final LyraIdentityService lyraIdentityService;
    private final SC_RW_ZhiXingRZRepository zhiXingRZRepository;
    private final SC_RW_ShuJuYuanRepository shuJuYuanRepository;
    private final SC_RW_TongYongPZRepository tongYongPZRepository;
    private final SC_ZD_ShuJuYZYRepository shuJuYZYRepository;
    private final SC_ZD_ShuJuYLBRepository shuJuYLBRepository;


    public RenWuGLServiceImpl(SequenceService sequenceService, SC_RW_JiBenXXRepository jiBenXXRepository, LyraIdentityService lyraIdentityService,
                              SC_RW_ZhiXingRZRepository zhiXingRZRepository, SC_RW_ShuJuYuanRepository shuJuYuanRepository, SC_RW_TongYongPZRepository tongYongPZRepository,
                              SC_ZD_ShuJuYZYRepository shuJuYZYRepository,SC_ZD_ShuJuYLBRepository shuJuYLBRepository) {
        this.lyraIdentityService = lyraIdentityService;
        this.jiBenXXRepository = jiBenXXRepository;
        this.sequenceService = sequenceService;
        this.zhiXingRZRepository = zhiXingRZRepository;
        this.shuJuYuanRepository = shuJuYuanRepository;
        this.tongYongPZRepository = tongYongPZRepository;
        this.shuJuYZYRepository = shuJuYZYRepository;
        this.shuJuYLBRepository=shuJuYLBRepository;
    }

    /**
     * 获取列表
     *
     * @param queryDto
     * @return
     */
    @Override
    public List<SC_RW_JiBenXXListDto> getJiBenXXList(SC_RW_JiBenXXQueryDto queryDto) {
        return getQuery(queryDto).select(SC_RW_JiBenXXListDto.class).fetchPage(queryDto.getPageIndex(), queryDto.getPageSize());
    }

    /**
     * 获取列表数量
     *
     * @param queryDto
     * @return
     */
    @Override
    public long getJiBenXXCount(SC_RW_JiBenXXQueryDto queryDto) {
        return getQuery(queryDto).count();
    }

    /**
     * 列表查询sql
     *
     * @param queryDto
     * @return
     */
    @Override
    public MsfQuerydslExpression<QSC_RW_JiBenXXModel, SC_RW_JiBenXXModel> getQuery(SC_RW_JiBenXXQueryDto queryDto) {

        return jiBenXXRepository.asQuerydsl().whereIf(StringUtil.hasText(queryDto.getLikeQuery()), t -> t.renWuMC.contains(queryDto.getLikeQuery()))
                .whereIf(StringUtil.hasText(queryDto.getFenLeiDM()), t -> t.fenLeiDM.eq(queryDto.getFenLeiDM()))
                .whereIf(Objects.equals(queryDto.getQiYongBZ(), 1), t -> t.qiYongBZ.eq(1));
    }

    @Override
    public SC_RW_JiBenXXDto getRenWuXXById(String id) throws TongYongYWException {
        var result= jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(result)){
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        return BeanUtil.copyProperties(result,SC_RW_JiBenXXDto::new);
    }
    /**
     * 根据ID查询任务基本信息
     * @param ids
     * @return
     * @throws TongYongYWException
     */
    @Override
    public List<SC_RW_JiBenXXDto> getRenWuXXByIds(List<String> ids) throws TongYongYWException {
        var result= jiBenXXRepository.findAllById(ids);
        if (result.size()==0){
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        return BeanUtil.copyListProperties(result,SC_RW_JiBenXXDto::new);
    }



    /**
     * 新增任务基本信息
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */

    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addRenWuJBXX(SC_RW_JiBenXXCreateDto createDto) throws TongYongYWException {
        //查询是否存在
        var shiFouCZ = jiBenXXRepository.existsByRenWuMC(createDto.getRenWuMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("该任务名称已存在:" + createDto.getRenWuMC() + "!");
        }
        if (!StringUtil.hasText(createDto.getZhiXingPLDM()) && StringUtil.hasText(createDto.getZhiXingPLMC())){
            var dto=BeanUtil.copyProperties(createDto,SC_RW_JiBenXXDto::new);
            createDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }

        var entity = BeanUtil.copyProperties(createDto, SC_RW_JiBenXXModel::new, (s, t) -> {
            t.setRenWuID(sequenceService.getXuHao("SC_RW_JiBenXX_RenWuID", 7));
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setQiYongBZ(1);
        });
        jiBenXXRepository.save(entity);
        return entity.getId();
    }

    /**
     * 编辑任务基本信息
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateRenWuJBXX(SC_RW_JiBenXXUpdateDto updateDto) throws TongYongYWException {
        var entity = jiBenXXRepository.findById(updateDto.getId()).orElse(null);
        if (Objects.isNull(entity)) {
            throw new TongYongYWException("该任务基本信息不存在");
        }
        //查询是否存在
        var shiFouCZ = jiBenXXRepository.existsByIdIsNotAndRenWuMC(updateDto.getId(), updateDto.getRenWuMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("该任务名称已存在!");
        }
        if (!StringUtil.hasText(updateDto.getZhiXingPLDM()) && StringUtil.hasText(updateDto.getZhiXingPLMC())){
            var dto=BeanUtil.copyProperties(updateDto,SC_RW_JiBenXXDto::new);
            updateDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }
        //把dto赋值到entity
        BeanUtil.mergeProperties(updateDto, entity);
        jiBenXXRepository.save(entity);
        return true;
    }

    /**
     * 启用任务
     *
     * @param id
     * @param qiYongBZ
     * @return
     * @throws TongYongYWException
     */
    @Override
    public Boolean qiYongRW(String id, Integer qiYongBZ) throws TongYongYWException {
        var entity = jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            throw new TongYongYWException("该任务基本信息不存在");
        }
        entity.setQiYongBZ(qiYongBZ);
        jiBenXXRepository.save(entity);
        return true;
    }

    /**
     * 获取列表
     *
     * @param queryDto
     * @return
     */
    @Override
    public List<SC_RW_ZhiXingRZListDto> getZhiXingRZList(SC_RW_ZhiXingRZQueryDto queryDto) {
        return zhiXingRZRepository.asQuerydsl().where(t -> t.renWuID.eq(queryDto.getRenWuID()).and(t.zhiXingKSSJ.after(queryDto.getZhiXingKSSJ())).and(t.zhiXingJSSJ.before(queryDto.getZhiXingJSSJ())))
                .orderBy(t -> t.zhiXingSJ.desc())
                .select(SC_RW_ZhiXingRZListDto.class)
                .fetchPage(queryDto.getPageIndex(), queryDto.getPageSize());
    }

    /**
     * 根据idList获取任务基本信息
     *
     * @param ids
     * @return
     */
    @Override
    public List<SC_RW_JiBenXXDto> getJiBenXXDtoList(String ids) {
        var idList = ids.split(",");
        return zhiXingRZRepository.asQuerydsl().where(t -> t.id.in(idList)).select(SC_RW_JiBenXXDto.class).fetch();

    }

    /**
     * 根据任务ID获取数据源列表
     *
     * @param renWuID
     * @return
     */
    @Override
    public List<SC_RW_ShuJuYuanDto> getShuJuYuanList(String renWuID) {
        return shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID)).select(SC_RW_ShuJuYuanDto.class).fetch();
    }


    /**
     * 数据源列表保存
     *
     * @param createDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveShuJuYuanList(SC_RW_ShuJuYuanCreateDto createDto) throws TongYongYWException {
        var renWuDto = jiBenXXRepository.asQuerydsl().where(t -> t.renWuID.eq(createDto.getRenWuID())).fetchOne();
        if (renWuDto == null) {
            throw new TongYongYWException("当前任务基本信息不存在！");
        }
        var shuJuYuanList = shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(createDto.getRenWuID())).fetch();
        var shuJuYIDs = shuJuYuanList.stream().map(SC_RW_ShuJuYuanModel::getShuJuYID).toList();
        var createShuJuYIDs = createDto.getShuJuYuanDtoList().stream().map(SC_RW_ShuJuYuanDto::getShuJuYID).toList();
        //求差值
        List<String> addIDList = new ArrayList<String>(createShuJuYIDs);
        List<String> delIDList = new ArrayList<String>(shuJuYIDs);
        addIDList.removeAll(shuJuYIDs);
        delIDList.removeAll(createShuJuYIDs);
        //需新增的数据源
        List<SC_RW_ShuJuYuanDto> addList = createDto.getShuJuYuanDtoList().stream().filter(t -> addIDList.contains(t.getShuJuYID())).toList();

        var entity = BeanUtil.copyProperties(addList, SC_RW_ShuJuYuanModel::new, (s, t) -> {
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setQiYongBZ(1);//默认启用
        });
        shuJuYuanRepository.save(entity);
        //需删除的数据源
        shuJuYuanRepository.asDeleteDsl().where(t -> t.shuJuYID.in(delIDList));
        return true;
    }

    /**
     * 获取通用配置列表
     *
     * @return
     */
    @Override
    public List<SC_RW_TongYongPZDto> getTongYongPZ() {
        return shuJuYZYRepository.asQuerydsl()
                .where(q -> q.shuJuYLBID.eq("SC0017"))
                .orderBy(q -> q.shunXuHao.asc())
                .leftJoin(tongYongPZRepository.asQuerydsl(), (shuJuYZY, tongYongPZ) -> shuJuYZY.shuJuYLBID.eq(tongYongPZ.fenLeiDM), TongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                        q.tongYongPZ().renWuDZ,
                        q.tongYongPZ().fuWuQIP,
                        q.tongYongPZ().id,
                        q.tongYongPZ().fenLeiDM.as(q.shuJuYZY().zhiYuID),
                        q.tongYongPZ().fenLeiMC.as(q.shuJuYZY().zhiYuMC)
                }, SC_RW_TongYongPZDto.class).fetch();

    }

    /**
     * 保存通用配置
     * @param creatDto
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveTongYongPZ(List<SC_RW_TongYongPZDto> creatDto) {
        List<SC_RW_TongYongPZModel> result = new ArrayList<>();
        //新增
        var addDto = creatDto.stream().filter(t -> Objects.isNull(t.getId())).toList();
        var addEntity = BeanUtil.copyListProperties(addDto, SC_RW_TongYongPZModel::new, (s, t) -> {
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZuHuID(lyraIdentityService.getJiGouID());
        });
        result.addAll(addEntity);
        //修改
        var updateDto = creatDto.stream().filter(t -> !Objects.isNull(t.getId())).toList();
        var updateEntity = BeanUtil.copyListProperties(updateDto, SC_RW_TongYongPZModel::new);
        result.addAll(updateEntity);
        tongYongPZRepository.saveAll(result);
        return true;
    }

    /**
     * 保存执行频率字典
     * @param dto
     * @return
     * @throws TongYongYWException
     */
    public String saveZhiXingPZZD(SC_RW_JiBenXXDto dto) throws TongYongYWException {
        String zhiYuID="";
        //region 判断执行频率是否存在 不存在的话 新增到值域表

            var shuJuYLBDto=shuJuYLBRepository.asQuerydsl().where(t->t.shuJuYLBID.eq("SC0018")).fetchOne();
            if (!Objects.isNull(shuJuYLBDto)){
                //查询最大的顺序号
                var shuJuYZYList=shuJuYZYRepository.asQuerydsl().where(t->t.shuJuYLBID.eq("SC0018")).select(SC_ZD_ShuJuYZYModel.class).fetch();
                // 获取hgZdHuLiDYMXModelList集合中的JG_ZZ_HuLiDYMXModel对象中的shunXuHao的最大值
                Optional<SC_ZD_ShuJuYZYModel> maxModel = shuJuYZYList.stream()
                        .max(Comparator.comparing(SC_ZD_ShuJuYZYModel::getShunXuHao));
                // 获取最大值
                int maxShunXuHao = maxModel.map(SC_ZD_ShuJuYZYModel::getShunXuHao).orElse(0);
                var zhuYuID=sequenceService.getXuHao("SC_ZD_ShuJuYZY_ZiYuID", 7);
                SC_ZD_ShuJuYZYModel shuJuYZYDto=new SC_ZD_ShuJuYZYModel();
                shuJuYZYDto.setShuJuYLBID(shuJuYLBDto.getShuJuYLBID());
                shuJuYZYDto.setShuJuYLBMC(shuJuYLBDto.getShuJuYLBMC());
                shuJuYZYDto.setZhiYuMC(dto.getZhiXingPLMC());
                shuJuYZYDto.setZhiYuID(zhuYuID);
                shuJuYZYDto.setBiaoZhunDM(zhuYuID);
                shuJuYZYDto.setBiaoZhunMC(dto.getZhiXingPLMC());
                shuJuYZYDto.setShunXuHao(maxShunXuHao+1);
                shuJuYZYRepository.save(shuJuYZYDto);
                zhiYuID=shuJuYZYDto.getZhiYuID();
            }else{
                throw new TongYongYWException("执行频率数据源类别不存在！");
            }

        return zhiYuID;

        //endregion
    }

    /**
     * 保存执行信息
     * @param id
     * @return
     */
    @Override
    public Boolean saveZhiXingRZ(String id) throws TongYongYWException {
        var jiBenxx=jiBenXXRepository.findById(id).orElse(null);
        var zhixingkssj=new Date();
        if (Objects.isNull(jiBenxx)){
            throw new TongYongYWException("该任务基本信息不存在！");
        }

        var entity = BeanUtil.copyProperties(jiBenxx, SC_RW_ZhiXingRZModel::new, (s, t) -> {

            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZhiXingKSSJ(zhixingkssj);

            t.setRuCan(jiBenxx.getRenWuCS());

        });
        zhiXingRZRepository.save(entity);
        return true;
    }

    @Override
    public Boolean saveZhiXingRZList(List<SC_RW_ZhiXingRZCreateDto> creatDto){
        var renWuIDs=creatDto.stream().map(SC_RW_ZhiXingRZCreateDto::getRenWuID).toList();
        var zhixingkssj=new Date();

        var jiBenXXList=jiBenXXRepository.asQuerydsl().where(t->t.renWuID.in(renWuIDs)).fetch();
        var entity = BeanUtil.copyProperties(jiBenXXList, SC_RW_ZhiXingRZModel::new, (s, t) -> {
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZhiXingKSSJ(zhixingkssj);
            var item=creatDto.stream().filter(q->Objects.equals(q.getRenWuID(),t.getRenWuID())).findFirst();
            t.setRuCan(item.get().getRuCan());
        });
        zhiXingRZRepository.save(entity);
        return true;
    }


}
