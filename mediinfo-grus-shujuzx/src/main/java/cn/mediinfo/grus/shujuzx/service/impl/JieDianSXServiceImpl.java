package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JieDianSXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianSXRepository;
import cn.mediinfo.grus.shujuzx.service.JieDianSXService;
import org.springframework.stereotype.Service;

/**
 * 闭环节点时效服务
 */
@Service
class JieDianSXServiceImpl implements JieDianSXService {
    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    public JieDianSXServiceImpl(SC_BH_JieDianSXRepository jieDianSXRepository) {
        this.jieDianSXRepository = jieDianSXRepository;
    }

    /**
     * 根据ID获取闭环节点时效     *     * @param id     * @return
     */
    @Override
    public SC_BH_JieDianSXDto getJieDianSXByID(String id) throws WeiZhaoDSJException {
        var result = jieDianSXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JieDianSXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}