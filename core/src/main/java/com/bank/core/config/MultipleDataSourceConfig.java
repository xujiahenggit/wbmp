package com.bank.core.config;

import com.bank.core.entity.UmspscDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源bean的配置类
 *
 * @author zzy
 */
@Configuration
public class MultipleDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource master() {
        //DataSource build = DruidDataSourceBuilder.create().build();
        DataSource build = new UmspscDataSource();
        return build;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.esbmgt")
    public DataSource esb() {
        //DataSource build = DruidDataSourceBuilder.create().build();
        DataSource build = new UmspscDataSource();
        return build;
    }

    /**
     * 设置动态数据源，通过@Primary 来确定主DataSource
     *
     * @return
     */
    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(master());
        //配置多数据源
        Map<Object, Object> map = new HashMap<>();
        map.put(DynamicDataSourceSwitcher.Mater, master());
        map.put(DynamicDataSourceSwitcher.esb_mgt, esb());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

}
