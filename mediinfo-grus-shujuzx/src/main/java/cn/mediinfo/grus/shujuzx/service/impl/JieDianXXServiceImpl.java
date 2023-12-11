package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.core.util.StringUtil;
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
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    private final LyraIdentityService lyraIdentityService;

    public JieDianXXServiceImpl(SC_BH_JieDianXXRepository jieDianXXRepository, SC_BH_JieDianSXRepository jieDianSXRepository, SC_BH_ZiBiHXXRepository ziBiHXXRepository, SC_BH_ZiBiHXSLRepository ziBiHXSLRepository, LyraIdentityService lyraIdentityService) {
        this.jieDianXXRepository = jieDianXXRepository;
        this.jieDianSXRepository = jieDianSXRepository;
        this.ziBiHXXRepository = ziBiHXXRepository;
        this.ziBiHXSLRepository = ziBiHXSLRepository;
        this.lyraIdentityService = lyraIdentityService;
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

        SC_BH_JieDianXXModel scBhJieDianXXModel = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getShangJiJDID()).orElse(null);

          SC_BH_JieDianXXModel jieDianXX = null; //todo 实例化

        SC_BH_JieDianXXModel jieDianXXModel = BeanUtil.copyProperties(dto.getJieDianXXSZDtoList(), SC_BH_JieDianXXModel.class);
        jieDianXXModel.setBiHuanID(dto.getBiHuanID());
        jieDianXXModel.setBiHuanMC(dto.getBiHuanMC());
        if (scBhJieDianXXModel==null)
        {
            if (dto.getJieDianXXSZDtoList().getId()!=null)
            {
                SC_BH_JieDianXXModel scBhJieDianXXModel1 = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getId()).orElse(new SC_BH_JieDianXXModel());
                BeanUtil.copyProperties(scBhJieDianXXModel1,jieDianXXModel);

                jieDianXX= jieDianXXRepository.save(scBhJieDianXXModel1);
            }else {
                jieDianXXModel.setShunXuHao(1);
                jieDianXX = jieDianXXRepository.save(jieDianXXModel);
            }

        }else {
            if (!StringUtil.hasText(dto.getJieDianXXSZDtoList().getId()) ) {
                //todo 查询操作修改
                jieDianXXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID()))
                        .where(n->n.jieDianID.eq(dto.getJieDianXXSZDtoList().getJieDianID())).execute();

                jieDianXXModel.setShunXuHao(scBhJieDianXXModel.getShunXuHao() + 1);
                //todo
                List<SC_BH_JieDianXXModel> fetch = jieDianXXRepository.asQuerydsl().where(n -> n.shunXuHao.gt(scBhJieDianXXModel.getShunXuHao())).fetch();
                for (var a : fetch) {
                    a.setShunXuHao(a.getShunXuHao() + 1);
                }
                jieDianXXRepository.saveAll(fetch);
                jieDianXX = jieDianXXRepository.save(jieDianXXModel);
            }else {
                SC_BH_JieDianXXModel scBhJieDianXXModel1 = jieDianXXRepository.findById(dto.getJieDianXXSZDtoList().getId()).orElse(new SC_BH_JieDianXXModel());
               BeanUtil.copyProperties(scBhJieDianXXModel1,jieDianXXModel);

                jieDianXX= jieDianXXRepository.save(scBhJieDianXXModel1);
            }
        }
        //节点时效
        List<SC_BH_JieDianSXModel> jieDianSX=new ArrayList<>();
        //子闭环信息
        List<SC_BH_ZiBiHXXModel> ziBiHXX= new ArrayList<>();
        //子闭环显示列
        List<SC_BH_ZiBiHXSLModel> ziBiHXSL=new ArrayList<>();

            var jieDianXXSZ=dto.getJieDianXXSZDtoList();
            if (BeanUtil.isNotEmpty(jieDianXXSZ.getZiBiHXXDto()))
            {
              var ziBiHXXModel=  BeanUtil.copyProperties(jieDianXXSZ.getZiBiHXXDto(),SC_BH_ZiBiHXXModel.class);

                ziBiHXXModel.setBiHuanID(dto.getBiHuanID());
                ziBiHXXModel.setBiHuanMC(dto.getBiHuanMC());
                ziBiHXXModel.setJieDianID(jieDianXXSZ.getJieDianID());
                ziBiHXXModel.setJieDianMC(jieDianXXSZ.getJieDianMC());
                ziBiHXX.add(ziBiHXXModel);
            }
            //时效
            for (var s :jieDianXXSZ.getJieDianSXList())
            {
               var jieDianSXModel= BeanUtil.copyProperties(s,SC_BH_JieDianSXModel.class);
                jieDianSXModel.setBiHuanID(dto.getBiHuanID());
                jieDianSXModel.setBiHuanMC(dto.getBiHuanMC());

                jieDianSX.add(jieDianSXModel);
            }
            //显示字段
            for (var x:jieDianXXSZ.getZiBiHXSLDtoList())
            {
                var ziBiHXSLModel=BeanUtil.copyProperties(x,SC_BH_ZiBiHXSLModel.class);
                ziBiHXSLModel.setBiHuanID(dto.getBiHuanID());
                ziBiHXSLModel.setBiHuanMC(dto.getBiHuanMC());
                ziBiHXSL.add(ziBiHXSLModel);
            }

        //节点失效
        jieDianSXRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        jieDianSXRepository.saveAll(jieDianSX);
        //子闭环信息
        ziBiHXXRepository.asDeleteDsl().where(n -> n.biHuanID.eq(dto.getBiHuanID())).execute();
        ziBiHXXRepository.saveAll(ziBiHXX);
        //子闭环显示列
        ziBiHXSLRepository.asDeleteDsl().where(n->n.biHuanID.eq(dto.getBiHuanID())).execute();
        ziBiHXSLRepository.saveAll(ziBiHXSL);
        return jieDianXX.getId();
    }

    /**
     * 根据闭环ID获取闭环设置信息
     *
     * @param biHuanID
     * @return
     */
    @Override
    public List<BiHuanSZXXDto> getBiHuanSZXXBybiHuanID(String biHuanID ,String jiGouID)
    {
        //节点信息
        List<BiHuanSZXXDto> jieDianXXList =jieDianXXRepository.jieDianXXList(biHuanID,jiGouID);
        //节点失效
        List< SC_BH_JieDianSXDto> biHuanSZXXList = jieDianSXRepository.biHuanSZXXList(biHuanID,lyraIdentityService.getJiGouID());

        //子闭环信息
        var ziBiHXXList = ziBiHXXRepository.ziBiHXXList(biHuanID,jiGouID);

        //子闭环显示列
        List<SC_BH_ZiBiHXSLDto> ziBiHXSLList = ziBiHXSLRepository.ziBiHXSLList(biHuanID,jiGouID);

        for (var a:jieDianXXList)
        {
            a.setZiBiHXXDto(BeanUtil.copyProperties(ziBiHXXList.stream().filter(x->x.getJieDianID().equals(a.getJieDianID())).findFirst().orElse(null),ZiBiHXXDto.class));

            a.setJieDianSXList(BeanUtil.copyToList(biHuanSZXXList.stream().filter(x->x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()),JieDianSXDto.class));

            a.setZiBiHXSLDtoList(BeanUtil.copyToList(ziBiHXSLList.stream().filter(x->x.getJieDianID().equals(a.getJieDianID())).collect(Collectors.toList()), ZiBiHXSLDto.class));
        }
        return jieDianXXList;
    }


    @Override
    public List<JieDianSXXXDto> getJieDianSXXX(String biHuanID,String jieDianID ,String jiGouID) {
        return jieDianXXRepository.byJieDianIDBiHuanID(biHuanID, jieDianID,jiGouID);
    }

    /**
     * 获取闭环信息
     *
     */
    @Override
    public BiHuanSZXXDto getBiHuanJDNRXX(String jieDianXXID) throws WeiZhaoDSJException {

        SC_BH_JieDianXXModel jieDianSXXXDto = jieDianXXRepository.findById(jieDianXXID).orElse(null);
         //todo  直接抛出异常

        if (jieDianSXXXDto!=null)
        {
            BiHuanSZXXDto biHuanSZXXDto = BeanUtil.copyProperties(jieDianSXXXDto, BiHuanSZXXDto.class);
            biHuanSZXXDto.setJieDianSXList(jieDianSXRepository.
                    JieDianSXList(jieDianSXXXDto.getBiHuanID(),
                            jieDianSXXXDto.getJieDianID(),
                            jieDianSXXXDto.getZuZhiJGID()));

            biHuanSZXXDto.setZiBiHXXDto(ziBiHXXRepository.ziBiHXXDto(jieDianSXXXDto.getBiHuanID(),
                            jieDianSXXXDto.getJieDianID(),
                            jieDianSXXXDto.getZuZhiJGID()));
            biHuanSZXXDto.setZiBiHXSLDtoList(ziBiHXSLRepository.ziBiHXSLDtoList(jieDianSXXXDto.getBiHuanID(),
                    jieDianSXXXDto.getJieDianID(),
                    jieDianSXXXDto.getZuZhiJGID()));
            return biHuanSZXXDto;
        }else {
            throw new WeiZhaoDSJException("未找到数据");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public boolean zuoFeiBiHuanJDXX(String jieDianXXID) throws WeiZhaoDSJException {

        SC_BH_JieDianXXModel jieDianXXModel = jieDianXXRepository.findById(jieDianXXID).orElse(null);
        if(jieDianXXModel==null)
        {
            throw new WeiZhaoDSJException("未找到数据");
        }
        jieDianXXRepository.delete(jieDianXXModel);
        jieDianSXRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        ziBiHXXRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        ziBiHXSLRepository.deleteByBiHuanID(jieDianXXModel.getBiHuanID(),
                jieDianXXModel.getJieDianID(),
                jieDianXXModel.getZuZhiJGID());
        return true;
    }

    @Override
    public boolean jieDianYC(String jieDianXXID,String yinCangBZ) {
        jieDianXXRepository.jieDianYC(jieDianXXID,yinCangBZ);
        return true;
    }


}