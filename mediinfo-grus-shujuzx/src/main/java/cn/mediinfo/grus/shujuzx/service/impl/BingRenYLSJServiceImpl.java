package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.Events.BaseEventDto;
import cn.mediinfo.grus.shujuzx.Events.BingRenXXEventDto;
import cn.mediinfo.grus.shujuzx.constant.JiuZhenLYEnum;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.bingrenylsjs.SC_LC_BingRenYLSJInDto;
import cn.mediinfo.grus.shujuzx.model.SC_LC_BingRenYLSJModel;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.BinRenJZCSTJDto;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.JZ_LC_JiuZhenSLRso;
import cn.mediinfo.grus.shujuzx.remoteservice.JiuZhenRemoteService;
import cn.mediinfo.grus.shujuzx.repository.SC_LC_BingRenYLSJRepository;
import cn.mediinfo.grus.shujuzx.service.BingRenYLSJService;
import cn.mediinfo.starter.base.exception.YuanChengException;
import cn.mediinfo.starter.base.lyra.service.LyraIdentityService;
import cn.mediinfo.starter.base.response.MsfResponse;
import cn.mediinfo.starter.base.util.MapUtils;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BingRenYLSJServiceImpl implements BingRenYLSJService {

    private final SC_LC_BingRenYLSJRepository sc_lc_bingRenYLSJRepository;
    private final JiuZhenRemoteService jiuZhenRemoteService;
    private final LyraIdentityService lyraIdentityService;

    public BingRenYLSJServiceImpl(
            SC_LC_BingRenYLSJRepository sc_lc_bingRenYLSJRepository,
            JiuZhenRemoteService jiuZhenRemoteService,
            LyraIdentityService lyraIdentityService) {
        this.sc_lc_bingRenYLSJRepository = sc_lc_bingRenYLSJRepository;
        this.jiuZhenRemoteService = jiuZhenRemoteService;
        this.lyraIdentityService = lyraIdentityService;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer addBingRenYLSJ(SC_LC_BingRenYLSJInDto addBingRenDto) {
        var bingRenYLSJList = sc_lc_bingRenYLSJRepository.findByBingRenID(addBingRenDto.getBingRenID());
        if (bingRenYLSJList != null) {
            if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.MenZhen.getValue())) {
                bingRenYLSJList.setMenZhenCS(bingRenYLSJList.getMenZhenCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.JiZhen.getValue())) {
                bingRenYLSJList.setJiZhenCS(bingRenYLSJList.getJiZhenCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.ZhuYuan.getValue())) {
                bingRenYLSJList.setZhuYuanCS(bingRenYLSJList.getZhuYuanCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.GongWei.getValue())) {
                bingRenYLSJList.setGongWeiCS(bingRenYLSJList.getGongWeiCS() + 1);
            }
            sc_lc_bingRenYLSJRepository.save(bingRenYLSJList);
        } else {
            var addModel = new SC_LC_BingRenYLSJModel();
            MapUtils.mergeProperties(addBingRenDto, addModel);
            if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.MenZhen.getValue())) {
                addModel.setMenZhenCS(addModel.getMenZhenCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.JiZhen.getValue())) {
                addModel.setJiZhenCS(addModel.getJiZhenCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.ZhuYuan.getValue())) {
                addModel.setZhuYuanCS(addModel.getZhuYuanCS() + 1);
            } else if (Objects.equals(addBingRenDto.getJiuZhenLYDM(), JiuZhenLYEnum.GongWei.getValue())) {
                addModel.setGongWeiCS(addModel.getGongWeiCS() + 1);
            }
            sc_lc_bingRenYLSJRepository.save(addModel);
        }
        return 1;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Integer updateBingRenYLSJ(Integer shouCiZX, Integer zhiXingSJ) throws YuanChengException {
        //要修改的值
        var binRenJZCSTJList = jiuZhenRemoteService.BinRenJZCSTJ(shouCiZX, zhiXingSJ).getData("调用就诊远程服务报错");
        //病人id集合
        var bingRenID = binRenJZCSTJList.stream().map(BinRenJZCSTJDto::getBingRenID).toList();
        //获取要更新的病人数据
        var bingRenYLSJUP = sc_lc_bingRenYLSJRepository.findByBingRenIDIn(bingRenID);
        //更新字段
        for (var n : bingRenYLSJUP) {
            var dto = binRenJZCSTJList
                    .stream()
                    .filter(x -> Objects.equals(x.getBingRenID(), n.getBingRenID()))
                    .findFirst()
                    .orElse(null);

            if (dto != null) {
                n.setMenZhenCS(dto.getMenZhenCS() + shouCiZX == 0 ? 0 : n.getMenZhenCS());
                n.setZhuYuanCS(dto.getZhuYuanCS() + shouCiZX == 0 ? 0 : n.getZhuYuanCS());
                n.setJiZhenCS(dto.getJiZhenCS() + shouCiZX == 0 ? 0 : n.getJiZhenCS());
                n.setGongWeiCS(dto.getGongWeiCS() + shouCiZX == 0 ? 0 : n.getGongWeiCS());
            }
            else {
                n.setMenZhenCS(shouCiZX == 0 ? 0 : n.getMenZhenCS());
                n.setZhuYuanCS(shouCiZX == 0 ? 0 : n.getZhuYuanCS());
                n.setJiZhenCS(shouCiZX == 0 ? 0 : n.getJiZhenCS());
                n.setGongWeiCS(shouCiZX == 0 ? 0 : n.getGongWeiCS());

            }
        }
        //转换更新
        sc_lc_bingRenYLSJRepository.saveAll(bingRenYLSJUP);
        var bingRenYLSJIn = binRenJZCSTJList
                .stream()
                .filter(x -> bingRenYLSJUP.stream().map(SC_LC_BingRenYLSJModel::getBingRenID).toList().contains(x.getBingRenID()))
                .toList();
        var inserData = MapUtils.copyListProperties(bingRenYLSJIn, SC_LC_BingRenYLSJModel::new, (a, b) -> {
            b.setZuZhiJGID(lyraIdentityService.getJiGouID());
            b.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        });
        sc_lc_bingRenYLSJRepository.saveAll(inserData);
        return 1;
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

            MsfResponse<List<JZ_LC_JiuZhenSLRso>> jiuZhenResult = jiuZhenRemoteService.GetJiuZhenCSByBRIDs(Collections.singletonList(bingRenXX.getBingRenID()));
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
