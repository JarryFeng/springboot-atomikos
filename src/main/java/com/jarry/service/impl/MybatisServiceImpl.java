package com.jarry.service.impl;

import com.jarry.handler.DataSourceHandler;
import com.jarry.entry.User;
import com.jarry.mapper.MybatisMapper;
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
    private MybatisMapper mybatisMapper;

    //@Autowired
    //private MybatisMapper2 mybatisMapper2;

    //transactionManager属性必须是接口PlatformTransactionManager的实现类
    @Transactional
    public void testTransation() {


        DataSourceHandler.setDataSource("dataSource1");
        User user = new User();
        user.setPassword("9988");
        user.setName("纸老虎");
        user.setAge(99);
        mybatisMapper.addUser(user);

        DataSourceHandler.setDataSource("dataSource2");
        User user2 = new User();
        user2.setPassword("19988");
        user2.setName("纸老虎2");//字段超长会报错
        user2.setAge(99);
        mybatisMapper.addUser(user2);

        int a = 1/0;
    }
}
