package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.grus.shujuzx.constant.ZhuSuoYCZLXEnum;
import cn.mediinfo.grus.shujuzx.dto.shujuzxzsys.BR_DA_ZhuSuoYCZRZDto;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ZhuSuoYCZRZService {
    /**
     * 获取主索引操作日志列表
     * @return
     * @throws TongYongYWException 通用异常
     */
    List<BR_DA_ZhuSuoYCZRZDto> getZhuSuoYCZRZList(Integer page, Integer pageSize, Date caoZuoKSRQ, Date caoZuoJSRQ, String caoZuoLXDM, String likeQuery) throws TongYongYWException, ParseException;
    /**
     * 获取主索引操作日志数量
     * @return
     * @throws TongYongYWException 通用异常
     */
    long getZhuSuoYCZRZCount(Date caoZuoKSRQ,Date caoZuoJSRQ,String caoZuoLXDM,String likeQuery) throws TongYongYWException, ParseException;
    /**
     * 操作日志
     * @return
     * @throws TongYongYWException 通用异常
     */
    Boolean addCaoZuoRZ(String bingRenID, String xingMing, ZhuSuoYCZLXEnum caoZuoLX, String caoZuoNR, Boolean saveChanges) throws TongYongYWException;
}
