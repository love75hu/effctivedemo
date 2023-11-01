package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.grus.shujuzx.dto.bihuansz.*;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianSXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_JieDianXXModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXSLModel;
import cn.mediinfo.grus.shujuzx.model.SC_BH_ZiBiHXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianSXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_JieDianXXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXSLRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_BH_ZiBiHXXRepository;
import cn.mediinfo.grus.shujuzx.service.JieDianXXService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 闭环节点信息服务
 */
@Service
public class JieDianXXServiceImpl implements JieDianXXService {
    private final SC_BH_JieDianXXRepository jieDianXXRepository;

    private final SC_BH_JieDianSXRepository jieDianSXRepository;

    private final SC_BH_ZiBiHXXRepository ziBiHXXRepository;

    private final SC_BH_ZiBiHXSLRepository ziBiHXSLRepository;

    public JieDianXXServiceImpl(SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository) {
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
    }

    /**
     * 根据ID获取闭环节点信息     *     * @param id     * @return
     */
    @Override
    public SC_BH_JieDianXXDto getJieDianXXByID(String id) throws WeiZhaoDSJException {
        var result = jieDianXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_BH_JieDianXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    //添加闭环设置信息
    @Override
    @Transactional(rollbackOn = Exception.class)
    public String addBiHuanSZXX(AddBiHuanSZXXDto dto) {

        //节点信息
        List<SC_BH_JieDianXXModel> jieDianXX = new ArrayList<>();
        //节点时效
        List<SC_BH_JieDianSXModel> jieDianSX=new ArrayList<>();
        //子闭环信息
        SC_BH_ZiBiHXXModel ziBiHXX= new SC_BH_ZiBiHXXModel();
        //子闭环显示列
        List<SC_BH_ZiBiHXSLModel> ziBiHXSL=new ArrayList<>();

        for (var b:dto.getJieDianXXSZDtoList())
        {
            //子闭环信息
            BeanUtil.copyProperties(b,ziBiHXX);
            jieDianXX.add(BeanUtil.copyProperties(b,SC_BH_JieDianXXModel.class));
            //时效
            for (var s :b.getJieDianSXList())
            {
                jieDianSX.add( BeanUtil.copyProperties(s,SC_BH_JieDianSXModel.class));
            }
            //显示字段
            for (var x:b.getZiBiHXSLDtoList())
            {
                ziBiHXSL.add(BeanUtil.copyProperties(x,SC_BH_ZiBiHXSLModel.class));
            }
        }
        //
        //节点信息
        jieDianXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        jieDianXXRepository.saveAll(jieDianXX);
        //节点失效
        jieDianSXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        jieDianSXRepository.saveAll(jieDianSX);
        //子闭环信息
        ziBiHXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        ziBiHXXRepository.save(ziBiHXX);
        //子闭环显示列
        ziBiHXSLRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        ziBiHXSLRepository.saveAll(ziBiHXSL);
        return dto.getBiHuanID();
    }

    /**
     * 根据闭环ID获取闭环设置信息
     *
     * @param biHuanID
     * @return
     */
    @Override
    public List<BiHuanSZXXDto> getBiHuanSZXXBybiHuanID(String biHuanID)
    {
        List<BiHuanSZXXDto> biHuanSZXXDtos=new ArrayList<>();
       //节点信息
        List<BiHuanSZXXDto> jieDianXXList = jieDianXXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .orderBy(n->n.shunXuHao.asc())
                .select(BiHuanSZXXDto.class).fetch();
        //节点失效
        List<BiHuanSZXXDto> biHuanSZXXList = jieDianSXRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .select(BiHuanSZXXDto.class).fetch();

        //子闭环信息
        BiHuanSZXXDto ziBiHXXList = ziBiHXXRepository.asQuerydsl().where(n -> n.biHuanID.eq(biHuanID)).select(BiHuanSZXXDto.class).fetchFirst();
        //子闭环显示列
        List<BiHuanSZXXDto> ziBiHXSLList = ziBiHXSLRepository.asQuerydsl()
                .where(n -> n.biHuanID.eq(biHuanID))
                .orderBy(n->n.shunXuHao.asc())
                .select(BiHuanSZXXDto.class).fetch();

        for (var a:jieDianXXList)
        {
            ZiBiHXXDto ziBiHXXDto = BeanUtil.copyProperties(a, ZiBiHXXDto.class);
            a.setZiBiHXXDto(ziBiHXXDto);
            a.setJieDianSXList(BeanUtil.copyToList(biHuanSZXXList.stream().filter(x->x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()),JieDianSXDto.class));

            a.setZiBiHXSLDtoList(BeanUtil.copyToList(ziBiHXSLList.stream().filter(x->x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()), ZiBiHXSLDto.class));
        }
        return jieDianXXList;
    }
}