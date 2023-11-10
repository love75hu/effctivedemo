package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.JiuluTextRso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient( value = "mediinfo-grus-linchuang-query",path = "mediinfo-grus-linchuang-query")
public interface LinChuangRemoteService {


    /**
     * 获取病历text
     */
    @GetMapping("/api/v1.0/BingLi/getBiLiJLTextXX")
    MsfResponse<List<JiuluTextRso>> getZuYuanMZJZXX(@SpringQueryMap BingLiXQDto bingLiXQDto);


}
