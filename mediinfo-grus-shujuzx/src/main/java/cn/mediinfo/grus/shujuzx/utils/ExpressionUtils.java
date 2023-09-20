package cn.mediinfo.grus.shujuzx.utils;

import cn.mediinfo.cyan.msf.core.util.StringUtil;
import cn.mediinfo.grus.shujuzx.dto.yinsigzszs.SC_ZD_YinSiPZOutDto;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExpressionUtils {
    /**
     * 移除重复的对象(根据多个指定列)
     * @param keyExtractors 多个用于判断的表达式
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors)
    {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t ->
        {
            final List<?> keys = Arrays.stream(keyExtractors)
                    .map(ke -> ke.apply(t))
                    .collect(Collectors.toList());

            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    public static <T> T getYinShiSJ(T tModel, List<SC_ZD_YinSiPZOutDto> tuoMinPZList) throws IllegalAccessException {
        if (tModel == null || CollectionUtils.isEmpty(tuoMinPZList)) {
            return tModel;
        }
        Class<?> type = tModel.getClass(); // 获取类型
        for (var tuoMinPZ : tuoMinPZList) {
            Field propertyInfo = ExpressionUtils.getDeclaredFieldIgnoreCase(type,tuoMinPZ.getShuJuYLM()); // 获取属性
            if(propertyInfo != null) {
                propertyInfo.setAccessible(true);
                String t = propertyInfo.getType().getName();
                if (t.equals("java.lang.String")) {
                    Object value = propertyInfo.get(tModel);
                    if (ObjectUtils.isEmpty(value) || !StringUtil.hasText(value.toString())) {
                        continue;
                    }
                    if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "1")) {
                        //1-全部替换为所需字符，如 *
                        propertyInfo.set(tModel, (tuoMinPZ.getTuoMinGZ() != null ? tuoMinPZ.getTuoMinGZ() : "*").repeat(value.toString().length())); //给对应属性赋值
                    } else if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "2")) {
                        //2-部分替换为设置字符：如：000***000，0为占位符
                        char[] charArray = value.toString().toCharArray();
                        int index = 1;
                        int j = 1;
                        String fuHao2 = "";
                        if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ())) {
                            String[] tuoMinGZs = tuoMinPZ.getTuoMinGZ().split(",");
                            if (tuoMinGZs.length > 0) {
                                index = tuoMinGZs[0].isEmpty() ? 0 : Integer.parseInt(tuoMinGZs[0]);
                            }
                            if (tuoMinGZs.length > 1) {
                                j = tuoMinGZs[1].isEmpty() ? 0 : Integer.parseInt(tuoMinGZs[1]);
                            }
                            if (tuoMinGZs.length > 2) {
                                fuHao2 = tuoMinGZs[2].isEmpty() ? "*" : tuoMinGZs[2];
                            }
                        }
                        for (int i = index; i < index + j; i++) {
                            if (charArray.length - 1 < i) {
                                break;
                            }
                            charArray[i] = fuHao2.charAt(0);
                        }
                        propertyInfo.set(tModel, new String(charArray));//给对应属性赋值
                    } else if (Objects.equals(tuoMinPZ.getTuoMinFSDM(), "3")) {
                        //3-正则替换
                        if (StringUtil.hasText(tuoMinPZ.getTuoMinGZ()) && tuoMinPZ.getTuoMinGZ().split(",").length > 1) {
                            Pattern pattern = Pattern.compile(tuoMinPZ.getTuoMinGZ().split(",")[0]);
                            String replaceWord = tuoMinPZ.getTuoMinGZ().split(",")[1];
                            value = pattern.matcher(value.toString()).replaceAll(replaceWord);
                        }
                        propertyInfo.set(tModel, value);//给对应属性赋值
                    }
                } else {
                    propertyInfo.set(tModel, null);
                }
            }
        }
        return tModel;
    }
    /**
     * 遍历匹配 -- 忽略大小写
     * @param cls
     * @param fieldName
     * @return
     */
    public static Field getDeclaredFieldIgnoreCase(Class<?> cls, String fieldName) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }
}
