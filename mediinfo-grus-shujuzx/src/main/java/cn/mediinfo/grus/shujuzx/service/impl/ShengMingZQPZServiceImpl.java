package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQCreateDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQListDto;
import cn.mediinfo.grus.shujuzx.dto.shengmingzqpzs.SC_ZD_ShengMingZQUpdateDto;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_ShengMingZQModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_ShengMingZQRepository;
import cn.mediinfo.grus.shujuzx.service.ShengMingZQPZService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShengMingZQPZServiceImpl implements ShengMingZQPZService {
    private final SequenceService sequenceService;
    private final SC_ZD_ShengMingZQRepository shengMingZQRepository;
    public ShengMingZQPZServiceImpl(SequenceService sequenceService,SC_ZD_ShengMingZQRepository shengMingZQRepository){
        this.sequenceService=sequenceService;
        this.shengMingZQRepository=shengMingZQRepository;
    }

    /**
     * 获取生命周期字典列表
     * @return
     */
    @Override
    public List<SC_ZD_ShengMingZQListDto> getShengMingZQList(){
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
        String shengmingZQID;
        if (!StringUtil.notHasText(createDto.getShengMingZQMC())) {
            shengmingZQID = sequenceService.getXuHao("SC_ZD_ShengMingZQ_ShengMingZQID", 7);
            boolean existShengMingZQMC = shengMingZQRepository.asQuerydsl().where(t->t.shengMingZQMC.eq(createDto.getShengMingZQMC())).exists();
            if (existShengMingZQMC) {
                throw new TongYongYWException("生命周期名称已存在，请重新确认! ");
            }
        } else {
            shengmingZQID = "";
        }
        SC_ZD_ShengMingZQModel addModel = BeanUtil.copyProperties(createDto, SC_ZD_ShengMingZQModel::new, (dto, model) -> {
            model.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
            model.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
            if (StringUtil.hasText(shengmingZQID)) {
                model.setShengMingZQID(shengmingZQID);
            }
        });
        shengMingZQRepository.insert(addModel);
        return true;
    }

    /**
     * 修改生命周期
     * @param updateDto
     * @return
     * @throws TongYongYWException
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean updateShengMingZQ(SC_ZD_ShengMingZQUpdateDto updateDto) throws TongYongYWException {

        //查询是否存在
        var shiFouCZ = shengMingZQRepository.existsByIdIsNotAndShengMingZQMC(updateDto.getId(), updateDto.getShengMingZQMC());
        //判断是否存在数据元类别
        if (shiFouCZ) {
            throw new TongYongYWException("生命周期名称已存在!");
        }
        var entity = shengMingZQRepository.findById(updateDto.getId()).orElse(null);
        if (null == entity) {
            throw new TongYongYWException("该数据源值域不存在!");
        }
        //把dto赋值到entity
        MapUtils.mergeProperties(updateDto, entity);
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

}
