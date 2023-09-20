package cn.mediinfo.grus.shujuzx.repository;

import cn.mediinfo.cyan.msf.orm.MsfJpaRepository;
import cn.mediinfo.cyan.msf.orm.datasource.MsfDataSource;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_BiHuanLCModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_BiHuanLCModel;
import org.springframework.data.jpa.repository.Query;
@MsfDataSource("datasourcesjzx")
public interface SC_ZD_BiHuanLCRepository extends MsfJpaRepository<QSC_ZD_BiHuanLCModel,SC_ZD_BiHuanLCModel, String> {
    /**
     *根据组织架构id和闭环类型代码流程名称判断闭环流程是否存在
     */
    public Boolean existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMC(String zuZhiJGID,String biHuanLXDM,String liuChengMC);
    @Query("select count(s) from SC_ZD_BiHuanLCModel as s where s.zuZhiJGID = :zuZhiJGID and s.biHuanLXDM = :biHuanLXDM and ((s.zhuYuanSYBZ=1 and s.zhuYuanSYBZ = :zhuYuanSYBZ) or (s.menZhenSYBZ=1 and s.menZhenSYBZ=:menZhenSYBZ) or (s.jiZhenSYBZ=1 and s.jiZhenSYBZ = :jiZhenSYBZ) or (s.tiJianSYBZ=1 and s.tiJianSYBZ=:tiJianSYBZ)) and s.qiYongBZ=1")
    public Integer getBiHuanLCModelList(String zuZhiJGID,String biHuanLXDM);

    /**
     *根据组织架构id和闭环类型代码流程名称和id判断闭环流程是否存在
     */
    public Boolean existsByZuZhiJGIDAndBiHuanLXDMAndLiuChengMCAndIdIsNot(String zuZhiJGID,String biHuanLXDM,String liuChengMC,String id);
    @Query("select count(s) from SC_ZD_BiHuanLCModel as s where s.zuZhiJGID = :zuZhiJGID and s.biHuanLXDM = :biHuanLXDM and s.id <> :id and ((s.zhuYuanSYBZ=1 and s.zhuYuanSYBZ = :zhuYuanSYBZ) or (s.menZhenSYBZ=1 and s.menZhenSYBZ=:menZhenSYBZ) or (s.jiZhenSYBZ=1 and s.jiZhenSYBZ = :jiZhenSYBZ) or (s.tiJianSYBZ=1 and s.tiJianSYBZ=:tiJianSYBZ)) and s.qiYongBZ=1")
    public Integer getBiHuanLCList(String zuZhiJGID,String biHuanLXDM,String id,Integer zhuYuanSYBZ,Integer menZhenSYBZ,Integer jiZhenSYBZ,Integer tiJianSYBZ);
}
