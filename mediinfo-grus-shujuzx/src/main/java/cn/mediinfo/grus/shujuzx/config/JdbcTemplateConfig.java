package cn.mediinfo.grus.shujuzx.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JdbcTemplateConfig {
    @Bean(name = "datasourcesjzx_jdbcTemplateFactory")
    public JdbcTemplate datasourcesjzx_jdbcTemplateFactory(@Qualifier("datasourcesjzx_dataSourceFactory") HikariDataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
