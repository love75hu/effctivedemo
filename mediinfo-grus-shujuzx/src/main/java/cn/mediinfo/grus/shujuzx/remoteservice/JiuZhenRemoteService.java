package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mediinfo-grus-huanzhe-query", path = "mediinfo-grus-huanzhe-query")
public interface JiuZhenRemoteService {

    /**
     * 通过病人id集合 获取就诊次数
     *
     * @param bingRenIDs 病人集合
     */
    @PostMapping("/api/v1.0/JiuZhen/GetJiuZhenCSByBRIDs")
    MsfResponse<List<JZ_LC_JiuZhenSLRso>> GetJiuZhenCSByBRIDs(@SpringQueryMap List<String> bingRenIDs);

    /**
     * 统计病人门诊住院就诊次数
     */
    @PostMapping("/api/v1.0/JiuZhen/BinRenJZCSTJ")
    MsfResponse<List<BinRenJZCSTJDto>> BinRenJZCSTJ(@RequestParam Integer shouCiZX, @RequestParam Integer zhiXingSJ);

    /**
     * 获取就诊信息
     */
    @GetMapping("/api/v1.0/JiuZhen/getZuYuanMZJZXX")
    MsfResponse<List<ZhuYuanMZJZXXRso>> getZuYuanMZJZXX(@SpringQueryMap List<JiuZhenIDYWLXIDRso> jiuZhenIDYWLXIDS);

    /**
     * 获取病人基本信息for主索引详情
     */
    @GetMapping("/api/v1.0/JiuZhen/GetBingRenJBXXByBRID")
    MsfResponse<BingRenZSYXXRso> GetBingRenJBXXByBRID(@SpringQueryMap String bingRenID);
}
