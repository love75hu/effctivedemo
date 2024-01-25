package cn.mediinfo.grus.shujuzx.remoteservice;

import cn.mediinfo.cyan.msf.core.response.MsfResponse;
import cn.mediinfo.grus.shujuzx.dto.bihuanlcs.BingLiXQDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSC_JZXXDto;
import cn.mediinfo.grus.shujuzx.dto.wendangscs.WenDangSLDto;
import cn.mediinfo.grus.shujuzx.remotedto.JiuZhenXXs.*;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.ChaXunDto;
import cn.mediinfo.grus.shujuzx.remotedto.linchuang.JiuluTextRso;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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


    /**
     * 通过病人id集合 获取就诊次数
     *
     * @param bingRenIDs 病人集合
     */
    @PostMapping("/api/v1.0/JiuZhen/GetJiuZhenCSByBRIDs")
    MsfResponse<List<JZ_LC_JiuZhenSLRso>> GetJiuZhenCSByBRIDs(@RequestBody List<String> bingRenIDs);

    /**
     * 统计病人门诊住院就诊次数
     */
    @PostMapping("/api/v1.0/JiuZhen/BinRenJZCSTJ")
    MsfResponse<List<BinRenJZCSTJDto>> BinRenJZCSTJ(@RequestParam Integer shouCiZX, @RequestParam Integer zhiXingSJ);

    /**
     * 获取就诊信息
     */
    @PostMapping("/api/v1.0/JiuZhen/getZuYuanMZJZXX")
    MsfResponse<List<ZhuYuanMZJZXXRso>> getZuYuanMZJZXX(@RequestBody List<JiuZhenIDYWLXIDRso> jiuZhenIDYWLXIDS);

    /**
     * 获取病人基本信息for主索引详情
     */
    @GetMapping("/api/v1.0/BingRen/GetBingRenJBXXByBRID")
    MsfResponse<BingRenZSYXXRso> GetBingRenJBXXByBRID(@RequestParam String bingRenID);

    /**
     * 获取文档生成健康档案列表
     */
    @PostMapping("/api/v1.0/WenDangSC/GetWenDangSCJKDAList")
    MsfResponse<List<WenDangSLDto>> GetWenDangSCList(@RequestParam String mpi);

    /**
     * 根据病人信息获取所有的就诊记录
     */
    @GetMapping("/api/v1.0/JiuZhen/getJiuZhenXXListByBRXX")
    MsfResponse<List<WenDangSC_JZXXDto>> getJiuZhenXXListByBRXX(@RequestParam String mpi);

    /**
     * 根据病人信息获取所有的就诊记录数量
     */
    @GetMapping("/api/v1.0/JiuZhen/getJiuZhenXXCountByBRXX")
    MsfResponse<Integer> getJiuZhenXXCountByBRXX(@RequestParam String mpi);

    /**
     * 根据mpi获取所有关联mpi
     */
    @GetMapping("/api/v1.0/BingRen/GetGuanLianBRIDs")
    MsfResponse<List<String>> GetGuanLianBRIDs(@RequestParam String bingRenID);

}
