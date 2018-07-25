package com.jarry.service.impl;

import com.jarry.entry.User;
import com.jarry.handler.DataSourceHandler;
import com.jarry.mapper.Primary.PrimaryMapper;
import com.jarry.mapper.slave.SlaveMapper;
import com.jarry.service.MybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;

/**
 * Created by jarry on 2018/5/31.
 */
@Service
public class MybatisServiceImpl implements MybatisService {

    @Autowired
    private PrimaryMapper primaryMapper;

    @Autowired
    private SlaveMapper slaveMapper;

    //transactionManager属性必须是接口PlatformTransactionManager的实现类
    @Transactional
    public void testTransation() {


        DataSourceHandler.setDataSource("dataSource1");
        User user = new User();
        user.setPassword("9988");
        user.setName("纸老虎");
        user.setAge(99);
        primaryMapper.addUser(user);

        DataSourceHandler.setDataSource("dataSource2");
        User user2 = new User();
        user2.setPassword("19988");
        user2.setName("纸老虎2");//字段超长会报错
        user2.setAge(99);
        slaveMapper.addUser(user2);

        int a = 1/0;
    }
}
