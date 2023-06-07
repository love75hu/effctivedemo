package cn.mediinfo.grus.shujuzx.service;

import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.AddFangWenRZDto;
import cn.mediinfo.grus.shujuzx.dto.ShuJuZXFWRZ.ShuJuZXFWRZDto;

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

}
