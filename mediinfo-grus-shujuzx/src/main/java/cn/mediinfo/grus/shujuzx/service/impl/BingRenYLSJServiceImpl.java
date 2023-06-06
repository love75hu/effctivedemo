package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.Events.BingRenXXEventDto;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.model.SC_LC_BingRenYLSJModel;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.JZ_LC_JiuZhenSLRso;
import cn.mediinfo.grus.shujuzx.remoteservice.JiuZhenRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_LC_BingRenYLSJRepository;
import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import cn.mediinfo.starter.base.response.MsfResponse;
import cn.mediinfo.starter.base.util.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BingRenYLSJServiceImpl implements BingRenYLSJService {

    private final SC_LC_BingRenYLSJRepository sc_lc_bingRenYLSJRepository;
    private final JiuZhenRemoteService jiuZhenRemoteService;

    public BingRenYLSJServiceImpl(SC_LC_BingRenYLSJRepository sc_lc_bingRenYLSJRepository,
                                  JiuZhenRemoteService jiuZhenRemoteService) {
        this.sc_lc_bingRenYLSJRepository = sc_lc_bingRenYLSJRepository;
        this.jiuZhenRemoteService = jiuZhenRemoteService;
    }

    /**
     * 订阅调用更新病人就诊次数-无病人数据新增病人事件
     *
     * @param eventDto 事件dto
     * @return 是否成功
     */
    @Override
    public Boolean updateBingRenYLSJForJZCS(BaseEventDto<Integer> eventDto) {
        var xiaoXiTou = eventDto.getXiaoXiTou();
        BingRenXXEventDto bingRenXX = eventDto.getYeWuXX().getBingRenXX();
        SC_LC_BingRenYLSJModel bingRenYLSJXX = sc_lc_bingRenYLSJRepository.findFirstByBingRenID(bingRenXX.getBingRenID());
        if (ObjectUtils.isEmpty(bingRenYLSJXX)) {
            var jiuZhenSLXX = new JZ_LC_JiuZhenSLRso();
            MsfResponse<List<JZ_LC_JiuZhenSLRso>> jiuZhenResult = jiuZhenRemoteService.GetJiuZhenCSByBRIDs(new ArrayList<String>() {{
                add(bingRenXX.getBingRenID());
            }});
            if (jiuZhenResult.getCode() >= 0) {
                jiuZhenSLXX = jiuZhenResult.getData().get(0);
            }
            JZ_LC_JiuZhenSLRso finalJiuZhenSLXX = jiuZhenSLXX;
            SC_LC_BingRenYLSJModel addBingRenYLSJ = MapUtils.copyProperties(bingRenXX, SC_LC_BingRenYLSJModel::new, (a, b) -> {
                b.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
                b.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGID);
                b.setXingMing(a.getBingRenXM());
                b.setMenZhenCS(finalJiuZhenSLXX.getMenZhenJZCS() == null ? 0 : finalJiuZhenSLXX.getMenZhenJZCS());
                b.setZhuYuanCS(finalJiuZhenSLXX.getZuYuanJZCS() == null ? 0 : finalJiuZhenSLXX.getZuYuanJZCS());
                b.setJiZhenCS(finalJiuZhenSLXX.getJiZhenJZCS() == null ? 0 : finalJiuZhenSLXX.getJiZhenJZCS());
                b.setGongWeiCS(finalJiuZhenSLXX.getGongWeiJZCS() == null ? 0 : finalJiuZhenSLXX.getGongWeiJZCS());
            });
            sc_lc_bingRenYLSJRepository.save(addBingRenYLSJ);

        } else {
            if (Objects.equals(xiaoXiTou.getXiaoXiLX(), "JZ_MZ_WanChengJZ")) {
                bingRenYLSJXX.setMenZhenCS(bingRenYLSJXX.getMenZhenCS() + 1);
            } else if (Objects.equals(xiaoXiTou.getXiaoXiLX(), "JZ_ZY_RuYuanDJ")) {
                bingRenYLSJXX.setZhuYuanCS(bingRenYLSJXX.getZhuYuanCS() + 1);
            } else if (Objects.equals(xiaoXiTou.getXiaoXiLX(), "JZ_ZY_QuXiaoRY")) {
                bingRenYLSJXX.setZhuYuanCS(bingRenYLSJXX.getZhuYuanCS() > 0 ? bingRenYLSJXX.getZhuYuanCS() - 1 : 0);
            }
        }
        sc_lc_bingRenYLSJRepository.save(bingRenYLSJXX);
        return true;
    }
}
