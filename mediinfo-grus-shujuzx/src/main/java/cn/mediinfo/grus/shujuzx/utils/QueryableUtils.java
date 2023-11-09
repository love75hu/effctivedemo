package cn.mediinfo.grus.shujuzx.utils;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.cyan.msf.orm.entity.MsfEntity;
import cn.mediinfo.cyan.msf.orm.enums.CustomComparable;
import cn.mediinfo.cyan.msf.orm.enums.OrderEnum;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.QuerydslExpression;
import cn.mediinfo.cyan.msf.orm.repository.querydsl.QuerydslJoinExpression;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class QueryableUtils {
    public static <QT extends EntityPath<T>, T extends MsfEntity<?>> QuerydslExpression<QT, T> orderBy(QuerydslExpression<QT, T> queryable, String paiXuZDMC, Integer daoXuBZ) {
        if (Objects.isNull(queryable) || StringUtil.notHasText(paiXuZDMC)) {
            return queryable;
        }
        //通过倒序标志获取排序规则是正序还是倒序
        var order = ObjectUtils.isEmpty(daoXuBZ) || daoXuBZ != 1 ? "asc" : "desc";
        //多个排序字段通过|分隔
        var paiXuZDList = paiXuZDMC.split("\\|");
        for (var item : paiXuZDList) {
            if (StringUtil.notHasText(item)) {
                continue;
            }
            queryable = queryable.orderBy(q -> OrderEnum.findByCode(order).apply(Expressions.comparablePath(CustomComparable.class, item)));
        }
        return queryable;
    }

    public static <T> QuerydslJoinExpression<T> orderBy(QuerydslJoinExpression<T> queryable, Function<T, Expression<?>[]> select, String paiXuZDMC, Integer daoXuBZ) {
        if (Objects.isNull(queryable) || Objects.isNull(select) || StringUtil.notHasText(paiXuZDMC)) {
            return queryable;
        }
        var paiXuZDList = paiXuZDMC.split("\\|");
        for (var paiXuZD : paiXuZDList) {
            queryable = queryable.orderBy(q -> orderBy(q, select, paiXuZD, daoXuBZ));
        }
        return queryable;
    }

    private static <T> OrderSpecifier<?> orderBy(T model, Function<T, Expression<?>[]> select, String paiXuZD, Integer daoXuBZ) {
        var fieldArray = select.apply(model);
        var order = ObjectUtils.isEmpty(daoXuBZ) || daoXuBZ != 1 ? "asc" : "desc";
        for (var field : fieldArray) {
            String fieldName;
            if (field instanceof Operation) {
                fieldName = ((Operation<?>) field).getArg(1).toString();
                if (Objects.equals(paiXuZD, fieldName)) {
                    var path = ((Operation<?>) field).getArg(0);
                    return OrderEnum.findByCode(order).apply(Expressions.comparablePath(CustomComparable.class, ((Path<?>) path).getRoot(), ((Path<?>) path).getMetadata().getName()));
                }
            } else {
                fieldName = ((Path<?>) field).getMetadata().getName();
                if (Objects.equals(paiXuZD, fieldName)) {
                    return OrderEnum.findByCode(order).apply(Expressions.comparablePath(CustomComparable.class, ((Path<?>) field).getRoot(), ((Path<?>) field).getMetadata().getName()));
                }
            }
        }
        return null;
    }

    /**
     * 字段是否为空值
     *
     * @param kongZhiList 空值字段
     * @param <QT>        QModel
     * @param <T>         model
     * @return func
     */
    public static <QT extends EntityPath<T>, T extends MsfEntity<?>> Function<QT, Predicate> ZiDuanSFWK(List<String> kongZhiList) {
        return model -> {
            var fields = model.getClass().getDeclaredFields();
            var whereBuilder = new BooleanBuilder();
            for (var item : kongZhiList) {
                if (StringUtil.notHasText(item)) {
                    continue;
                }
                var field = Arrays.stream(fields).filter(q -> item.equalsIgnoreCase(q.getName())).findFirst().orElse(null);
                if (Objects.isNull(field)) {
                    continue;
                }
                if (field.getGenericType() instanceof StringPath) {
                    //if (field.getType().getName().equals("com.querydsl.core.types.dsl.StringPath")) {
                    whereBuilder.or(Expressions.path(Object.class, model, field.getName()).isNull().or(Expressions.stringPath(model, field.getName()).eq("")));//.isEmpty()));
                } else {
                    whereBuilder.or(Expressions.path(Object.class, model, field.getName()).isNull());
                }

            }
            return whereBuilder;
        };
    }
}
