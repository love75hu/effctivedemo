package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnCXLSDto;
import cn.mediinfo.grus.shujuzx.dto.chaxunfaxx.FangAnXXDto;

import java.util.List;

public interface ChaXunFAXXService {

    List<FangAnXXDto> getChaXunFAXXSelect(String likeQuery, String fangAnLXDM, Integer pageIndex, Integer pageSize);

    List<FangAnXXDto> getFangAnXXList(String likeQuery, Integer pageIndex, Integer pageSize);

    List<FangAnCXLSDto> getFangAnCXLSList(Integer pageIndex, Integer pageSize);

    Boolean deleteFangAnCXLS(String id);

    Boolean deleteChaXunFA(String id);
}
