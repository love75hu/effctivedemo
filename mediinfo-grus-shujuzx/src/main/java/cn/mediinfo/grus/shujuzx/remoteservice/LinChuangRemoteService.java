package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.ChaXunDto;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.JiuluTextRso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@FeignClient( value = "mediinfo-grus-linchuang-query",path = "mediinfo-grus-linchuang-query")
public interface LinChuangRemoteService {


    /**
     * 获取病历text
     */
    @PostMapping("/api/v1.0/BingLi/getBiLiJLTextXX")
    MsfResponse<List<JiuluTextRso>> getZuYuanMZJZXX(@RequestBody BingLiXQDto bingLiXQDto);

    /**
     * 获取数量
     */
    @PostMapping("/api/v1.0/ChaXun/getShuLiang")
    MsfResponse<Long> getShuLiang(@RequestBody ChaXunDto chaXunDto);

    /**
     * 获取字符串列表
     */
    @PostMapping("/api/v1.0/ChaXun/getStringList")
    MsfResponse<List<String>> getStringList(@RequestBody ChaXunDto chaXunDto);

    /**
     * 获取字典列表
     */
    @PostMapping("/api/v1.0/ChaXun/getZiDianList")
    MsfResponse<List<Map<String, Object>>> getZiDianList(@RequestBody ChaXunDto chaXunDto);

}
