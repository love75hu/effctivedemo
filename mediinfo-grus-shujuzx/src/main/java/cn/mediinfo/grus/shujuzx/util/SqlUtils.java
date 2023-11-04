package cn.mediinfo.grus.shujuzx.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.text.MessageFormat;
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
        String pattern="t_(\\d+)";
        return MessageFormat.format("select {0} from ({1}) as ll0",field,sql.replaceAll(pattern,"zcx_$1"));
    }

}
