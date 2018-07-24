package com.jarry.handler;

import com.jarry.handler.DataSourceHandler;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by jarry on 2018/7/4.
 */
public class MyDataSourceRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHandler.getDataSource();
    }
}
