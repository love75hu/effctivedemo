package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXZSYs.*;
import cn.mediinfo.starter.base.exception.TongYongYWException;
import cn.mediinfo.starter.base.response.MsfResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ZhuSuoYGLService {
    /**
     * 获取主索引的相似索引信息
     * @return BR_DA_JiBenXXByZSYGLDto
     * @throws TongYongYWException 通用异常
     */
    List<BR_DA_JiBenXXByZSYGLDto> getXiangSiSYList(String zhuSuoYBRID) throws  TongYongYWException;

    /**
     * 获取主索引详情
     * @return
     * @throws TongYongYWException 通用异常
     */
    ZhuSuoYXQDto getZhuSuoYXQ(String bingRenID, String chaXunMSDM) throws TongYongYWException, ParseException, NoSuchFieldException, IllegalAccessException;

    /**
     * 获取主索引管理数量
     * @return
     * @throws TongYongYWException 通用异常
     */
    Integer getZhuSuoYinCount(Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH) throws TongYongYWException, ParseException;
    /**
     * 获取主索引管理列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    List<BR_DA_JiBenXXByZSYGLDto> getZhuSuoYGLList(Integer pageIndex, Integer pageSize, Date kaiShiSJ, Date jieShuSJ, Integer xiangSiDu, String MPI, String xingMing, String lianXiDH, String shenFenZH, String jiuZhenKH) throws TongYongYWException, ParseException;
    /**
     * 合并页面获取主索引和相似索引信息
     * @return
     * @throws TongYongYWException 通用异常
     */
    List<BR_DA_JiBenXXByHBXXDto> getZhuSuoYXSList(String bingRenID, Integer xiangSiDu) throws TongYongYWException;
    /**
     * 合并
     * @return
     * @throws TongYongYWException 通用异常
     */
    Boolean heBing(SaveHeBingDto dto) throws TongYongYWException;

    /**
     * 忽略合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    Boolean huLueHB(HuLueHBDto dto) throws TongYongYWException;

    /**
     * 忽略合并
     * @return Boolean
     * @throws TongYongYWException 通用异常
     */
    String updateBingRenJBXX(BR_DA_JiBenXXCreateDto dto) throws TongYongYWException;
}
