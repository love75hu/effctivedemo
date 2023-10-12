package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDMXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDMXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDMXService;
import org.springframework.stereotype.Service;

/**
 * 闭环视图节点明细服务
 */
@Service
public class BiHuanSTJDMXServiceImpl implements BiHuanSTJDMXService {
    private final SC_BH_ShiTuJDMXRepository shiTuJDMXRepository;
    public BiHuanSTJDMXServiceImpl(SC_BH_ShiTuJDMXRepository shiTuJDMXRepository)
    {

        this.shiTuJDMXRepository = shiTuJDMXRepository;
    }

    @Override
    public SC_BH_ShiTuJDMXDto getShiTuJDMXById(String id) throws WeiZhaoDSJException {
        SC_BH_ShiTuJDMXDto result = shiTuJDMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuJDMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
return result;
    }


}
