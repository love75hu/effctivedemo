package cn.mediinfo.grus.shujuzx.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
    @Bean(name = "datasourcesjzx_dataSourceProperties")
    @Primary
    @ConfigurationProperties("spring.datasource.multiple.datasourcesjzx")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @ConfigurationProperties(prefix = "spring.datasource.multiple.datasourcesjzx.configuration")
    @Bean(name = "datasourcesjzx_dataSourceFactory")
    public HikariDataSource datasourcesjzx_dataSourceFactory(@Qualifier("datasourcesjzx_dataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
