package cn.mediinfo.grus.shujuzx.service;
import cn.mediinfo.cyan.msf.core.exception.YuanChengException;
import cn.mediinfo.grus.shujuzx.dto.shitumx.ShiTuMXZHCXDto;

import java.util.List;
public interface LinChuangJSSCXXZService {

    /**
     *综合查询输出项选择列表
     * @param yeWuLX
     * @param likeQuery
     * @return
     */
    List<ShiTuMXZHCXDto> getShuTuMXForZHCX(Integer yeWuLX, String likeQuery) throws YuanChengException;
}
