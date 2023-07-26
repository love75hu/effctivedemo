package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.quanshengmzqs.PeiZhiXXDtos;

public interface QuanShengMZQService {
    /**
     * 根据查询模式代码获取配置信息
     *
     * @param chaXunMSDM
     * @return
     */
    PeiZhiXXDtos getPeiZhiXXByCXMSDM(String chaXunMSDM);

}
