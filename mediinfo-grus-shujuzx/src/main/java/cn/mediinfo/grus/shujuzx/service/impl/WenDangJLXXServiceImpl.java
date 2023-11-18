package cn.mediinfo.grus.shujuzx.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.wendangjls.SC_GW_JiLuXXSCDto;
import cn.mediinfo.grus.shujuzx.model.*;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuNRRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_GW_JiLuXXRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangJLXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class WenDangJLXXServiceImpl implements WenDangJLXXService
{
    private final LyraIdentityService lyraIdentityService;
    private final SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository;
    private final SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository;
    private final StringGenerator stringGenerator;
    public WenDangJLXXServiceImpl (LyraIdentityService lyraIdentityService,SC_GW_JiLuNRRepository sc_gw_jiLuNRRepository,SC_GW_JiLuXXRepository sc_gw_jiLuXXRepository,StringGenerator stringGenerator){
        this.lyraIdentityService = lyraIdentityService;
        this.sc_gw_jiLuNRRepository = sc_gw_jiLuNRRepository;
        this.sc_gw_jiLuXXRepository = sc_gw_jiLuXXRepository;
        this.stringGenerator = stringGenerator;
    }

    /**
     * 保存文档信息
     * @param dto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean addWenDangJLXX(SC_GW_JiLuXXSCDto dto) throws TongYongYWException {
        if(ObjectUtils.isEmpty(dto) || dto.getJiLuXXList().isEmpty()){
            throw new TongYongYWException("文档信息不能为空！");
        }
        //删除之前的文档
       var ids=sc_gw_jiLuXXRepository.asQuerydsl().where(t -> t.bingRenID.in(dto.getMpiList())).where(t->t.wenDangID.eq(dto.getWenDangID())).select(p->p.id).fetch().stream().toList();
        sc_gw_jiLuXXRepository.asDeleteDsl().where(t -> t.id.in(ids)).execute();
        sc_gw_jiLuNRRepository.asDeleteDsl().where(t -> t.wenDangJLID.in(ids)).execute();
        //保存新的文档
        List<SC_GW_JiLuXXModel> jiLuXXModelList= ListUtil.toList();
        List<SC_GW_JiLuNRModel> jiLuNRModelList= ListUtil.toList();
        dto.getJiLuXXList().forEach(e -> {
            SC_GW_JiLuXXModel jiLuXXModel= BeanUtil.copyProperties(e, SC_GW_JiLuXXModel.class);
            SC_GW_JiLuNRModel jiLuNRModel= BeanUtil.copyProperties(e.getJiLuNRDto(), SC_GW_JiLuNRModel.class);
            var id=stringGenerator.Create();
            jiLuXXModel.setId(id);
            jiLuXXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            jiLuXXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            jiLuXXModelList.add(jiLuXXModel);
            jiLuNRModel.setWenDangJLID(id);
            jiLuNRModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
            jiLuNRModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            jiLuNRModelList.add(jiLuNRModel);
        });
        sc_gw_jiLuXXRepository.saveAll(jiLuXXModelList);
        sc_gw_jiLuNRRepository.saveAll(jiLuNRModelList);
        return true;
    }

}
