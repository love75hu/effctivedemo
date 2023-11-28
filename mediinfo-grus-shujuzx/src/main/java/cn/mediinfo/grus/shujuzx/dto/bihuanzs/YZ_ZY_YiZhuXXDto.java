package cn.mediinfo.grus.shujuzx.dto.bihuanzs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class YZ_ZY_YiZhuXXDto {
    /**
     * 组织机构ID
     */
    @Column(columnDefinition = "组织机构ID")
    private String zuZhiJGID;
    /**
     * 组织机构名称
     */
    @Column(columnDefinition = "组织机构名称")
    private String zuZhiJGMC;
    /**
     * 病人ID
     */
    @Column(columnDefinition = "病人ID")
    private String bingRenID;
    /**
     * 就诊ID
     */
    @Column(columnDefinition = "就诊ID")
    private String jiuZhenID;
    /**
     * 住院就诊ID
     */
    @Column(columnDefinition = "住院就诊ID")
    private String zhuYuanJZID;
    /**
     * 姓名
     */
    @Column(columnDefinition = "姓名")
    private String xingMing;
    /**
     * 住院号
     */
    @Column(columnDefinition = "住院号")
    private String zhuYuanHao;
    /**
     * 当前病区ID
     */
    @Column(columnDefinition = "当前病区ID")
    private String dangQianBQID;
    /**
     * 当前病区名称
     */
    @Column(columnDefinition = "当前病区名称")
    private String dangQianBQMC;
    /**
     * 当前科室ID
     */
    @Column(columnDefinition = "当前科室ID")
    private String dangQianKSID;
    /**
     * 当前科室名称
     */
    @Column(columnDefinition = "当前科室名称")
    private String dangQianKSMC;
    /**
     * 当前床位ID
     */
    @Column(columnDefinition = "当前床位ID")
    private String dangQianCWID;
    /**
     * 当前床位名称
     */
    @Column(columnDefinition = "当前床位名称")
    private String dangQianCWMC;
    /**
     * 当前医疗组ID
     */
    @Column(columnDefinition = "当前医疗组ID")
    private String dangQianYLZID;
    /**
     * 当前医疗组名称
     */
    @Column(columnDefinition = "当前医疗组名称")
    private String dangQianYLZMC;
    /**
     * 父医嘱ID
     */
    @Column(columnDefinition = "父医嘱ID")
    private String fuYiZID;
    /**
     * 顺序号
     */
    @Column(columnDefinition = "顺序号")
    private Integer shunXuHao;
    /**
     * 医嘱单号
     */
    @Column(columnDefinition = "医嘱单号")
    private String yiZhuDH;
    /**
     * 医嘱名称
     */
    @Column(columnDefinition = "医嘱名称")
    private String yiZhuMC;
    /**
     * 医嘱分类代码[YZ0002]
     */
    @Column(columnDefinition = "医嘱分类代码[YZ0002]")
    private String yiZhuFLDM;
    /**
     * 医嘱分类名称[1.诊疗2.西药3.成药4.草药7.大输液.8文本9.检查10.检验11.血液12.手术13.会诊][YZ0002]
     */
    @Column(columnDefinition = "医嘱分类名称[1.诊疗2.西药3.成药4.草药7.大输液.8文本9.检查10.检验11.血液12.手术13.会诊][YZ0002]")
    private String yiZhuFLMC;
    /**
     * 开始时间
     */
    @Column(columnDefinition = "开始时间")
    private Date kaiShiSJ;
    /**
     * 结束时间
     */
    @Column(columnDefinition = "结束时间")
    private Date jieShuSJ;
    /**
     * 医生嘱托
     */
    @Column(columnDefinition = "医生嘱托")
    private String yiShengZT;
    /**
     * 医嘱描述
     */
    @Column(columnDefinition = "医嘱描述")
    private String yiZhuMS;
    /**
     * 频次ID
     */
    @Column(columnDefinition = "频次ID")
    private String pinCiID;
    /**
     * 频次名称
     */
    @Column(columnDefinition = "频次名称")
    private String pinCiMC;
    /**
     * 频次执行时间:08:00|12:00|16:00对血糖类型医嘱会可选择
     */
    @Column(columnDefinition = "频次执行时间:08:00|12:00|16:00对血糖类型医嘱会可选择")
    private String pinCiZXSJ;
    /**
     * 首日次数
     */
    @Column(columnDefinition = "首日次数")
    private Integer shouRiCS;
    /**
     * 长期医嘱标志[1.长期0.临时2.ST]
     */
    @Column(columnDefinition = "长期医嘱标志[1.长期0.临时2.ST]")
    private Integer changQiYZBZ;
    /**
     * 自费状态[0.普通1.自费2.强制自费3.强制公费]
     */
    @Column(columnDefinition = "自费状态[0.普通1.自费2.强制自费3.强制公费]")
    private String ziFeiZT;
    /**
     * 单价
     */
    @Column(columnDefinition = "单价")
    private double danJia;
    /**
     * 医嘱项目ID
     */
    @Column(columnDefinition = "医嘱项目ID")
    private String yiZhuXMID;
    /**
     * 医嘱项目名称
     */
    @Column(columnDefinition = "医嘱项目名称")
    private String yiZhuXMMC;
    /**
     * 计费间隔：分钟为单位
     */
    @Column(columnDefinition = "计费间隔：分钟为单位")
    private Integer jiFeiJG;
    /**
     * 计费方式代码[YZ0009]
     */
    @Column(columnDefinition = "计费方式代码[YZ0009]")
    private String jiFeiFSDM;
    /**
     * 计费方式名称[0普通1计头不计尾]
     */
    @Column(columnDefinition = "计费方式名称[0普通1计头不计尾]")
    private String jiFeiFSMC;
    /**
     * 医嘱类型代码[YZ0003]：管道 血糖等
     */
    @Column(columnDefinition = "医嘱类型代码[YZ0003]：管道 血糖等")
    private String yiZhuLXDM;
    /**
     * 医嘱类型名称
     */
    @Column(columnDefinition = "医嘱类型名称")
    private String yiZhuLXMC;
    /**
     * 价格ID
     */
    @Column(columnDefinition = "价格ID")
    private String jiaGeID;
    /**
     * 规格ID
     */
    @Column(columnDefinition = "规格ID")
    private String guiGeID;
    /**
     * 大规格ID
     */
    @Column(columnDefinition = "大规格ID")
    private String daGuiGID;
    /**
     * 药品名称
     */
    @Column(columnDefinition = "药品名称")
    private String yaoPinMC;
    /**
     * 药品规格
     */
    @Column(columnDefinition = "药品规格")
    private String yaoPinGG;
    /**
     * 药品别名ID：药品名称显示主名或者别名
     */
    @Column(columnDefinition = "药品别名ID：药品名称显示主名或者别名")
    private String yaoPinBMID;
    /**
     * 产地类别代码
     */
    @Column(columnDefinition = "产地类别代码")
    private String chanDiLBDM;
    /**
     * 产地类别名称
     */
    @Column(columnDefinition = "产地类别名称")
    private String chanDiLBMC;
    /**
     * 产地ID
     */
    @Column(columnDefinition = "产地ID")
    private String chanDiID;
    /**
     * 产地名称
     */
    @Column(columnDefinition = "产地名称")
    private String chanDiMC;
    /**
     * 打印剂量
     */
    @Column(columnDefinition = "打印剂量")
    private String daYinJL;
    /**
     * 一次剂量
     */
    @Column(columnDefinition = "一次剂量")
    private BigDecimal yiCiJL;
    /**
     * 一次剂量单位
     */
    @Column(columnDefinition = "一次剂量单位")
    private String yiCiJLDW;
    /**
     * 一次用量
     */
    @Column(columnDefinition = "一次用量")
    private BigDecimal yiCiYL;
    /**
     * 包装量
     */
    @Column(columnDefinition = "包装量")
    private BigDecimal baoZhuangLiang;
    /**
     * 包装单位
     */
    @Column(columnDefinition = "包装单位")
    private String baoZhuangDW;
    /**
     * 最小单位用量
     */
    @Column(columnDefinition = "最小单位用量")
    private BigDecimal zuiXiaoDWYL;
    /**
     * 最小单位
     */
    @Column(columnDefinition = "最小单位")
    private String zuiXiaoDW;
    /**
     * 剂量
     */
    @Column(columnDefinition = "剂量")
    private BigDecimal jiLiang;
    /**
     * 剂量单位
     */
    @Column(columnDefinition = "剂量单位")
    private String jiLiangDW;
    /**
     * 体积
     */
    @Column(columnDefinition = "体积")
    private BigDecimal tiJi;
    /**
     * 体积单位
     */
    @Column(columnDefinition = "体积单位")
    private String tiJiDW;
    /**
     * 剂型ID
     */
    @Column(columnDefinition = "剂型ID")
    private String jiXingID;
    /**
     * 剂型名称
     */
    @Column(columnDefinition = "剂型名称")
    private String jiXingMC;
    /**
     * 剂型类别代码[YP0059]
     */
    @Column(columnDefinition = "剂型类别代码[YP0059]")
    private String jiXingLBDM;
    /**
     * 剂型类别名称[0.普通1.大输液2.针剂]
     */
    @Column(columnDefinition = "剂型类别名称[0.普通1.大输液2.针剂]")
    private String jiXingLBMC;
    /**
     * 滴速
     */
    @Column(columnDefinition = "滴速")
    private String diSu;
    /**
     * 滴速单位
     */
    @Column(columnDefinition = "滴速单位")
    private String diSuDW;
    /**
     * 服药顺序代码
     */
    @Column(columnDefinition = "服药顺序代码")
    private String fuYaoSXDM;
    /**
     * 服药顺序名称:口服药餐前餐后遵医嘱等
     */
    @Column(columnDefinition = "服药顺序名称:口服药餐前餐后遵医嘱等")
    private String fuYaoSXMC;
    /**
     * 领药方式代码[YZ0013]
     */
    @Column(columnDefinition = "领药方式代码[YZ0013]")
    private String lingYaoFSDM;
    /**
     * 领药方式名称[0.普通1.基数药2.自备药3.嘱托4.大规格领药5.计费6.单日累计取整7.单医嘱累计8.临时领药]
     */
    @Column(columnDefinition = "领药方式名称[0.普通1.基数药2.自备药3.嘱托4.大规格领药5.计费6.单日累计取整7.单医嘱累计8.临时领药]")
    private String lingYaoFSMC;
    /**
     * 取整类型代码[YZ0019]
     */
    @Column(columnDefinition = "取整类型代码[YZ0019]")
    private String quZhengLXDM;
    /**
     * 取整类型名称[1.单次取整2.单日累计3.逐日累计4.按请领单取整6.单医嘱取整]
     */
    @Column(columnDefinition = "取整类型名称[1.单次取整2.单日累计3.逐日累计4.按请领单取整6.单医嘱取整]")
    private String quZhengLXMC;
    /**
     * 皮试标志[0.不需要皮试1.普通皮试2.原液皮试]
     */
    @Column(columnDefinition = "皮试标志[0.不需要皮试1.普通皮试2.原液皮试]")
    private Integer piShiBZ;
    /**
     * 皮试结果代码[YZ0007]
     */
    @Column(columnDefinition = "皮试结果代码[YZ0007]")
    private String piShiJGDM;
    /**
     * 皮试结果名称
     */
    @Column(columnDefinition = "皮试结果名称")
    private String piShiJGMC;
    /**
     * 处理意见代码[YZ0008]
     */
    @Column(columnDefinition = "处理意见代码[YZ0008]")
    private String chuLiYJDM;
    /**
     * 处理意见名称
     */
    @Column(columnDefinition = "处理意见名称")
    private String chuLiYJMC;
    /**
     * 皮试医嘱id:长期医嘱填临时皮试医嘱的医嘱id方便同步皮试结果
     */
    @Column(columnDefinition = "皮试医嘱id:长期医嘱填临时皮试医嘱的医嘱id方便同步皮试结果")
    private String piShiYZID;
    /**
     * 毒理分类代码[YP0006]
     */
    @Column(columnDefinition = "毒理分类代码[YP0006]")
    private String duLiFLDM;
    /**
     * 毒理分类名称
     */
    @Column(columnDefinition = "毒理分类名称")
    private String duLiFLMC;
    /**
     * 中药开方类型代码[YZ0005]
     */
    @Column(columnDefinition = "中药开方类型代码[YZ0005]")
    private String zhongYaoKFLXDM;
    /**
     * 中药开方类型名称[0:饮片,1:散配颗粒剂,2:整包颗粒剂,3:小包装饮片]
     */
    @Column(columnDefinition = "中药开方类型名称[0:饮片,1:散配颗粒剂,2:整包颗粒剂,3:小包装饮片]")
    private String zhongYaoKFLXMC;
    /**
     * 贴数
     */
    @Column(columnDefinition = "贴数")
    private Integer tieShu;
    /**
     * 草药帖给药方式ID
     */
    @Column(columnDefinition = "草药帖给药方式ID")
    private String caoYaoTGYFSID;
    /**
     * 草药帖给药方式名称
     */
    @Column(columnDefinition = "草药帖给药方式名称")
    private String caoYaoTGYFSMC;
    /**
     * 代煎贴数
     */
    @Column(columnDefinition = "代煎贴数")
    private Integer daiJianTS;
    /**
     * 代煎单位
     */
    @Column(columnDefinition = "代煎单位")
    private String daiJianDW;
    /**
     * 特殊服法代码[YP0057]
     */
    @Column(columnDefinition = "特殊服法代码[YP0057]")
    private String teShuFFDM;
    /**
     * 特殊服法名称
     */
    @Column(columnDefinition = "特殊服法名称")
    private String teShuFFMC;
    /**
     * 药房位置ID
     */
    @Column(columnDefinition = "药房位置ID")
    private String yaoFangWZID;
    /**
     * 药房位置名称
     */
    @Column(columnDefinition = "药房位置名称")
    private String yaoFangWZMC;
    /**
     * 给药方式ID
     */
    @Column(columnDefinition = "给药方式ID")
    private String geiYaoFSID;
    /**
     * 给药方式名称
     */
    @Column(columnDefinition = "给药方式名称")
    private String geiYaoFSMC;
    /**
     * 给药方式类型代码
     */
    @Column(columnDefinition = "给药方式类型代码")
    private String geiYaoFSLXDM;
    /**
     * 给药方式类型名称
     */
    @Column(columnDefinition = "给药方式类型名称")
    private String geiYaoFSLXMC;
    /**
     * 避光标志
     */
    @Column(columnDefinition = "避光标志")
    private Integer biGuangBZ;
    /**
     * 出院带药标志[1出院带药]
     */
    @Column(columnDefinition = "出院带药标志[1出院带药]")
    private Integer chuYuanDYBZ;
    /**
     * 静脉配标志
     */
    @Column(columnDefinition = "静脉配标志")
    private Integer jingMaiPBZ;
    /**
     * 夜间标志
     */
    @Column(columnDefinition = "夜间标志")
    private Integer yeJianBZ;
    /**
     * 他科标志
     */
    @Column(columnDefinition = "他科标志")
    private Integer taKeBZ;
    /**
     * 外配标志
     */
    @Column(columnDefinition = "外配标志")
    private Integer waiPeiBZ;
    /**
     * 营养标志
     */
    @Column(columnDefinition = "营养标志")
    private Integer yingYangBZ;
    /**
     * 协定方标志
     */
    @Column(columnDefinition = "协定方标志")
    private Integer xieDingFBZ;
    /**
     * 协定处方id
     */
    @Column(columnDefinition = "协定处方id")
    private String xieDingFID;
    /**
     * 协定处方名称
     */
    @Column(columnDefinition = "协定处方名称")
    private String xieDingFMC;
    /**
     * 保密标志
     */
    @Column(columnDefinition = "保密标志")
    private Integer baoMiBZ;
    /**
     * 执行科室ID
     */
    @Column(columnDefinition = "执行科室ID")
    private String zhiXingKSID;
    /**
     * 执行科室名称:医技类项目的执行科室
     */
    @Column(columnDefinition = "执行科室名称:医技类项目的执行科室")
    private String zhiXingKSMC;
    /**
     * 发往科室ID
     */
    @Column(columnDefinition = "发往科室ID")
    private String faWangKSID;
    /**
     * 发往科室名称:药品发往手术室
     */
    @Column(columnDefinition = "发往科室名称:药品发往手术室")
    private String faWangKSMC;
    /**
     * 加急标志[1加急]
     */
    @Column(columnDefinition = "加急标志[1加急] ")
    private Integer jiaJiBZ;
    /**
     * 双签名标志[1需要双签名]精麻毒、交叉配血、高危高风险
     */
    @Column(columnDefinition = "双签名标志[1需要双签名]精麻毒、交叉配血、高危高风险")
    private Integer shuangQianMBZ;
    /**
     * 时机代码
     */
    @Column(columnDefinition = "时机代码")
    private String shiJiDM;
    /**
     * 时机名称[0.普通1.术前2.术中3.术后]
     */
    @Column(columnDefinition = "时机名称[0.普通1.术前2.术中3.术后]")
    private String shiJiMC;
    /**
     * 关联手术医嘱ID
     */
    @Column(columnDefinition = "关联手术医嘱ID")
    private String guanLianSSYZID;
    /**
     * 关联手术医嘱名称
     */
    @Column(columnDefinition = "关联手术医嘱名称")
    private String guanLianSSYZMC;
    /**
     * 医嘱状态代码[YZ0006]
     */
    @Column(columnDefinition = "医嘱状态代码[YZ0006]")
    private String yiZhuZTDM;
    /**
     * 医嘱状态名称[1.待提交2.已提交3.已执行4.已停止5.已撤销6.申请撤销]
     */
    @Column(columnDefinition = "医嘱状态名称[1.待提交2.已提交3.已执行4.已停止5.已撤销6.申请撤销]")
    private String yiZhuZTMC;
    /**
     * 开单时间
     */
    @Column(columnDefinition = "开单时间")
    private Date kaiDanSJ;
    /**
     * 开单医生ID
     */
    @Column(columnDefinition = "开单医生ID")
    private String kaiDanYSID;
    /**
     * 开单医生姓名
     */
    @Column(columnDefinition = "开单医生姓名")
    private String kaiDanYSXM;
    /**
     * 开单医疗组ID
     */
    @Column(columnDefinition = "开单医疗组ID")
    private String kaiDanYLZID;
    /**
     * 开单医疗组名称
     */
    @Column(columnDefinition = "开单医疗组名称")
    private String kaiDanYLZMC;
    /**
     * 开单科室ID
     */
    @Column(columnDefinition = "开单科室ID")
    private String kaiDanKSID;
    /**
     * 开单科室名称
     */
    @Column(columnDefinition = "开单科室名称")
    private String kaiDanKSMC;
    /**
     * 开单病区ID
     */
    @Column(columnDefinition = "开单病区ID")
    private String kaiDanBQID;
    /**
     * 开单病区名称
     */
    @Column(columnDefinition = "开单病区名称")
    private String kaiDanBQMC;
    /**
     * 开单位置ID
     */
    @Column(columnDefinition = "开单位置ID")
    private String kaiDanWZID;
    /**
     * 开单位置名称
     */
    @Column(columnDefinition = "开单位置名称")
    private String kaiDanWZMC;
    /**
     * 提交时间
     */
    @Column(columnDefinition = "提交时间")
    private Date tiJiaoSJ;
    /**
     * 提交医生ID
     */
    @Column(columnDefinition = "提交医生ID")
    private String tiJiaoYSID;
    /**
     * 提交医生姓名
     */
    @Column(columnDefinition = "提交医生姓名")
    private String tiJiaoYSXM;
    /**
     * 复核时间
     */
    @Column(columnDefinition = "复核时间")
    private Date fuHeSJ;
    /**
     * 复核人ID
     */
    @Column(columnDefinition = "复核人ID")
    private String fuHeRID;
    /**
     * 复核人姓名
     */
    @Column(columnDefinition = "复核人姓名")
    private String fuHeRXM;
    /**
     * 停嘱操作时间
     */
    @Column(columnDefinition = "停嘱操作时间")
    private Date tingZhuCZSJ;
    /**
     * 停嘱医生ID
     */
    @Column(columnDefinition = "停嘱医生ID")
    private String tingZhuYSID;
    /**
     * 停嘱医生姓名
     */
    @Column(columnDefinition = "停嘱医生姓名")
    private String tingZhuYSXM;
    /**
     * 停嘱病区ID
     */
    @Column(columnDefinition = "停嘱病区ID")
    private String tingZhuBQID;
    /**
     * 停嘱病区名称
     */
    @Column(columnDefinition = "停嘱病区名称")
    private String tingZhuBQMC;
    /**
     * 停嘱复核时间
     */
    @Column(columnDefinition = "停嘱复核时间")
    private Date tingZhuFHSJ;
    /**
     * 停嘱复核人ID
     */
    @Column(columnDefinition = "停嘱复核人ID")
    private String tingZhuFHRID;
    /**
     * 停嘱复核人姓名
     */
    @Column(columnDefinition = "停嘱复核人姓名")
    private String tingZhuFHRXM;
    /**
     * 排斥时间
     */
    @Column(columnDefinition = "排斥时间")
    private Date paiChiSJ;
    /**
     * 排斥医嘱ID
     */
    @Column(columnDefinition = "排斥医嘱ID")
    private String paiChiYZID;
    /**
     * 排斥医嘱名称
     */
    @Column(columnDefinition = "排斥医嘱名称")
    private String paiChiYZMC;
    /**
     * 最近计划时间(记录生成执行计划时间)
     */
    @Column(columnDefinition = "最近计划时间(记录生成执行计划时间)")
    private Date zuiJinJHSJ;
    /**
     * 最近计划人ID
     */
    @Column(columnDefinition = "最近计划人ID")
    private String zuiJinJHRID;
    /**
     * 最近计划人姓名
     */
    @Column(columnDefinition = "最近计划人姓名")
    private String zuiJinJHRXM;
    /**
     * 撤销类型代码:[1.医生撤销2.病人撤销]
     */
    @Column(columnDefinition = "撤销类型代码:[1.医生撤销2.病人撤销]")
    private String cheXiaoLXDM;
    /**
     * 撤销类型名称
     */
    @Column(columnDefinition = "撤销类型名称")
    private String cheXiaoLXMC;
    /**
     * 申请撤销原因
     */
    @Column(columnDefinition = "申请撤销原因")
    private String shenQingCXYY;
    /**
     * 原医嘱状态代码[YZ0006]
     */
    @Column(columnDefinition = "原医嘱状态代码[YZ0006]")
    private String yuanYiZZTDM;
    /**
     * 原医嘱状态名称
     */
    @Column(columnDefinition = "原医嘱状态名称")
    private String yuanYiZZTMC;
    /**
     * 原执行时间
     */
    @Column(columnDefinition = "原执行时间")
    private Date yuanZhiXSJ;
    /**
     * 原结束时间
     */
    @Column(columnDefinition = "原结束时间")
    private Date yuanJieSSJ;
    /**
     * 原停嘱操作时间
     */
    @Column(columnDefinition = "原停嘱操作时间")
    private Date yuanTingZCZSJ;
    /**
     * 原停嘱医生ID
     */
    @Column(columnDefinition = "原停嘱医生ID")
    private String yuanTingZYSID;
    /**
     * 原停嘱医生姓名
     */
    @Column(columnDefinition = "原停嘱医生姓名")
    private String yuanTingZYSXM;
    /**
     * 申请撤销时间
     */
    @Column(columnDefinition = "申请撤销时间")
    private Date shenQingCXSJ;
    /**
     * 申请撤销医生ID
     */
    @Column(columnDefinition = "申请撤销医生ID")
    private String shenQingCXYSID;
    /**
     * 申请撤销医生姓名
     */
    @Column(columnDefinition = "申请撤销医生姓名")
    private String shenQingCXYSXM;
    /**
     * 拒绝撤销时间
     */
    @Column(columnDefinition = "拒绝撤销时间")
    private Date juJuECXSJ;
    /**
     * 拒绝撤销人ID
     */
    @Column(columnDefinition = "拒绝撤销人ID")
    private String juJuECXRID;
    /**
     * 拒绝撤销人姓名
     */
    @Column(columnDefinition = "拒绝撤销人姓名")
    private String juJuECXRXM;
    /**
     * 撤销时间
     */
    @Column(columnDefinition = "撤销时间")
    private Date cheXiaoSJ;
    /**
     * 撤销人ID
     */
    @Column(columnDefinition = "撤销人ID")
    private String cheXiaoRID;
    /**
     * 撤销人姓名
     */
    @Column(columnDefinition = "撤销人姓名")
    private String cheXiaoRXM;
    /**
     * 输入人ID
     */
    @Column(columnDefinition = "输入人ID")
    private String shuRuRID;
    /**
     * 输入姓名
     */
    @Column(columnDefinition = "输入姓名")
    private String shuRuRXM;
    /**
     * 打印标志
     */
    @Column(columnDefinition = "打印标志")
    private Integer daYinBZ;
    /**
     * 已打印标志
     */
    @Column(columnDefinition = "已打印标志")
    private Integer yiDaYBZ;
    /**
     * 输入位置大类代码(1.门诊诊间2.住院医生3.病区护士4.手术室5.医技科室6.药房7.药库8.候诊位置)[7075]
     */
    @Column(columnDefinition = "输入位置大类代码(1.门诊诊间2.住院医生3.病区护士4.手术室5.医技科室6.药房7.药库8.候诊位置)[7075]")
    private String shuRuWZDLDM;
    /**
     * 输入位置大类名称
     */
    @Column(columnDefinition = "输入位置大类名称")
    private String shuRuWZDLMC;
    /**
     * 临床路径病人项目ID(CP_BR_XIANGMU.ID)
     */
    @Column(columnDefinition = "临床路径病人项目ID(CP_BR_XIANGMU.ID)")
    private String linChuangLJBRXMID;
    /**
     * 临床路径导入标志
     */
    @Column(columnDefinition = "临床路径导入标志")
    private Integer linChuangLJDRBZ;
    /**
     * 院前标志
     */
    @Column(columnDefinition = "院前标志")
    private Integer yuanQianBZ;
    /**
     * 院前医嘱ID
     */
    @Column(columnDefinition = "院前医嘱ID")
    private String yuanQianYZID;
    /**
     * 化疗标志
     */
    @Column(columnDefinition = "化疗标志")
    private Integer huaLiaoBZ;


}
