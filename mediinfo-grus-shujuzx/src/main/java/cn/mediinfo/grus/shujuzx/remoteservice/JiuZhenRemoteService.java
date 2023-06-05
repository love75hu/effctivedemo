package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.BinRenJZCSTJDto;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.JZ_LC_JiuZhenSLRso;
import cn.mediinfo.starter.base.response.MsfResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mediinfo-vela-jiuzhen", path = "mediinfo-vela-jiuzhen")
public interface JiuZhenRemoteService {

    /**
     * 通过病人id集合 获取就诊次数
     * @param bingRenIDs 病人集合
     */
    @PostMapping("/api/v1.0/JiuZhen/GetJiuZhenCSByBRIDs")
    MsfResponse<List<JZ_LC_JiuZhenSLRso>> GetJiuZhenCSByBRIDs(@SpringQueryMap List<String> bingRenIDs);
    /**
     * 统计病人门诊住院就诊次数
     */
    @PostMapping("/api/v1.0/JiuZhen/BinRenJZCSTJ")
    MsfResponse<List<BinRenJZCSTJDto>>BinRenJZCSTJ(@RequestParam Integer shouCiZX,@RequestParam Integer zhiXingSJ);


}
