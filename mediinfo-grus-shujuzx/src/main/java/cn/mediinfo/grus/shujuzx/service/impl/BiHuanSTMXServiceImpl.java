package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuMXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTMXService;
import org.springframework.stereotype.Service;

/**
 * 闭环视图明细服务
 */
@Service
public class BiHuanSTMXServiceImpl implements BiHuanSTMXService {
    private final SC_BH_ShiTuMXRepository shiTuMXRepository;

    public BiHuanSTMXServiceImpl(SC_BH_ShiTuMXRepository shiTuMXRepository) {
        this.shiTuMXRepository = shiTuMXRepository;
    }
    @Override
    public SC_BH_ShiTuMXDto getShiTuMXById(String id) throws WeiZhaoDSJException {
        var result= shiTuMXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuMXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}
