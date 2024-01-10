package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQListDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQUpdateDto;
import cn.mediinfo.grus.shujuzx.dto.shujuyzys.SC_ZD_ShuJuYZYDto;
import cn.mediinfo.grus.shujuzx.model.SC_RW_JiBenXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShengMingZQModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShuJuYZYModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShengMingZQRepository;
import cn.mediinfo.grus.shujuzx.service.ShengMingZQPZService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShengMingZQPZServiceImpl implements ShengMingZQPZService {
    private final SequenceService sequenceService;
    private final SC_ZD_ShengMingZQRepository shengMingZQRepository;
    private final LyraIdentityService lyraIdentityService;

    public ShengMingZQPZServiceImpl(SequenceService sequenceService, SC_ZD_ShengMingZQRepository shengMingZQRepository, LyraIdentityService lyraIdentityService) {
        this.sequenceService = sequenceService;
        this.shengMingZQRepository = shengMingZQRepository;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 获取生命周期字典列表
     *
     * @return
     */
    @Override
    public List<SC_ZD_ShengMingZQListDto> getShengMingZQList() {
        var shengMingZQList = shengMingZQRepository.asQuerydsl()
                .orderBy(x -> x.shunXuHao.asc())
                .select(SC_ZD_ShengMingZQListDto.class)
                .fetch();

        return shengMingZQList;
    }

    /**
     * 新增生命周期字典
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean addShengMingZQ(SC_ZD_ShengMingZQCreateDto createDto) throws TongYongYWException {
        if (!StringUtil.notHasText(createDto.getShengMingZQMC())) {
            boolean existShengMingZQMC = shengMingZQRepository.asQuerydsl().where(t -> t.shengMingZQMC.eq(createDto.getShengMingZQMC())).exists();
            if (existShengMingZQMC) {
                throw new TongYongYWException("生命周期名称已存在，请重新确认! ");
            }
        }
        String shengmingZQID = sequenceService.getXuHao("SC_ZD_ShengMingZQ_ShengMingZQID", 7);

        SC_ZD_ShengMingZQModel addModel = BeanUtil.copyProperties(createDto, SC_ZD_ShengMingZQModel::new, (dto, model) -> {
            model.setZuZhiJGID(lyraIdentityService.getJiGouID());
            model.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
            model.setShengMingZQID(shengmingZQID);
            if (Objects.isNull(createDto.getShunXuHao())){
                // 获取shuJuYZYList集合中的SC_ZD_ShuJuYZYModel对象中的shunXuHao的最大值
                Optional<SC_ZD_ShengMingZQModel> maxModel=shengMingZQRepository.findAll().stream().max(Comparator.comparing(SC_ZD_ShengMingZQModel::getShunXuHao));;
                // 获取最大值
                int maxShunXuHao = maxModel.map(SC_ZD_ShengMingZQModel::getShunXuHao).orElse(0);
                model.setShunXuHao(maxShunXuHao+1);
            }
        });
        shengMingZQRepository.save(addModel);
        return true;
    }

    /**
     * 修改生命周期
     *
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateShengMingZQ(SC_ZD_ShengMingZQUpdateDto updateDto) throws TongYongYWException {

        var entity = shengMingZQRepository.findById(updateDto.getId()).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该生命周期不存在!");
        }
        //查询是否存在
        var shiFouCZ = shengMingZQRepository.existsByIdIsNotAndShengMingZQMC(updateDto.getId(), updateDto.getShengMingZQMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("生命周期名称已存在!");
        }

        //把dto赋值到entity
        BeanUtil.mergeProperties(updateDto, entity);
        shengMingZQRepository.save(entity);
        return true;
    }

    /**
     * 作废数据源值域
     *
     * @param id
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean zuoFeiShengMingZQ(String id) throws TongYongYWException {
        var entity = shengMingZQRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该生命周期字典不存在!");
        }
        //删除数据源值域
        shengMingZQRepository.delete(entity);
        return true;
    }

    @Override
    public SC_ZD_ShengMingZQDto getShengMingZQByID(String id) throws TongYongYWException {
        var shuJuYZY = shengMingZQRepository.findById(id).orElse(null);
        if (Objects.isNull(shuJuYZY)){
            throw new TongYongYWException("该生命周期字典不存在!");
        }
        return BeanUtil.copyProperties(shuJuYZY, SC_ZD_ShengMingZQDto::new);
    }

}
