package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.aqua.scheduler.api.SchedulerService;
import cn.mediinfo.cyan.aqua.scheduler.api.SchedulerTriggerService;


import cn.mediinfo.cyan.aqua.scheduler.api.contanst.ConCurrent;
import cn.mediinfo.cyan.aqua.scheduler.api.contanst.MisFirePolicy;
import cn.mediinfo.cyan.aqua.scheduler.api.contanst.TriggerType;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.job.ScheduleCurrentServiceJobCreateDto;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.job.ScheduleCurrentServiceJobUpdateDto;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.trigger.TriggerCreateDto;
import cn.mediinfo.cyan.aqua.scheduler.api.dto.trigger.TriggerUpdateDto;import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.http.ContentType;
import cn.mediinfo.cyan.msf.core.http.HttpMethod;
import cn.mediinfo.cyan.msf.core.http.HttpService;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.DateUtil;
import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.security.IdentityService;
import cn.mediinfo.grus.shujuzx.dto.renwugls.*;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.TreeDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.po.renwugl.JiBenXXAndTongYongPZPO;
import cn.mediinfo.grus.shujuzx.po.renwugl.TongYongPZPO;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.GY_ZD_ShuJuYuanTreeRso;
import cn.mediinfo.grus.shujuzx.remoteservice.GongYongRemoteService;
import cn.mediinfo.grus.shujuzx.repository.*;
import cn.mediinfo.grus.shujuzx.service.RenWuGLService;
import cn.mediinfo.grus.shujuzx.service.ShuJuYZYService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import com.querydsl.core.types.Expression;
import jakarta.transaction.Transactional;
import okhttp3.Response;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Service
public class RenWuGLServiceImpl implements RenWuGLService {
    private final SequenceService sequenceService;
    private final SC_RW_JiBenXXRepository jiBenXXRepository;
    private final SC_RW_ZhiXingRZRepository zhiXingRZRepository;
    private final SC_RW_ShuJuYuanRepository shuJuYuanRepository;
    private final SC_RW_TongYongPZRepository tongYongPZRepository;
    private final SC_ZD_ShuJuYZYRepository shuJuYZYRepository;
    private final SC_ZD_ShuJuYLBRepository shuJuYLBRepository;
    private final GongYongRemoteService gongYongRemoteService;
    private final SchedulerService schedulerService;
    private final SchedulerTriggerService schedulerTriggerService;
    private final HttpService httpService;
    private final ShuJuYZYService shuJuYZYService;
    private final Environment environment;
    private final IdentityService identityService;
    private final LyraIdentityService lyraIdentityService;

    public RenWuGLServiceImpl(SequenceService sequenceService, SC_RW_JiBenXXRepository jiBenXXRepository,
                              SC_RW_ZhiXingRZRepository zhiXingRZRepository, SC_RW_ShuJuYuanRepository shuJuYuanRepository, SC_RW_TongYongPZRepository tongYongPZRepository,
                              SC_ZD_ShuJuYZYRepository shuJuYZYRepository, SC_ZD_ShuJuYLBRepository shuJuYLBRepository, GongYongRemoteService gongYongRemoteService,
                              SchedulerService schedulerService, SchedulerTriggerService schedulerTriggerService, HttpService httpService,
                              ShuJuYZYService shuJuYZYService, Environment environment, IdentityService identityService, LyraIdentityService lyraIdentityService) {
        this.jiBenXXRepository = jiBenXXRepository;
        this.sequenceService = sequenceService;
        this.zhiXingRZRepository = zhiXingRZRepository;
        this.shuJuYuanRepository = shuJuYuanRepository;
        this.tongYongPZRepository = tongYongPZRepository;
        this.shuJuYZYRepository = shuJuYZYRepository;
        this.shuJuYLBRepository = shuJuYLBRepository;
        this.gongYongRemoteService = gongYongRemoteService;
        this.schedulerService = schedulerService;
        this.schedulerTriggerService = schedulerTriggerService;
        this.httpService = httpService;
        this.shuJuYZYService=shuJuYZYService;
        this.environment = environment;
        this.identityService = identityService;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 获取列表
     *
     * @param likeQuery
     * @param fenLeiDM
     * @param qiYongBZ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_RW_JiBenXXListDto> getJiBenXXList(String likeQuery, String fenLeiDM, Integer qiYongBZ, Integer pageIndex, Integer pageSize) {

        var result = jiBenXXRepository.asQuerydsl()
                .whereIf(StringUtil.hasText(likeQuery), t ->t.renWuMC.lower().contains(likeQuery.toLowerCase()).or(t.renWuSM.contains(likeQuery)))
                .whereIf(StringUtil.hasText(fenLeiDM), t -> t.fenLeiDM.eq(fenLeiDM))
                .whereIf(Objects.equals(qiYongBZ, 1), t -> t.qiYongBZ.eq(qiYongBZ))
                .leftJoin(tongYongPZRepository.asQuerydsl(), (jiBenXX, tongYongPZ) -> jiBenXX.fenLeiDM.eq(tongYongPZ.fenLeiDM), JiBenXXAndTongYongPZPO::new)
                .orderBy(t->t.jiBenXX().shunXuHao.asc())
                .select(q -> new Expression[]{
                                q.jiBenXX().id,
                                q.jiBenXX().renWuID,
                                q.jiBenXX().renWuMC,
                                q.jiBenXX().renWuSM,
                                q.jiBenXX().fenLeiMC,
                                q.jiBenXX().zhiXingPLMC,
                                q.jiBenXX().zhiXingZTMC,
                                q.jiBenXX().zhiXingZTDM,
                                q.jiBenXX().beiZhu,
                                q.jiBenXX().zuiJinZXRZID,
                                q.jiBenXX().zhiXingHS,
                                q.jiBenXX().qiYongBZ,
                                q.jiBenXX().zhiXingKSSJ.as("zuiJinZXSJ"),
                                q.jiBenXX().renWuDZ,
                                q.tongYongPZ().fuWuQIP,
                                q.tongYongPZ().fuWuQDK,
                                q.tongYongPZ().renWuDZ.as("fuWuQRWDZ")
                                // ,q.tongYongPZ().fuWuQIP.concat(":").concat(q.tongYongPZ().fuWuQDK).concat("/spoon/spoon?file=/opt/kettle").concat(q.jiBenXX().renWuDZ).as("sheJiQDZ")
                        }
                        , SC_RW_JiBenXXListDto.class).fetchPage(pageIndex, pageSize);
        var renWuIDs = result.stream().map(SC_RW_JiBenXXListDto::getRenWuID).toList();
        var shuJuYuanDtos = shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.in(renWuIDs)).fetch();
        result.forEach(item -> {
            if (!Objects.isNull(item.getFuWuQIP()) && !Objects.isNull(item.getFuWuQDK())
            && !Objects.equals(item.getFuWuQIP(),"") && !Objects.equals(item.getFuWuQDK(),"")){
                var IP=item.getFuWuQIP().startsWith("http://")? item.getFuWuQIP():"http://"+item.getFuWuQIP();

                var shejidz =IP.concat(":").concat(item.getFuWuQDK()).concat("/spoon/spoon?file=/opt/kettle");
                if (Objects.isNull(item.getRenWuDZ()) || Objects.equals(item.getRenWuDZ(),"")) {
                    if(!Objects.isNull(item.getFuWuQRWDZ()) && !Objects.equals(item.getFuWuQRWDZ(),"")){
                        shejidz = shejidz.concat(item.getFuWuQRWDZ()).concat("/").concat(item.getRenWuMC()).concat(".kjb");
                    }else{
                        shejidz="";
                    }
                }else{
                    shejidz=shejidz.concat(item.getRenWuDZ());
                }
                item.setSheJiQDZ(shejidz);
            }
            var shuJuYuanList = shuJuYuanDtos.stream().filter(t -> Objects.equals(t.getRenWuID(), item.getRenWuID())).toList();
            if (shuJuYuanList.size() > 0) {
                var maxYeWuZXSJ = shuJuYuanList.stream().sorted(Comparator.comparing(SC_RW_ShuJuYuanModel::getYeWuZXSJ, Comparator.nullsLast(Comparator.reverseOrder()))).findFirst().orElse(null);
                item.setYeWuZXSJ(maxYeWuZXSJ.getYeWuZXSJ());
            }
        });
        return result;
    }

    /**
     * 获取列表数量
     *
     * @param likeQuery
     * @param fenLeiDM
     * @param qiYongBZ
     * @return
     */
    @Override
    public long getJiBenXXCount(String likeQuery, String fenLeiDM, Integer qiYongBZ) {
        return jiBenXXRepository.asQuerydsl()
                .whereIf(StringUtil.hasText(likeQuery), t ->t.renWuMC.lower().contains(likeQuery.toLowerCase()).or(t.renWuSM.lower().contains(likeQuery.toLowerCase())))
                .whereIf(StringUtil.hasText(fenLeiDM), t -> t.fenLeiDM.eq(fenLeiDM))
                .whereIf(Objects.equals(qiYongBZ, 1), t -> t.qiYongBZ.eq(qiYongBZ)).count();
    }

    @Override
    public SC_RW_JiBenXXDto getRenWuXXById(String id) throws TongYongYWException {
        var result = jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(result)) {
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        return BeanUtil.copyProperties(result, SC_RW_JiBenXXDto::new);
    }

    /**
     * 根据ID查询任务基本信息
     *
     * @param ids
     * @return
     * @throws TongYongYWException
     */
    @Override
    public JiBenXXListDto getRenWuXXByIds(List<String> ids) throws TongYongYWException {
        JiBenXXListDto result = new JiBenXXListDto();
        var entity = jiBenXXRepository.findAllById(ids);
        if (entity.size() == 0) {
            throw new TongYongYWException("该任务基本信息不存在！");
        }
        List<String> canShuKeys = new ArrayList<>();
        entity.forEach(item -> {
            if (!Objects.isNull(item.getRenWuCS()) && !item.getRenWuCS().isEmpty()) {
                Map<String,String> map=JacksonUtil.getJsonToMap(item.getRenWuCS(),String.class,String.class);
                Set<String> keys = map.keySet();
                canShuKeys.addAll(keys);
            }
        });
        result.setJiBenXXDtoList(BeanUtil.copyListProperties(entity, SC_RW_JiBenXXDto::new));
        result.setCanShuKeys(canShuKeys.stream().distinct().toList());
        return result;
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
        var id=schedulerService.trigger().getTriggerName();
        //查询是否存在
        var shiFouCZ = jiBenXXRepository.existsByRenWuMC(createDto.getRenWuMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("该任务名称已存在:" + createDto.getRenWuMC() + "!");
        }
        if (!StringUtil.hasText(createDto.getZhiXingPLDM()) && StringUtil.hasText(createDto.getZhiXingPLMC())) {
            var dto = BeanUtil.copyProperties(createDto, SC_RW_JiBenXXDto::new);
            createDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }
        var entity = BeanUtil.copyProperties(createDto, SC_RW_JiBenXXModel::new, (s, t) -> {
            t.setRenWuID(sequenceService.getXuHao("SC_RW_JiBenXX_RenWuID", 9));
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setChuFaQTBZTID(id);
            if (Objects.isNull(createDto.getShunXuHao())){
                // 获取shuJuYZYList集合中的SC_RW_JiBenXXModel对象中的shunXuHao的最大值
                List<SC_RW_JiBenXXModel> maxModel=jiBenXXRepository.findAll().stream().sorted(Comparator.comparing(SC_RW_JiBenXXModel::getShunXuHao,Comparator.nullsLast(Comparator.reverseOrder()))).toList();
               if(maxModel.size()>0){
                   int maxShunXuHao = maxModel.stream().findFirst().map(SC_RW_JiBenXXModel::getShunXuHao).orElse(0);
                   t.setShunXuHao(maxShunXuHao+1);
               }
                // 获取最大值
            }

        });
        jiBenXXRepository.save(entity);
   //作业和触发器同步到中台
        createJob(createDto.getRenWuMC());
        if(StringUtil.hasText(createDto.getZhiXingPLDM())){
            tongBuTrigger(createDto.getRenWuMC(),id,createDto.getZhiXingPLDM());
        }
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
        if (!StringUtil.hasText(updateDto.getZhiXingPLDM()) && StringUtil.hasText(updateDto.getZhiXingPLMC())) {
            var dto = BeanUtil.copyProperties(updateDto, SC_RW_JiBenXXDto::new);
            updateDto.setZhiXingPLDM(saveZhiXingPZZD(dto));
        }
        var oldRenWuMC=entity.getRenWuMC();
        var oldCorn=entity.getZhiXingPLDM();
        //把dto赋值到entity
        BeanUtil.mergeProperties(updateDto, entity,true);
        jiBenXXRepository.save(entity);
       //同步中台job和触发器
        updateJob(updateDto.getRenWuMC(),oldRenWuMC);
        if(StringUtil.hasText(updateDto.getZhiXingPLDM()) && StringUtil.hasText(oldCorn) && !updateDto.getZhiXingPLDM().equals(oldCorn)){
            tongBuTrigger(updateDto.getRenWuMC(),entity.getChuFaQTBZTID(),updateDto.getZhiXingPLDM());
        }
        return true;
    }

    /**
     * 作废任务
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiRenWuJBXX(String id) throws TongYongYWException {
        var entity = jiBenXXRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该任务不存在!");
        }
        //删除数据源值域
        jiBenXXRepository.delete(entity);
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
     *   创建job
     *
     */
    public String createJob(String renWuMC) {
        String url=environment.getProperty("mediinfo.remote.urls.mediinfo-lyra-gongyong")+"/mediinfo-grus-shujuzx/api/v1.0/RenWuDD/zhiXingRW?renWuMC="+renWuMC;
        //创建job
        var createModel = ScheduleCurrentServiceJobCreateDto
                .builder()
                //.fuWuMC(serviceName) //不指定则默认使用当前服务的applicationName
                .jobName(renWuMC)
                .jobGroup("mediinfo-grus-shujuzx")
                .requestUrl(url)
                .httpMethod(HttpMethod.GET)
                .concurrent(ConCurrent.YES)
                .build();
       return schedulerService.job().create(createModel);
    }

    /**
     *   更新job
     *
     */
    public String updateJob(String newRenWuMC,String oldRenWuMC) throws TongYongYWException {
        String url=environment.getProperty("mediinfo.remote.urls.mediinfo-lyra-gongyong")+"/mediinfo-grus-shujuzx/api/v1.0/RenWuDD/zhiXingRW?renWuMC="+newRenWuMC;
        //创建job
        var updateModel = ScheduleCurrentServiceJobUpdateDto
                .builder()
                //.fuWuMC(serviceName) //不指定则默认使用当前服务的applicationName
                .jobName(newRenWuMC)
                .jobGroup("mediinfo-grus-shujuzx")
                .oldJobGroup("mediinfo-grus-shujuzx")
                .oldJobName(oldRenWuMC)
                .requestUrl(url)
                .httpMethod(HttpMethod.GET)
                .concurrent(ConCurrent.YES)
                .build();
       return schedulerService.job().update(updateModel);
    }



    /**
     *   同步触发器
     *
     */
    public void tongBuTrigger(String renWuMC,String id, String cron) throws TongYongYWException {
        //根据触发器id和分组获取触发器详情
        var response= schedulerTriggerService.get(id, "mediinfo-grus-shujuzx");
        if(response!=null){
            var updateDto= TriggerUpdateDto
                    .builder()
                    .tenantId(identityService.getTenantId())
                    .tenantName(identityService.getTenantName())
                    .misFirePolicy(MisFirePolicy.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)
                    .triggerName(id)
                    .triggerNameText(renWuMC+"_触发器")
                    .triggerType(TriggerType.CRON)
                    .cron(cron)
                    .build();
            schedulerTriggerService.update(renWuMC,"GRUS_SC",updateDto);
        }
        else{
            var createDto= TriggerCreateDto
                    .builder()
                    .tenantId(identityService.getTenantId())
                    .tenantName(identityService.getTenantName())
                    .misFirePolicy(MisFirePolicy.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY)
                    .triggerNameText(renWuMC+"_触发器")
                    .triggerName(id)
                    .triggerType(TriggerType.CRON)
                    .cron(cron)
                    .build();
            schedulerService.trigger().create(renWuMC,"mediinfo-grus-shujuzx",createDto);
        }
    }

    /* 根据id查询日志详情
     *
     * @param id
     * @return
     */
    @Override
    public SC_RW_ZhiXingRZDto getZhiXingRZDto(String id) {
        var entity = zhiXingRZRepository.findById(id).orElse(null);
//        if (entity != null && entity.getRuCan()!=null) {
//            entity.setRuCan(getJsonStr(entity.getRuCan()));
//        }
        return BeanUtil.copyProperties(entity, SC_RW_ZhiXingRZDto::new);
    }

    /**
     * 获取列表
     *
     * @param renWuID 任务id
     * @param zhiXingKSSJ
     * @param zhiXingJSSJ
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<SC_RW_ZhiXingRZListDto> getZhiXingRZList(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ, Integer pageIndex, Integer pageSize) {
        return zhiXingRZRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID).and(t.zhiXingSJ.after(zhiXingKSSJ)).and(t.zhiXingSJ.before(zhiXingJSSJ)))
                .orderBy(t -> t.zhiXingSJ.desc())
                .select(SC_RW_ZhiXingRZListDto.class)
                .fetchPage(pageIndex, pageSize);
    }

    @Override
    public Long getZhiXingRZCount(String renWuID, Date zhiXingKSSJ, Date zhiXingJSSJ) {
        return zhiXingRZRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID).and(t.zhiXingSJ.after(zhiXingKSSJ)).and(t.zhiXingSJ.before(zhiXingJSSJ)))
                .select(SC_RW_ZhiXingRZListDto.class)
                .count();
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
        List<GY_ZD_ShuJuYuanTreeRso> ShuJuYuanTree = gongYongRemoteService.getShuJuYFLTree().getData();
        List<GY_ZD_ShuJuYuanTreeRso> treeChild = new ArrayList<>();
        ShuJuYuanTree.forEach(item -> {
            //var array=  Stream.concat(item.getChildren().stream(), treeChild.stream()).toList();
            treeChild.addAll(Stream.concat(item.getChildren().stream(), treeChild.stream()).toList());
        });
        var result = shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(renWuID)).fetch();
        var entity = BeanUtil.copyListProperties(result, SC_RW_ShuJuYuanDto::new, (s, t) -> {
            var entityChild = treeChild.stream().anyMatch(q -> Objects.equals(q.getId(), t.getShuJuYID()));
            if (entityChild){
                var itemchild=treeChild.stream().filter(q -> Objects.equals(q.getId(), t.getShuJuYID())).findFirst().get();
                t.setChangShangMC(itemchild.getChangShangMC());
                t.setXiTongMC(itemchild.getXiTongMC());
            }

        });

        return entity;
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
        var renWuDto = jiBenXXRepository.findFirstByRenWuID(createDto.getRenWuID());
        if (renWuDto == null) {
            throw new TongYongYWException("当前任务基本信息不存在！");
        }
        var shuJuYuanList = shuJuYuanRepository.asQuerydsl().where(t -> t.renWuID.eq(createDto.getRenWuID())).fetch();
        var shuJuYIDs = shuJuYuanList.stream().map(SC_RW_ShuJuYuanModel::getShuJuYID).toList();
        var createShuJuYIDs = createDto.getShuJuYuanDtoList().stream().map(SC_RW_ShuJuYuanDto::getShuJuYID).filter(shuJuYID -> !Objects.isNull(shuJuYID)).toList();
        //求差值
        List<String> addIDList = new ArrayList<String>(createShuJuYIDs);
        List<String> delIDList = new ArrayList<String>(shuJuYIDs);
        addIDList.removeAll(shuJuYIDs);
        delIDList.removeAll(createShuJuYIDs);
        //需新增的数据源
        List<SC_RW_ShuJuYuanDto> addList = createDto.getShuJuYuanDtoList().stream().filter(t -> addIDList.contains(t.getShuJuYID())).toList();
        var entity = BeanUtil.copyListProperties(addList, SC_RW_ShuJuYuanModel::new, (s, t) -> {
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setRenWuID(renWuDto.getRenWuID());
            t.setRenWuMC(renWuDto.getRenWuMC());
        });

        //需要修改的数据源
        var updateIds = new ArrayList<String>(shuJuYIDs);
        updateIds.removeAll(addIDList);
        updateIds.removeAll(delIDList);
        List<SC_RW_ShuJuYuanModel> updateDtos = new ArrayList<>();
        List<SC_RW_ShuJuYuanDto> updateList = new ArrayList<>(createDto.getShuJuYuanDtoList().stream().filter(t -> updateIds.contains(t.getShuJuYID())).toList());
        updateList.forEach(item -> {
            var shujuyItem = shuJuYuanList.stream().filter(t -> Objects.equals(item.getShuJuYID(), t.getShuJuYID())).findFirst().orElse(null);
            if (shujuyItem != null && !Objects.equals(shujuyItem.getQiYongBZ(), item.getQiYongBZ())) {
                BeanUtil.mergeProperties(item, shujuyItem);
                updateDtos.add(shujuyItem);
            }
        });

        entity.addAll(updateDtos);
        shuJuYuanRepository.saveAll(entity);
        //需删除的数据源
        shuJuYuanRepository.asDeleteDsl().where(t -> t.shuJuYID.in(delIDList)).execute();

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
                .where(q -> q.shuJuYLBID.eq("SC0021"))
                .orderBy(q -> q.shunXuHao.asc())
                .leftJoin(tongYongPZRepository.asQuerydsl(), (shuJuYZY, tongYongPZ) -> shuJuYZY.zhiYuID.eq(tongYongPZ.fenLeiDM), TongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                        q.tongYongPZ().zuZhiJGID,
                        q.tongYongPZ().zuZhiJGMC,
                        q.tongYongPZ().renWuDZ,
                        q.tongYongPZ().fuWuQIP,
                        q.tongYongPZ().id,
                        q.tongYongPZ().fuWuQDK,
                        q.shuJuYZY().zhiYuID.as("fenLeiDM"),
                        q.shuJuYZY().zhiYuMC.as("fenLeiMC")
                }, SC_RW_TongYongPZDto.class).fetch();

    }

    /**
     * 保存通用配置
     *
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
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
        });
        tongYongPZRepository.saveAll(addEntity);
        //修改
        var updateDto = creatDto.stream().filter(t -> !Objects.isNull(t.getId())).toList();
        var ids = updateDto.stream().map(SC_RW_TongYongPZDto::getId).toList();
        var tongYongPZList = tongYongPZRepository.asQuerydsl().where(t -> t.id.in(ids)).fetch();
        for (var item : tongYongPZList) {
            var tongYongPZItem = updateDto.stream().filter(q -> Objects.equals(q.getId(), item.getId())).findFirst().orElse(null);
            BeanUtil.mergeProperties(tongYongPZItem, item, true);
        }

        tongYongPZRepository.saveAll(tongYongPZList);
        return true;
    }

    /**
     * 保存执行频率字典
     *
     * @param dto
     * @return
     * @throws TongYongYWException
     */
    public String saveZhiXingPZZD(SC_RW_JiBenXXDto dto) throws TongYongYWException {
        String zhiYuID = "";
        //region 判断执行频率是否存在 不存在的话 新增到值域表

        var shuJuYLBDto = shuJuYLBRepository.asQuerydsl().where(t -> t.shuJuYLBID.eq("SC0018")).fetchOne();
        if (!Objects.isNull(shuJuYLBDto)) {
            //查询最大的顺序号
            var shuJuYZYList = shuJuYZYRepository.asQuerydsl().where(t -> t.shuJuYLBID.eq("SC0018")).select(SC_ZD_ShuJuYZYModel.class).fetch();
            // 获取shuJuYZYList集合中的SC_ZD_ShuJuYZYModel对象中的shunXuHao的最大值
            Optional<SC_ZD_ShuJuYZYModel> maxModel = shuJuYZYList.stream()
                    .max(Comparator.comparing(SC_ZD_ShuJuYZYModel::getShunXuHao));
            // 获取最大值
            int maxShunXuHao = maxModel.map(SC_ZD_ShuJuYZYModel::getShunXuHao).orElse(0);
            var zhuYuID = sequenceService.getXuHao("SC_ZD_ShuJuYZY_ZhiYuID", 7);
            SC_ZD_ShuJuYZYModel shuJuYZYDto = new SC_ZD_ShuJuYZYModel();
            shuJuYZYDto.setZuZhiJGID(lyraIdentityService.getJiGouID());
            shuJuYZYDto.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            shuJuYZYDto.setShuJuYLBID(shuJuYLBDto.getShuJuYLBID());
            shuJuYZYDto.setShuJuYLBMC(shuJuYLBDto.getShuJuYLBMC());
            shuJuYZYDto.setZhiYuMC(dto.getZhiXingPLMC());
            shuJuYZYDto.setMoJiBZ(1);
            shuJuYZYDto.setZhiYuID(zhuYuID);
            shuJuYZYDto.setBiaoZhunDM(zhuYuID);
            shuJuYZYDto.setBiaoZhunMC(dto.getZhiXingPLMC());
            shuJuYZYDto.setShunXuHao(maxShunXuHao + 1);
            shuJuYZYRepository.save(shuJuYZYDto);
            zhiYuID = shuJuYZYDto.getZhiYuID();
        } else {
            throw new TongYongYWException("执行频率数据源类别不存在！");
        }

        return zhiYuID;

        //endregion
    }

    /**
     * 根据分类代码查询通用配置默认任务地址
     *
     * @param fenLeiDM
     * @return
     */
    @Override
    public SC_RW_TongYongPZDto getTongYongPZByFLDM(String fenLeiDM) {
        return shuJuYZYRepository.asQuerydsl()
                .where(q -> q.shuJuYLBID.eq("SC0021").and(q.zhiYuID.eq(fenLeiDM)))
                .orderBy(q -> q.shunXuHao.asc())
                .leftJoin(tongYongPZRepository.asQuerydsl(), (shuJuYZY, tongYongPZ) -> shuJuYZY.zhiYuID.eq(tongYongPZ.fenLeiDM), TongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                        q.tongYongPZ().zuZhiJGID,
                        q.tongYongPZ().zuZhiJGMC,
                        q.tongYongPZ().renWuDZ,
                        q.tongYongPZ().fuWuQIP,
                        //q.tongYongPZ().fuWuQIP.concat(":").concat(q.tongYongPZ().fuWuQDK).concat(q.tongYongPZ().renWuDZ).as("renWuDZ"),
                        q.tongYongPZ().id,
                        q.tongYongPZ().fuWuQDK,
                        q.shuJuYZY().zhiYuID.as("fenLeiDM"),
                        q.shuJuYZY().zhiYuMC.as("fenLeiMC")
                }, SC_RW_TongYongPZDto.class).fetchOne();

    }

    /**
     * 批量执行
     *
     * @param creatDto
     * @return
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String saveRenWuZXList(List<SC_RW_ZhiXingRZCreateDto> creatDto) {
        var renWuIDs = creatDto.stream().map(SC_RW_ZhiXingRZCreateDto::getRenWuID).toList();
        var zhixingsj = new Date();
        var jiBenXXList = jiBenXXRepository.asQuerydsl()
                .where(t -> t.renWuID.in(renWuIDs))
                .leftJoin(tongYongPZRepository.asQuerydsl(), (jiBenXX, tongYongPZ) -> jiBenXX.fenLeiDM.eq(tongYongPZ.fenLeiDM), JiBenXXAndTongYongPZPO::new)
                .select(q -> new Expression<?>[]{
                                q.jiBenXX().id,
                                q.jiBenXX().renWuID,
                                q.jiBenXX().renWuMC,
                                q.jiBenXX().renWuSM,
                                q.jiBenXX().fenLeiMC,
                                q.jiBenXX().zhiXingPLMC,
                                q.jiBenXX().zhiXingZTMC,
                                q.jiBenXX().zhiXingZTDM,
                                q.jiBenXX().beiZhu,
                                q.jiBenXX().zuiJinZXRZID,
                                q.jiBenXX().zhiXingHS,
                                q.jiBenXX().qiYongBZ,
                                q.jiBenXX().renWuDZ,
                                q.tongYongPZ().fuWuQIP,
                                q.tongYongPZ().fuWuQDK,
                                q.tongYongPZ().renWuDZ.as("fuWuQRWDZ")
                        }
                        , SC_RW_JiBenXXListDto.class).fetch();
        StringBuilder resultStr = new StringBuilder();
        //循环任务基本信息 单条执行
        jiBenXXList.forEach(jbxxItem -> {
            var renwudz=(!Objects.isNull(jbxxItem.getRenWuDZ())&& !jbxxItem.getRenWuDZ().isEmpty())?jbxxItem.getRenWuDZ(): jbxxItem.getFuWuQRWDZ();
            if (!Objects.isNull(jbxxItem.getFuWuQIP()) && !Objects.isNull(jbxxItem.getFuWuQDK()) && !jbxxItem.getFuWuQIP().isEmpty() && !jbxxItem.getFuWuQDK().isEmpty() && !renwudz.isEmpty()
            ) {
                SC_RW_ZhiXingRZCreateDto item = creatDto.stream().filter(q -> Objects.equals(q.getRenWuID(), jbxxItem.getRenWuID())).findFirst().get();
                SC_RW_ZhiXingRZModel zhiXingRZEntity = new SC_RW_ZhiXingRZModel();
                zhiXingRZEntity.setRenWuID(jbxxItem.getRenWuID());
                zhiXingRZEntity.setRenWuMC(jbxxItem.getRenWuMC());
                zhiXingRZEntity.setZhiXingKSSJ(zhixingsj);
                zhiXingRZEntity.setZhiXingSJ(zhixingsj);
                zhiXingRZEntity.setZuZhiJGID(lyraIdentityService.getJiGouID());
                zhiXingRZEntity.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
                zhiXingRZEntity.setRuCan(item.getRuCan());
                zhiXingRZEntity.setZhiXingRID(lyraIdentityService.getYongHuId());
                zhiXingRZEntity.setZhiXingRXM(lyraIdentityService.getUserName());
                zhiXingRZEntity.setZhiXingZTDM("0");
                zhiXingRZEntity.setZhiXingZTMC("等待");
                zhiXingRZEntity.setZhiXingZTDM("0");
                zhiXingRZEntity.setZhiXingFSDM(item.getZhiXingfFSDM());
                zhiXingRZEntity.setZhiXingFSMC(item.getZhiXingfFSMC());
                zhiXingRZRepository.save(zhiXingRZEntity);
                try {
                    resultStr.append(saveRenWuZX(zhiXingRZEntity, jbxxItem));
                } catch (Exception e) {
                    //region 调度失败 基本信息与执行记录的【执行状态】字段更改
                    resultStr.append(jbxxItem.getRenWuMC()).append("执行失败|");
                    var zhixingjssj = new Date();
                    var zhixingkssj = zhiXingRZEntity.getZhiXingKSSJ();
                    var zhixinghs = DateUtil.getSecondsBetween(zhixingkssj, zhixingjssj);
                    zhiXingRZRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "2")
                            .set(t -> t.zhiXingZTMC, "错误")
                            .set(t -> t.zhiXingKSSJ, zhixingkssj)
                            .set(t -> t.zhiXingJSSJ, zhixingjssj)
                            .set(t -> t.zhiXingHS, zhixinghs)
                            .where(t -> t.id.eq(zhiXingRZEntity.getId())).execute();
                    jiBenXXRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "2")
                            .set(t -> t.zhiXingZTMC, "错误")
                            .set(t -> t.zuiJinZXRZID, zhiXingRZEntity.getId())
                            .set(t -> t.zhiXingKSSJ, zhixingkssj)
                            .set(t -> t.zhiXingJSSJ, zhixingjssj)
                            .set(t -> t.zhiXingHS, zhixinghs)
                            .where(t -> t.id.eq(jbxxItem.getId())).execute();
                    //endregion
                }

            } else {
                resultStr.append(jbxxItem.getRenWuMC()).append("地址为空|");
            }
        });

        return resultStr.toString();
    }

    /**
     * ETL调度及执行状态代码调整
     *
     * @param createDto
     * @param renWUXXDto
     * @return
     * @throws Exception
     */
    @Transactional(rollbackOn = Exception.class)
    public String saveRenWuZX(SC_RW_ZhiXingRZModel createDto, SC_RW_JiBenXXListDto renWUXXDto) throws IOException {
        var zhiXingRZID=createDto.getId();
        //返回值
        StringBuilder resultStr = new StringBuilder();
        //参数
        StringBuilder canShuStr = new StringBuilder();
        //拼接参数
        if (!Objects.isNull(createDto.getRuCan())) {
            Map<String, String> map= JacksonUtil.getJsonToMap(createDto.getRuCan(),String.class,String.class);
            Set<String> keys = map.keySet();
            keys.forEach(item -> {
                canShuStr.append("&").append(item).append("=").append(map.get(item));
            });
        }
        //url拼接
        var IP=renWUXXDto.getFuWuQIP().startsWith("http://")?renWUXXDto.getFuWuQIP():"http://"+renWUXXDto.getFuWuQIP();
        String url=IP.concat(":").concat(renWUXXDto.getFuWuQDK()).concat("/spoon/kettle/executeJob?job=/opt/kettle").concat(renWUXXDto.getRenWuDZ())+ "&renWuID=" + renWUXXDto.getRenWuID() + "&zhiXingRZID=" + zhiXingRZID + canShuStr;
        if (Objects.isNull(renWUXXDto.getRenWuDZ()) || Objects.equals(renWUXXDto.getRenWuDZ(),"")){
            url =IP.concat(":").concat(renWUXXDto.getFuWuQDK()).concat("/spoon/kettle/executeJob?job=/opt/kettle").concat(renWUXXDto.getFuWuQRWDZ()).concat("/").concat(renWUXXDto.getRenWuMC()).concat(".kjb")+ "&renWuID=" + renWUXXDto.getRenWuID() + "&zhiXingRZID=" + zhiXingRZID + canShuStr;
        }
        //region  设置ContentType，获取xml返回值
        Map<String,String> header=Map.of("Content-Type", ContentType.XML_DATA_FORMAT.getValue());
        Response response=httpService.get(url,header);
        String result = null;
        if (response.body() != null) {
            result = response.body().string();
        }
        //xml字符串转节点
       var jsonNodes= JacksonUtil.getXmlToJsonNode(result);

        //判断
        if (Objects.equals(jsonNodes.get("result").asText(), "ERROR")) {
            resultStr.append(renWUXXDto.getRenWuMC()).append("执行失败|");
            var zhixingjssj = new Date();
            var zhixingkssj = createDto.getZhiXingKSSJ();
            var zhixinghs = DateUtil.getSecondsBetween(zhixingkssj, zhixingjssj);
            zhiXingRZRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "2")
                    .set(t -> t.zhiXingZTMC, "错误")
                    .set(t -> t.zhiXingKSSJ, zhixingjssj)
                    .set(t -> t.zhiXingJSSJ, zhixingjssj)
                    .set(t -> t.zhiXingHS, zhixinghs)
                    .where(t -> t.id.eq(zhiXingRZID)).execute();
            jiBenXXRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "2")
                    .set(t -> t.zhiXingZTMC, "错误")
                    .set(t -> t.zuiJinZXRZID, zhiXingRZID)
                    .set(t -> t.zhiXingKSSJ, zhixingjssj)
                    .set(t -> t.zhiXingJSSJ, zhixingjssj)
                    .set(t -> t.zhiXingHS, zhixinghs)
                    .where(t -> t.id.eq(renWUXXDto.getId())).execute();

        } else {
            zhiXingRZRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "3").set(t -> t.zhiXingZTMC, "正在执行").where(t -> t.id.eq(zhiXingRZID)).execute();
            jiBenXXRepository.asUpdateDsl().set(t -> t.zhiXingZTDM, "3").set(t -> t.zhiXingZTMC, "正在执行").set(t -> t.zuiJinZXRZID, zhiXingRZID).where(t -> t.id.eq(renWUXXDto.getId())).execute();
        }
        //endregion
        return resultStr.toString();
    }


    /**
     * 点击任务名称查看任务详情
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    public RenWuXQDto getRenWuXQ(String id) throws TongYongYWException {
        var entity=jiBenXXRepository.findById(id).orElse(null);
        if (Objects.isNull(entity)) {
            throw new TongYongYWException("该任务基本信息不存在");
        }
        var ShuJuYZYList=shuJuYZYRepository.findByShuJuYLBID("SC0021");
        //判断当前任务分类是否为最后一级
        String fenleimc=entity.getFenLeiMC();
        var shujuyzyDto=ShuJuYZYList.stream().filter(t->Objects.equals(t.getZhiYuID(),entity.getFenLeiDM())).findFirst().orElse(null);
        //判断当前分类是否存在父级
        if (shujuyzyDto!=null && !Objects.isNull(shujuyzyDto.getFuJiDM()) ){
            fenleimc=getFenLeiMC(shujuyzyDto.getFuJiDM(),fenleimc,ShuJuYZYList);
        }
        var tongyongpz=tongYongPZRepository.asQuerydsl().where(t->t.fenLeiDM.eq(entity.getFenLeiDM())).fetchFirst();
        String finalFenleimc = fenleimc;
        var result=BeanUtil.copyProperties(entity,RenWuXQDto::new,(s,t)->{
            if (!Objects.isNull(tongyongpz)){
                if (!Objects.isNull(tongyongpz.getFuWuQIP()) && !Objects.isNull(tongyongpz.getFuWuQDK()) && !Objects.isNull(tongyongpz.getRenWuDZ())){
                    var IP=tongyongpz.getFuWuQIP().startsWith("http://")? tongyongpz.getFuWuQIP():"http://"+tongyongpz.getFuWuQIP();
                    var renwudz=IP.concat(":").concat(tongyongpz.getFuWuQDK()).concat("/spoon/kettle/executeJob?job=/opt/kettle").concat(tongyongpz.getRenWuDZ()).concat("/").concat(s.getRenWuMC()).concat(".kjb");
                    if (!Objects.isNull(s.getRenWuDZ()) && !s.getRenWuDZ().isEmpty()){
                        renwudz=IP.concat(":").concat(tongyongpz.getFuWuQDK()).concat("/spoon/kettle/executeJob?job=/opt/kettle").concat(s.getRenWuDZ());
                    }
                    t.setRenWuDZ(renwudz);
                }
            }
            t.setFenLeiMC(finalFenleimc);
        });
        result.shuJuYuanDtoList=getShuJuYuanList(entity.getRenWuID());
        return result;
    }
    public String getFenLeiMC(String FenLeiDM,String fenleimc,List<SC_ZD_ShuJuYZYModel> shuJuYZYModels){
        var entity=shuJuYZYModels.stream().filter(t->Objects.equals(t.getZhiYuID(),FenLeiDM)).findFirst().orElse(null);
        if (!Objects.isNull(entity) ){
            //判断是否存在父级 存在并拼接
            fenleimc=entity.getZhiYuMC()+"-"+fenleimc;
            if (!Objects.isNull(entity.getFuJiDM()) && !Objects.equals(entity.getFuJiDM(),"")){
                fenleimc=getFenLeiMC(entity.getFuJiDM(),fenleimc,shuJuYZYModels);
            }
        }
        return fenleimc;
    }

    /**
     * 获取通用配置列表
     * @return
     */
    @Override
    public List<SC_RW_TongYongPZDto> getTongYongPZNew() {
        List<SC_RW_TongYongPZDto> result=new ArrayList<>();
        //获取数据源字典值域树结构
        var ShuJuYZDList=shuJuYZYService.getShuJuYZYTreeListByLBID("SC0021");
        //获取所有已保存的通用配置信息
        List<SC_RW_TongYongPZModel> tongYongPZModels=tongYongPZRepository.findAll();
        result=TongYongPZTree(ShuJuYZDList,result,tongYongPZModels);
        return result;
    }

    public List<SC_RW_TongYongPZDto> TongYongPZTree(List<TreeDto> treeDtos, List<SC_RW_TongYongPZDto> entity,List<SC_RW_TongYongPZModel> tongYongPZModels){
        //循环树
        treeDtos.forEach(item->{
            SC_RW_TongYongPZDto dto=new SC_RW_TongYongPZDto();
            var tongYongPZItem=tongYongPZModels.stream().filter(t->Objects.equals(t.getFenLeiDM(),item.value)).findFirst().orElse(null);
            if (!Objects.isNull(tongYongPZItem)){
                BeanUtil.mergeProperties(tongYongPZItem,dto);
            }else{
                dto.setFenLeiDM(item.getValue());
                dto.setFenLeiMC(item.getLabel());
            }
            List<SC_RW_TongYongPZDto> children=new ArrayList<>();
            //判断是否存在子集，且递归赋值
            if (!Objects.isNull(item.children)){
                dto.setChildren(TongYongPZTree(item.children,children,tongYongPZModels));
            }
            entity.add(dto);
        });
        return entity;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean saveTongYongPZNew(List<SC_RW_TongYongPZDto> creatDto) {
        //子集数据汇总到createDto中
        ChangeLevel(creatDto);
        //新增
        var addDto = creatDto.stream().filter(t -> Objects.isNull(t.getId()) && !Objects.isNull(t.getFuWuQIP()) && !Objects.isNull(t.getFuWuQDK())).toList();
        List<SC_RW_TongYongPZModel> tongYongPZModelList= BeanUtil.copyListProperties(addDto, SC_RW_TongYongPZModel::new, (s, t) -> {
            t.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            t.setZuZhiJGID(lyraIdentityService.getJiGouID());
        });

        //修改
        var updateDto = creatDto.stream().filter(t -> !Objects.isNull(t.getId())).toList();
        var ids = updateDto.stream().map(SC_RW_TongYongPZDto::getId).toList();
        var tongYongPZList = tongYongPZRepository.asQuerydsl().where(t -> t.id.in(ids)).fetch();
        for (var item : tongYongPZList) {
            var tongYongPZItem = updateDto.stream().filter(q -> Objects.equals(q.getId(), item.getId())).findFirst().orElse(null);
            BeanUtil.mergeProperties(tongYongPZItem, item);
        }
        tongYongPZModelList.addAll(tongYongPZList);
        if(tongYongPZModelList.size()>0){
            tongYongPZRepository.saveAll(tongYongPZModelList);
        }
        return true;
    }
    public List<SC_RW_TongYongPZDto> ChangeLevel(List<SC_RW_TongYongPZDto> dtos){
        List<SC_RW_TongYongPZDto> entity=new ArrayList<>();
        dtos.forEach(item->{
           if (!Objects.isNull(item.getChildren())){
             var children=ChangeLevel(item.getChildren());
               entity.addAll(children);
           }
        });
        dtos.addAll(entity);
        return dtos;
    }


}
