package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXXDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXXRepository;
import cn.mediinfo.grus.shujuzx.service.ZiBiHXXService;
import org.springframework.stereotype.Service;

/**
 * 子闭环信息服务
 */
@Service
public class ZiBiHXXServiceImpl implements ZiBiHXXService {
    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    public ZiBiHXXServiceImpl(SC_BH_ZiBiHXXRepository ziBiHXXRepository) {
        this.ziBiHXXRepository = ziBiHXXRepository;
    }

    /**
     * 根据ID获取子闭环信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_ZiBiHXXDto getZiBiHXXByID(String id) throws WeiZhaoDSJException {
        var result = ziBiHXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ZiBiHXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}