package cn.mediinfo.grus.shujuzx.dto.shitumx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 *  数据库连接信息
 */
@Getter
@Setter
@ToString
public class DatabaseDTO {

    /**
     * jdbcUrl
     */
    private String jdbcUrl;

    /**
     * driverClassName 没有可不填
     */
    private String driverClassName;

    /**
     *  用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * schema
     */
    private String schema;

    /**
     * 表名
     */
    private List<String> tableList;
}
