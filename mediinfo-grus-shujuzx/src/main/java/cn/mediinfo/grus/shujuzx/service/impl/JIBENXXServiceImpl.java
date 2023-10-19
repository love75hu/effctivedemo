package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_JIBENXXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JIBENXXRepository;
import cn.mediinfo.grus.shujuzx.service.JiBenXXService;
import org.springframework.stereotype.Service;

/**
 * 闭环信息服务
 */
@Service
class JIBENXXServiceImpl implements JiBenXXService {
    private final SC_BH_JIBENXXRepository jIBENXXRepository;

    public JIBENXXServiceImpl(SC_BH_JIBENXXRepository jIBENXXRepository) {
        this.jIBENXXRepository = jIBENXXRepository;
    }

    /**
     * 根据ID获取闭环信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JIBENXXDto getJIBENXXByID(String id) throws WeiZhaoDSJException {
        var result = jIBENXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JIBENXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}