package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.SC_BH_ZiBiHXSLDto;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXSLRepository;
import cn.mediinfo.grus.shujuzx.service.ZiBiHXSLService;
import org.springframework.stereotype.Service;

/**
 * 子闭环显示列服务
 */
@Service
public class ZiBiHXSLServiceImpl implements ZiBiHXSLService {
    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;

    public ZiBiHXSLServiceImpl(SC_BH_ZiBiHXSLRepository ziBiHXSLRepository) {
        this.ziBiHXSLRepository = ziBiHXSLRepository;
    }

    /**
     * 根据ID获取子闭环显示列     *     * @param id     * @return
     */
    @Override
    public SC_BH_ZiBiHXSLDto getZiBiHXSLByID(String id) throws WeiZhaoDSJException {
        var result = ziBiHXSLRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_ZiBiHXSLDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }
}