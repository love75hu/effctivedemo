package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuJDXXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuJDXXRepository;
import cn.mediinfo.grus.shujuzx.service.BiHuanSTJDXXService;
import org.springframework.stereotype.Service;

/**
 * 闭环视图节点信息服务
 */
@Service
public class BiHuanSTJDXXServiceImpl implements BiHuanSTJDXXService {
    private final SC_BH_ShiTuJDXXRepository shiTuJDXXRepository;

    public BiHuanSTJDXXServiceImpl(SC_BH_ShiTuJDXXRepository shiTuJDXXRepository) {
        this.shiTuJDXXRepository = shiTuJDXXRepository;
    }
    @Override
    public SC_BH_ShiTuJDXXDto  getShiTuJDXX(String id) throws WeiZhaoDSJException {
        var result=shiTuJDXXRepository.asQuerydsl().where(s->s.id.eq(id)).select(SC_BH_ShiTuJDXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

}
