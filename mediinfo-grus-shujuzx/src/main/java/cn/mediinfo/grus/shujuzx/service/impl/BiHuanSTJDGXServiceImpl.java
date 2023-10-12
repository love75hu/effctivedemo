package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDGXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDGXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDGXService;
import org.springframework.stereotype.Service;

/**
 * 闭环视图节点关系服务
 */
@Service
public class BiHuanSTJDGXServiceImpl implements BiHuanSTJDGXService {
    private final SC_BH_ShiTuJDGXRepository shiTuJDGXRepository;
    public BiHuanSTJDGXServiceImpl(SC_BH_ShiTuJDGXRepository shiTuJDGXRepository)
    {
        this.shiTuJDGXRepository = shiTuJDGXRepository;
    }
    @Override
    public SC_BH_ShiTuJDGXDto getShiTuJDGXByID(String id) throws WeiZhaoDSJException {
        var result= shiTuJDGXRepository.asQuerydsl().where(s->s.id.eq(id)).select(SC_BH_ShiTuJDGXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}
