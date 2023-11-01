package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.remotedto.GongYong.JG_ZZ_JiGouXXRso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "mediinfo-lyra-dizuo", path = "mediinfo-lyra-dizuo")
public interface DiZuoRemoteService {
    @GetMapping("/api/v1.0/ZuZhiXX/GetJiGouXXAllList")
    MsfResponse<List<JG_ZZ_JiGouXXRso>> getJiGouXXAllList();
}
