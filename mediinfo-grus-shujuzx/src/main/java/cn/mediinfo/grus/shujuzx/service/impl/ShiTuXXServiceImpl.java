package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 临床检索视图信息服务
 */
@Service
class ShiTuXXServiceImpl implements ShiTuXXService {
    private final StringGenerator stringGenerator;
    private final SC_CX_ShiTuXXRepository shiTuXXRepository;
    private final ShiTuMXServiceImpl shiTuMXService;
    private final LyraIdentityService lyraIdentityService;

    public ShiTuXXServiceImpl(StringGenerator stringGenerator, SC_CX_ShiTuXXRepository shiTuXXRepository, ShiTuMXServiceImpl shiTuMXService, LyraIdentityService lyraIdentityService) {
        this.stringGenerator = stringGenerator;
        this.shiTuXXRepository = shiTuXXRepository;
        this.shiTuMXService = shiTuMXService;
        this.lyraIdentityService = lyraIdentityService;
    }

    /**
     * 根据ID获取临床检索视图信息
     */
    @Override
    public SC_CX_ShiTuXXDto getShiTuXXByID(String id) throws WeiZhaoDSJException {
        var result = shiTuXXRepository.asQuerydsl().where(s -> s.id.eq(id)).select(SC_CX_ShiTuXXDto.class).fetchFirst();
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 根据ID获取临床检索视图信息
     */
    @Override
    public SC_CX_ShiTuXXDto getShiTuXXByShiTuID(String shiTuID) throws MsfResponseException, WeiZhaoDSJException {
      var result=shiTuXXRepository.getShiTuXXByShiTuID(shiTuID);
        AssertUtil.checkWeiZhaoDSJ(result != null, "未获取到数据");
        return result;
    }

    /**
     * 获取临床检索视图树
     */
    @Override
    public List<LinChuangJSSTDtoTree> getLinChuangJSSTTree(String fuLeiID, String likeQuery) {
        List<SC_CX_ShiTuXXModel> shiTuXXList = shiTuXXRepository.shiTuXXList(likeQuery);
        List<LinChuangJSSTDtoTree> linChuangJSSTDtoTrees= BeanUtil.copyListProperties(shiTuXXList, LinChuangJSSTDtoTree::new);

        List<LinChuangJSSTDtoTree> linChuangJSSTDtoTreeList=new ArrayList<>();
        for(LinChuangJSSTDtoTree linChuangJSSTDtoTree :linChuangJSSTDtoTrees.stream().filter(s->s.getFuLeiID().equals(fuLeiID)).toList())
        {
            linChuangJSSTDtoTree.setChildren(
                    linChuangJSSTDtoTrees.stream().filter(s->s.getFuLeiID().equals(linChuangJSSTDtoTree.getShiTuID())).toList()
            );
            linChuangJSSTDtoTreeList.add(linChuangJSSTDtoTree);
        }
        return linChuangJSSTDtoTreeList;
    }

    /**
     * 新增视图分类
     */
    @Override
    public Boolean addShiTuFL(ShiTuFLDto dto) throws TongYongYWException {
        AssertUtil.checkTongYongYW(!shiTuXXRepository.existsByFuLeiMC(dto.getShiTuFLMC()), "已经存在相同名称");
        SC_CX_ShiTuXXModel shiTuXXModel =new SC_CX_ShiTuXXModel();
        shiTuXXModel.setFuLeiID("0");
        shiTuXXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        shiTuXXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        shiTuXXModel.setFuLeiMC(dto.getShiTuFLMC());
        var shunXuHao=shiTuXXRepository.getMaxShunXuHao();
        shiTuXXModel.setShunXuHao(shunXuHao==null?0:shunXuHao+1);

        shiTuXXRepository.save(shiTuXXModel);
        return true;
    }

    /**
     * 获取视图分类列表
     */
    @Override
    public List<ShiTuFLDto> getShiTuFLList(String fuLeiID, String likeQuery) {
        return shiTuXXRepository.getShiTuFLList(fuLeiID,likeQuery);
    }


    /**
     * 根据主键获取视图分类
     */
    @Override
    public ShiTuFLDto getShiTuFLByID(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuXXDto shiTuXXByID = getShiTuXXByID(id);
        ShiTuFLDto shiTuFLByIDDto = new ShiTuFLDto();
        BeanUtil.copyProperties(shiTuXXByID,shiTuFLByIDDto);
        shiTuFLByIDDto.setShiTuFLMC(shiTuXXByID.getFuLeiMC());
        return shiTuFLByIDDto;
    }


    /**
     * 新增临床检索视图信息
     */
    @Override
    public Boolean addShiTuXX(ShiTuXXDto dto) {
        dto.setShiTuID(stringGenerator.Create());
    var shiTuXXModel= BeanUtil.copyProperties(dto,SC_CX_ShiTuXXModel::new);
        shiTuXXModel.setZuZhiJGMC(lyraIdentityService.getJiGouMC());
        shiTuXXModel.setZuZhiJGID(lyraIdentityService.getJiGouID());
        shiTuXXModel.setMoJiBZ(1);
        shiTuXXModel.setShunXuHao(dto.getShunXuHao()==null?shiTuXXRepository.getMaxShunXuHaoByFuLeiID(dto.getFuLeiID())+1:dto.getShunXuHao());
        shiTuXXRepository.save(shiTuXXModel);
        return true;
    }

    /**
     * 编辑视图信息
     */
    @Override
    public Boolean updateShiTuXX(SC_CX_ShiTuXXDto dto) throws TongYongYWException {

        SC_CX_ShiTuXXModel scCxShiTuXXModel = shiTuXXRepository.findById(dto.getId()).orElse(null);
        AssertUtil.checkTongYongYW(scCxShiTuXXModel!=null, "未找到");
        BeanUtil.copyProperties(dto,scCxShiTuXXModel);
        shiTuXXRepository.save(scCxShiTuXXModel);
        return true;
    }

    /**
     * 编辑视图分类
     */
    @Override
    public Boolean updateShiTuFL(ShiTuFLDto dto) throws TongYongYWException {
        SC_CX_ShiTuXXModel scCxShiTuXXModel = shiTuXXRepository.findById(dto.getId()).orElse(null);
        if (scCxShiTuXXModel==null) {
            throw new TongYongYWException("未获取到数据");
        }
        scCxShiTuXXModel.setFuLeiMC(dto.getShiTuFLMC());
        shiTuXXRepository.save(scCxShiTuXXModel);
        return true;
    }

    /**
     * 作废视图分类
     */
    @Override
    public Boolean zuoFeiShiTuFL(String id) throws WeiZhaoDSJException {
        getShiTuXXByID(id);
        shiTuXXRepository.deleteById(id);
        return true;
    }

    /**
     * 根据主键编辑视图字段
     */
    @Override
    public ShiTuMXDto getShiTuMXByID(String id) throws WeiZhaoDSJException {
        SC_CX_ShiTuXXDto shiTuXXByID = getShiTuXXByID(id);
        var shiTuMxDto=new ShiTuMXDto();
        BeanUtil.copyProperties(shiTuXXByID,new ShiTuMXDto());
        //获取关联条件
        shiTuMxDto.setGuanLianZDList(shiTuMXService.getGuanLianTJList(shiTuXXByID.getShiTuID()));
        return shiTuMxDto;

    }

    /**
     * 编辑视图字段
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateShiTuMX(UpdateShiTuMXDto updateShiTuMXDto) throws WeiZhaoDSJException {
        SC_CX_ShiTuXXModel scCxShiTuXXModel = shiTuXXRepository.findById(updateShiTuMXDto.getId()).orElse(null);
        if (scCxShiTuXXModel==null) {
            throw new WeiZhaoDSJException("未获取到数据");
        }
        BeanUtil.copyProperties(updateShiTuMXDto,scCxShiTuXXModel);
        shiTuXXRepository.save(scCxShiTuXXModel);
        shiTuMXService.updateShiTuMX(scCxShiTuXXModel.getShiTuID(),scCxShiTuXXModel.getShiTuMC(),updateShiTuMXDto.getGuanLianZDList());
        return true;
    }
}