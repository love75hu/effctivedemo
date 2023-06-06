package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.constant.ChaXunMSEnum;
import cn.mediinfo.grus.shujuzx.dto.YinSiGZSZs.SC_ZD_YinSiPZOutDto;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_YinSiPZRepository;
import cn.mediinfo.grus.shujuzx.service.YinSiGZSZService;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.util.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

/**
 * 隐私规则设置
 */
@Service
public class YinSiGZSZServiceImpl implements YinSiGZSZService {
    public  final SC_ZD_YinSiPZRepository scZdYinSiPZRepository;

    public YinSiGZSZServiceImpl(SC_ZD_YinSiPZRepository scZdYinSiPZRepository) {
        this.scZdYinSiPZRepository = scZdYinSiPZRepository;
    }

    @Override
    public List<SC_ZD_YinSiPZOutDto> getYinSiGZPZList(String chaXunMSDM, String zuZhiJGID) throws TongYongYWException {
        var canXunMSDMList = List.of(chaXunMSDM, ChaXunMSEnum.TongYongMS.getValue());
        var list = scZdYinSiPZRepository.findByZuZhiJGIDAndChaXunMSDMInAndQiYongBZ(zuZhiJGID,canXunMSDMList,1);
        var result = list.stream().filter(o-> Objects.equals(o.getChaXunMSDM(), chaXunMSDM)).map(o-> MapUtils.copyProperties(o,SC_ZD_YinSiPZOutDto::new)).toList();
        if(CollectionUtils.isEmpty(result)){
             result = list.stream().filter(o-> Objects.equals(o.getChaXunMSDM(), ChaXunMSEnum.TongYongMS.getValue())).map(o-> MapUtils.copyProperties(o,SC_ZD_YinSiPZOutDto::new)).toList();
        }
        return result;
    }
}
