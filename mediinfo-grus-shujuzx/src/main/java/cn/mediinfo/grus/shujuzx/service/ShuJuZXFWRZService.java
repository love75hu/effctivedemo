package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.gongxiangwd.SC_RZ_FangWenGXWDCreateDto;
import cn.mediinfo.grus.shujuzx.dto.gongxiangwd.SC_RZ_FangWenGXWDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.dto.shujuzxfwrz.ShuJuZXFWRZDto;

import java.util.Date;
import java.util.List;

public interface ShuJuZXFWRZService {
Boolean addFangWenRZ(AddFangWenRZDto addFangWenRZDto);

    /**
     * 根据查询条件查询360访问日志列表数量
     *
     * @param fangWenKSRQ 访问开始日期
     * @param fangWenJSRQ 访问结束日期
     * @param bingRenID 患者MPI
     * @param xingMing 患者姓名
     * @param fangWenRXM 访问人
     * @return
     */
    Integer getFangWenRZCount(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM);

    /**
     * 根据查询条件查询360访问日志列表
     *
     * @param fangWenKSRQ 访问开始日期
     * @param fangWenJSRQ 访问结束日期
     * @param bingRenID 患者MPI
     * @param xingMing 患者姓名
     * @param fangWenRXM 访问人
     * @param pageIndex 页码
     * @param pageSize 每页大小
     * @return
     */
    List<ShuJuZXFWRZDto> getFangWenRZList(Date fangWenKSRQ, Date fangWenJSRQ, String bingRenID, String xingMing, String fangWenRXM, Integer pageIndex, Integer pageSize);

    /**
     * 获取共享文档访问日志列表
     */
    List<SC_RZ_FangWenGXWDto> getFangWenGXWDList(Date fangWenRQKS, Date fangWenRQJS, String likeQuery, String fangWenR, Integer pageIndex, Integer pageSize);

    /**
     * 获取共享文档访问日志数量
     */
    long getFangWenGXWDCount(Date fangWenRQKS, Date fangWenRQJS, String likeQuery, String fangWenR);

    String addFangWenGXWD(SC_RZ_FangWenGXWDCreateDto fangWenGXWCreateDto);

}
