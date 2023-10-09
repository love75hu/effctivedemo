package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.grus.shujuzx.bo.RelatedFangAnBO;
import cn.mediinfo.grus.shujuzx.dto.fangannr.FangAnSqlDTO;
import cn.mediinfo.grus.shujuzx.dto.fangansc.FangAnSCFieldDTO;
import cn.mediinfo.grus.shujuzx.manager.FangAnNRManager;
import cn.mediinfo.grus.shujuzx.model.SC_CX_FangAnNRModel;
import cn.mediinfo.grus.shujuzx.service.FangAnNRService;
import cn.mediinfo.grus.shujuzx.service.FangAnSCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FangAnNRServiceImpl implements FangAnNRService {

    @Autowired
    private FangAnNRManager fangAnNRManager;

    @Autowired
    private FangAnSCService fangAnSCService;

    @Override
    public List<FangAnSqlDTO> listFangAnSql(List<RelatedFangAnBO> relatedFangAnList) {
        Set<String> fangAnIds = relatedFangAnList.stream().map(RelatedFangAnBO::getFangAnId).collect(Collectors.toSet());
        Set<String> fangAnSCIds = relatedFangAnList.stream().map(RelatedFangAnBO::getFangAnSCId).collect(Collectors.toSet());
        List<SC_CX_FangAnNRModel> fangAnNRModelList = fangAnNRManager.listByFangAnIds(fangAnIds);
        List<FangAnSCFieldDTO> fieldList = fangAnSCService.listFangAnSCFieldByIds(fangAnSCIds);

        Map<String, String> sqlMap = fangAnNRModelList.stream().collect(Collectors.toMap(SC_CX_FangAnNRModel::getFangAnID, SC_CX_FangAnNRModel::getChaXunSQL));
        Map<String, String> fieldMap = fieldList.stream().collect(Collectors.toMap(FangAnSCFieldDTO::getId, FangAnSCFieldDTO::getZiDuanBM));

        return relatedFangAnList.stream().map(e -> {
            FangAnSqlDTO fangAnSql = new FangAnSqlDTO();
            fangAnSql.setFangAnId(e.getFangAnId());
            fangAnSql.setSql(sqlMap.get(e.getFangAnId()));
            fangAnSql.setZiDuanBM(fieldMap.get(e.getFangAnSCId()));
            return fangAnSql;
        }).toList();
    }
}
