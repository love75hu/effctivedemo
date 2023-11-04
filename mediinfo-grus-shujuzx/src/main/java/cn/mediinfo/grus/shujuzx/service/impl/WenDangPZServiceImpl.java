package cn.mediinfo.grus.shujuzx.service.impl;

import cn.mediinfo.cyan.msf.core.exception.TongYongYWException;
import cn.mediinfo.cyan.msf.core.util.MapUtils;
import cn.mediinfo.cyan.msf.core.util.PageRequestUtil;
import cn.mediinfo.cyan.msf.orm.util.QueryDSLUtils;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangGreaterDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangMBDto;
import cn.mediinfo.grus.shujuzx.dto.wenDang.SC_ZD_WenDangUpDateDto;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangMBModel;
import cn.mediinfo.grus.shujuzx.model.QSC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangMBModel;
import cn.mediinfo.grus.shujuzx.model.SC_ZD_WenDangModel;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangMBRepository;
import cn.mediinfo.grus.shujuzx.repository.SC_ZD_WenDangRepository;
import cn.mediinfo.grus.shujuzx.service.WenDangPZService;
import cn.mediinfo.lyra.extension.service.LyraIdentityService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

@Service
public class WenDangPZServiceImpl implements WenDangPZService {

    private final LyraIdentityService lyraIdentityService;
    private final SC_ZD_WenDangRepository sc_zd_wenDangRepository;
    private final SC_ZD_WenDangMBRepository sc_zd_wenDangMBRepository;
    private final EntityManager entityManager;


    public WenDangPZServiceImpl(LyraIdentityService lyraIdentityService,
                                SC_ZD_WenDangRepository sc_zd_wenDangRepository, SC_ZD_WenDangMBRepository sc_zd_wenDangMBRepository, EntityManager entityManager) {
        this.lyraIdentityService = lyraIdentityService;
        this.sc_zd_wenDangRepository = sc_zd_wenDangRepository;
        this.sc_zd_wenDangMBRepository = sc_zd_wenDangMBRepository;
        this.entityManager = entityManager;
    }

    /**
     * 获取文档配置列表
     */
    @Override
    public List<SC_ZD_WenDangDto> getWenDangPZList(String leiBieDM, String likeQuery, Integer pageIndex, Integer pageSize) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        List<SC_ZD_WenDangModel> list = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .orderBy(sjModel.shunXuHao.asc())
                .offset(PageRequestUtil.of(pageIndex, pageSize).getOffset()).limit(pageSize)
                .fetch();
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : MapUtils.copyListProperties(list, SC_ZD_WenDangDto::new);
    }

    /**
     * 获取文档配置数量
     */
    @Override
    public long getWenDangPZCount(String leiBieDM, String likeQuery) {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        var Count = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(leiBieDM), () -> sjModel.leiBieDM.eq(leiBieDM)))
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(likeQuery), () -> sjModel.wenDangID.contains(likeQuery).or(sjModel.wenDangMC.contains(likeQuery))))
                .fetch().size();
        return Count;
    }

    /**
     * 新增文档配置
     */
    @Override
    public String addWenDangPZ(SC_ZD_WenDangGreaterDto wenDangGreaterDto) throws TongYongYWException {
        QSC_ZD_WenDangModel sjModel = QSC_ZD_WenDangModel.sC_ZD_WenDangModel;
        var Count = new JPAQueryFactory(sc_zd_wenDangRepository.getEntityManager())
                .select(sjModel)
                .from(sjModel)
                .where(QueryDSLUtils.whereIf(StringUtils.hasText(wenDangGreaterDto.getWenDangID()), () -> sjModel.wenDangID.eq(wenDangGreaterDto.getWenDangID())))
                .fetch().size();

        if (Count > 0) {
            throw new TongYongYWException("该文档已配置,请重新确认!");
        }

        var zuZhiJGID = lyraIdentityService.getJiGouID();
        var jiGouMC = lyraIdentityService.getJiGouMC();
        var WenDang = new SC_ZD_WenDangModel();
        WenDang.setZuZhiJGID(zuZhiJGID);
        WenDang.setZuZhiJGMC(jiGouMC);
        MapUtils.mergeProperties(wenDangGreaterDto, WenDang, true);
        sc_zd_wenDangRepository.save(WenDang);

        var WenDangMB = new SC_ZD_WenDangMBModel();
        WenDangMB.setZuZhiJGID(zuZhiJGID);
        WenDangMB.setZuZhiJGMC(jiGouMC);
        MapUtils.mergeProperties(wenDangGreaterDto, WenDangMB, true);
        sc_zd_wenDangMBRepository.save(WenDangMB);
        return WenDang.getId();
    }


    /**
     * 修改文档配置
     */
    @Override
    public Boolean UpDateWenDangPZ(SC_ZD_WenDangUpDateDto wenDangUpDateDto) throws TongYongYWException {

        var wenDangPZ = sc_zd_wenDangRepository.findById(wenDangUpDateDto.getId()).orElseGet(() -> null);
        if (wenDangPZ == null) {
            throw new TongYongYWException("查无此数据！");
        }
        MapUtils.mergeProperties(wenDangUpDateDto, wenDangPZ, true);
        sc_zd_wenDangRepository.save(wenDangPZ);

        QSC_ZD_WenDangMBModel Model = QSC_ZD_WenDangMBModel.sC_ZD_WenDangMBModel;

        var wenDangPZMB = sc_zd_wenDangMBRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangPZ.getWenDangID())).select(SC_ZD_WenDangMBModel.class).fetchFirst();

        MapUtils.mergeProperties(wenDangUpDateDto, wenDangPZMB, true);
        sc_zd_wenDangMBRepository.save(wenDangPZMB);
        return true;
    }

    /**
     * 根据文档ID获取模板内容
     */
    @Override
    public SC_ZD_WenDangMBDto getWenDangMBXX(String wenDangID) throws TongYongYWException {
        var wenDangPZMB = sc_zd_wenDangMBRepository.asQuerydsl().where(s -> s.wenDangID.eq(wenDangID)).select(SC_ZD_WenDangMBDto.class).fetchFirst();
        if (wenDangPZMB == null) {
            throw new TongYongYWException("查无此数据！");
        }

        return wenDangPZMB;
    }


//    public static void main(String[] args) {
//        // 创建DOM解析器工厂
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        // 创建DOM解析器
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        // 将XML字符串解析为Document对象
//        Document document = builder.parse("xml字符串");
//
//        // 获取XML文档的根节点
//        Node root = document.getDocumentElement();
//
//        // 遍历根节点的子节点
//        NodeList nodeList = root.getChildNodes();
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                // 如果节点是元素节点，则输出节点名称
//                System.out.println(node.getNodeName());
//                // 如果节点有子节点，则递归遍历子节点
//                if (node.hasChildNodes()) {
//                    printNode(node.getChildNodes(), 1);
//                }
//            }
//        }
//    }
//
//    private static void printNode(NodeList nodeList, int level) {
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            if (node.getNodeType() == Node.ELEMENT_NODE) {
//                // 输出缩进
//                for (int j = 0; j < level; j++) {
//                    System.out.print("  ");
//                }
//                // 输出节点名称
//                System.out.println(node.getNodeName());
//                // 如果节点有子节点，则递归遍历子节点
//                if (node.hasChildNodes()) {
//                    printNode(node.getChildNodes(), level + 1);
//                }
//            }
//        }
//    }
}
