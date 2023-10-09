package cn.mediinfo.grus.shujuzx.sql.ast;

import cn.mediinfo.cyan.msf.core.util.JacksonUtil;
import org.junit.jupiter.api.Test;

class SQLQueryExprTest {

    @Test
    void test() {
        String json = "{\"root\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\"SELECT\"},\"left\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\" t0.id,t0.xingming,t0.chushengrq,t1.id,t1.jiuzhenrq\"},\"left\":null,\"right\":null},\"right\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\"FROM\"},\"left\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\" vela_br.br_da_jibenxx t0,vela_jz.jz_mz_jiuzhenxx t1\"},\"left\":null,\"right\":null},\"right\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\"WHERE\"},\"left\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\"t0.id=t1.bingrenid \"},\"left\":null,\"right\":null},\"right\":{\"value\":{\"fieldName\":null,\"operator\":\"AND\",\"val\":null,\"text\":\"AND\"},\"left\":{\"value\":{\"fieldName\":null,\"operator\":\"AND\",\"val\":null,\"text\":\"AND\"},\"left\":null,\"right\":{\"value\":{\"fieldName\":null,\"operator\":\"AND\",\"val\":null,\"text\":\"AND\"},\"left\":{\"value\":{\"fieldName\":\"t0.xingming\",\"operator\":\"EQUALITY\",\"val\":\"'张三'\",\"text\":\"t0.xingming = '张三' \"},\"left\":{\"value\":{\"fieldName\":\"t1.nianling\",\"operator\":\"GREATERTHAN\",\"val\":\"30\",\"text\":\"t1.nianling > 30 \"},\"left\":null,\"right\":null},\"right\":null},\"right\":{\"value\":{\"fieldName\":\"t0.chushengrq\",\"operator\":\"GREATERTHAN\",\"val\":\"'1940-01-01'\",\"text\":\"t0.chushengrq > '1940-01-01' \"},\"left\":null,\"right\":null}}},\"right\":{\"value\":{\"fieldName\":null,\"operator\":null,\"val\":null,\"text\":\"t0.xingbiedm<>'2' \"},\"left\":null,\"right\":null}}}}}}";
        SQLQueryExpr expr = JacksonUtil.getJsonToBean(json, SQLQueryExpr.class);
        System.out.println(expr.buildSQL(expr.getRoot()));
    }

}