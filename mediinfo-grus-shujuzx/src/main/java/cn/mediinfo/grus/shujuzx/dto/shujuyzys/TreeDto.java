package cn.mediinfo.grus.shujuzx.dto.shujuyzys;

import lombok.Data;

import java.util.List;

@Data
public class TreeDto {
    /// <summary>
    /// ID
    /// </summary>
    public String id;
    /// <summary>
    /// 代码
    /// </summary>
    public String daiMa;
    /// <summary>
    /// 名称
    /// </summary>
    public String name;

    /// <summary>
    /// 是否是叶子节点
    /// </summary>
    public Boolean leaf;

    /// <summary>
    /// 是否父级
    /// </summary>
    public Boolean isParent;
    /// <summary>
    /// 父级ID
    /// </summary>
    public String pId;
    /// <summary>
    /// 是否展开
    /// </summary>
    public Boolean open;
    /// <summary>
    /// 是否选中
    /// </summary>
    public Boolean checked;
    /// <summary>
    /// 机构类型(机构树专用)
    /// </summary>
    ///public Integer jiGouLX;
    /// <summary>
    /// 上级机构代码链
    /// </summary>
   /// public String shangJiJGDML;
    /// <summary>
    /// 名称(TreeSelect)
    /// </summary>
    /// <value></value>
    public String label;
    /// <summary>
    ///  值(TreeSelect)
    /// </summary>
    public String value;
    /// <summary>
    /// 末级标志
    /// </summary>
    public Integer moJiBZ;

    /// <summary>
    /// 性质属性(每一位代表一个属性,字典定义)
    /// </summary>
    public String xingZhiSX;

    /// <summary>
    /// 代码级别
    /// </summary>
    public Integer daiMaJB;

    public List<TreeDto> children;

    /// <summary>
    /// 场景id
    /// </summary>
    public String changJingId;
    /// <summary>
    /// 场景名称
    /// </summary>
    public String changJingMC;
    /// <summary>
    /// 上级数据域值域代码
    /// </summary>
    public String shangJiSJYZYDM;
    /// <summary>
    /// 机构权限标志
    /// </summary>
    public Integer jiGouQXBZ = 0;
}
