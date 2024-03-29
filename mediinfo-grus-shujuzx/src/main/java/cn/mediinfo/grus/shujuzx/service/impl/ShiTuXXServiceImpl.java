package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.MsfResponseException;
import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.exception.WeiZhaoDSJException;
import cn.mediinfo.cyan.msf.core.util.AssertUtil;
import cn.mediinfo.cyan.msf.core.util.BeanUtil;
import cn.mediinfo.cyan.msf.stringgenerator.StringGenerator;
import cn.mediinfo.cyan.msf.util.tree.TreeNode;
import cn.mediinfo.cyan.msf.util.tree.TreeUtil;
import cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant;
import cn.mediinfo.grus.shujuzx.dto.shitumx.SC_CX_ShiTuXXByShiTuIDDto;
import cn.mediinfo.grus.shujuzx.dto.zonghecx.*;
import cn.mediinfo.grus.shujuzx.model.SC_CX_ShiTuXXModel;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuMXRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_CX_ShiTuXXRepository;
import cn.mediinfo.grus.shujuzx.service.ShiTuMXService;
import cn.mediinfo.grus.shujuzx.service.ShiTuXXService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import cn.mediinfo.lyra.extension.service.SequenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant.Fu_LEI_ID;
import static cn.mediinfo.grus.shujuzx.constant.ShuJuZXConstant.Fu_LEI_MC;

/**
 * 临床检索视图信息服务
 */
@Service
class ShiTuXXServiceImpl implements ShiTuXXService {
    private final StringGenerator stringGenerator;
    private final SC_CX_ShiTuXXRepository shiTuXXRepository;
    private final ShiTuMXService shiTuMXService;
    private final SequenceService sequenceService;



    public ShiTuXXServiceImpl(StringGenerator stringGenerator,
                              SC_CX_ShiTuXXRepository shiTuXXRepository,
                              ShiTuMXService shiTuMXService, SequenceService sequenceService) {
        this.stringGenerator = stringGenerator;
        this.shiTuXXRepository = shiTuXXRepository;
        this.shiTuMXService = shiTuMXService;
        this.sequenceService = sequenceService;
    }

    /**
     * 根据ID获取临床检索视图信息
     */
    @Override
    public SC_CX_ShiTuXXDto getShiTuXXByID(String id) throws WeiZhaoDSJException {
        //获取视图信息
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
    public  List<TreeNode<LinChuangJSSTDtoTree>> getLinChuangJSSTTree(String fuLeiID, String likeQuery) {
        //获取视图信息
        List<SC_CX_ShiTuXXModel> shiTuXXList = shiTuXXRepository.shiTuXXList(likeQuery);

        List<LinChuangJSSTDtoTree> linChuangJSSTDtoTrees= BeanUtil.copyListProperties(shiTuXXList, LinChuangJSSTDtoTree::new);

        return TreeUtil.assembleTree(linChuangJSSTDtoTrees, LinChuangJSSTDtoTree::getShiTuID, LinChuangJSSTDtoTree::getFuLeiID);
    }

    /**
     * 新增视图分类
     */
    @Override
    public String addShiTuFL(ShiTuFLDto dto) throws TongYongYWException {
        //判断是否存在相同名称
        AssertUtil.checkTongYongYW(!shiTuXXRepository.existsByFuLeiMC(dto.getShiTuFLMC()), "已经存在相同名称");
        //新增视图分类
        SC_CX_ShiTuXXModel shiTuXXModel =new SC_CX_ShiTuXXModel();

        shiTuXXModel.setFuLeiID(Fu_LEI_ID);
        shiTuXXModel.setFuLeiMC(Fu_LEI_MC);
        shiTuXXModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        shiTuXXModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        shiTuXXModel.setShiTuMC(dto.getShiTuFLMC());
        shiTuXXModel.setShiTuID(sequenceService.getXuHao("SC_CX_ShiTuXX_ShiTuID", 6));
        var shunXuHao=shiTuXXRepository.getMaxShunXuHao();
        //设置顺序号
        shiTuXXModel.setShunXuHao(dto.getShunXuHao()!=null?dto.getShunXuHao():shunXuHao+1);//顺序
        //保存视图分类
        SC_CX_ShiTuXXModel save = shiTuXXRepository.save(shiTuXXModel);
        return save.getId();
    }

    /**
     * 获取视图分类列表
     */
    @Override
    public List<ShiTuFLDto> getShiTuFLList(String fuLeiID, String likeQuery) {
        //获取视图分类列表
        return shiTuXXRepository.getShiTuFLList(fuLeiID,likeQuery);
    }


    /**
     * 根据主键获取视图分类
     */
    @Override
    public ShiTuFLDto getShiTuFLByID(String id) throws WeiZhaoDSJException {
        //获取视图信息
        SC_CX_ShiTuXXDto shiTuXXByID = getShiTuXXByID(id);
        ShiTuFLDto shiTuFLByIDDto = new ShiTuFLDto();
        //复制属性
        BeanUtil.copyProperties(shiTuXXByID,shiTuFLByIDDto);
        shiTuFLByIDDto.setShiTuFLMC(shiTuXXByID.getShiTuMC());
        return shiTuFLByIDDto;
    }


    /**
     * 新增临床检索视图信息
     */
    @Override
    public String addShiTuXX(ShiTuXXDto dto) {

        //生成视图id
        dto.setShiTuID(sequenceService.getXuHao("SC_CX_ShiTuxx_ShiTuID", 6));
        //复制属性
    var shiTuXXModel= BeanUtil.copyProperties(dto,SC_CX_ShiTuXXModel::new);
        shiTuXXModel.setZuZhiJGMC(ShuJuZXConstant.TONGYONG_JGMC);
        shiTuXXModel.setZuZhiJGID(ShuJuZXConstant.TONGYONG_JGID);
        shiTuXXModel.setMoJiBZ(1);
        shiTuXXModel.setShunXuHao(dto.getShunXuHao()==null?shiTuXXRepository.getMaxShunXuHaoByFuLeiID(dto.getFuLeiID())+1:dto.getShunXuHao());
        //保存返回id
        SC_CX_ShiTuXXModel save = shiTuXXRepository.save(shiTuXXModel);
        return save.getId();
    }

    /**
     * 编辑视图信息
     */
    @Override
    public Boolean updateShiTuXX(SC_CX_ShiTuXXDto dto) throws TongYongYWException {

        //获取视图信息
        SC_CX_ShiTuXXModel scCxShiTuXXModel = shiTuXXRepository.findById(dto.getId()).orElse(null);
        if (scCxShiTuXXModel==null) {
            throw new TongYongYWException("未获取到数据");
        }
        BeanUtil.copyProperties(dto,scCxShiTuXXModel);
        //更新
        shiTuXXRepository.save(scCxShiTuXXModel);

        return true;
    }

    /**
     * 编辑视图分类
     */
    @Override
    public Boolean updateShiTuFL(ShiTuFLDto dto) throws TongYongYWException {
        //判断是否存
        SC_CX_ShiTuXXModel scCxShiTuXXModel = shiTuXXRepository.findById(dto.getId()).orElse(null);
        if (scCxShiTuXXModel==null) {
            throw new TongYongYWException("未获取到数据");
        }
        //修改分类名称
        scCxShiTuXXModel.setShiTuMC(dto.getShiTuFLMC()); 
        shiTuXXRepository.save(scCxShiTuXXModel);
        return true;
    }

    /**
     * 作废视图分类
     */
    @Override
    public Boolean zuoFeiShiTuFL(String id) throws WeiZhaoDSJException {
        var data=shiTuXXRepository.findById(id).orElseThrow(()->new WeiZhaoDSJException("未获取到数据"));
        shiTuXXRepository.deleteAll(shiTuXXRepository.findByFuLeiID(data.getShiTuID()));
        shiTuXXRepository.deleteById(id);
        return true;
    }



    /**
     * 编辑视图字段
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateShiTuMX(UpdateShiTuMXDto updateShiTuMXDto) throws WeiZhaoDSJException {
        var scCxShiTuXXModel = shiTuMXService.getShiTuMXByID(updateShiTuMXDto.getId());
        BeanUtil.copyProperties(updateShiTuMXDto,scCxShiTuXXModel);
        shiTuMXService.updateShiTuMX(scCxShiTuXXModel.getShiTuID(),scCxShiTuXXModel.getShiTuMC(),updateShiTuMXDto.getGuanLianZDList());
        return true;
    }

    @Override
    public List<SC_CX_ShiTuXXByShiTuIDDto> getShiTuXXByIds(List<String> ids) {
        return shiTuXXRepository.findByShiTuIDIn(ids);
    }
}