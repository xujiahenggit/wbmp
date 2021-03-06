package com.bank.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceSwitcher.getDataSource();
//        log.info("------------------当前数据源 {}", dataSource==null?"master":dataSource);
        return dataSource;
    }
}
