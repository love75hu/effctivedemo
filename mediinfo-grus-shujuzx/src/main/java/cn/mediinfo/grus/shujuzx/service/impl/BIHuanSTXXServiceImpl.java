package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuangl.SC_BH_ShiTuXXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.BIHuanSTXXService;
import org.springframework.stereotype.Service;

/**
 * 闭环视图信息服务
 */
@Service
class BIHuanSTXXServiceImpl implements BIHuanSTXXService {
    private final SC_BH_ShiTuXXRepository shiTuXXRepository;

    public BIHuanSTXXServiceImpl(SC_BH_ShiTuXXRepository shiTuXXRepository) {
        this.shiTuXXRepository = shiTuXXRepository;
    }

    /**
     * 根据ID获取闭环视图信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_ShiTuXXDto getShiTuXXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ShiTuXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}