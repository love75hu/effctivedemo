package cn.mediinfo.grus.shujuzx.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public final class SqlUtils {
    private SqlUtils() {
    }

    /**
     * 替换输出字段
     * @param sql sql查询语句
     * @param field 替换字段
     * @return 替换后sql查询语句
     */
    public static String replaceOutputField(String sql, String field) {
        if (StringUtils.isEmpty(sql) || (!sql.startsWith("SELECT") && !sql.startsWith("select"))) {
            return "";
        }
        sql = sql.replace("select", "SELECT").replace("from", "FROM");
        int selectIndex = sql.indexOf("SELECT") + "SELECT".length();
        int fromIndex = sql.indexOf("FROM");

        String fields = sql.substring(selectIndex, fromIndex).trim();
        List<String> fieldList = StrUtil.split(fields, ",");

        String replaceFields = "";
        for (String e : fieldList) {
            String col = e.contains(".") ? e.substring(e.indexOf(".") + 1) : e;
            if (Objects.equals(col, field)) {
                replaceFields = e;
                break;
            }
        }

        return sql.replace(fields, replaceFields);
    }

}
