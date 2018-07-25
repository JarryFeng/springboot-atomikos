package com.jarry.config;

/**
 * Created by jarry on 2018/7/16.
 */

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.jarry.handler.MyDataSourceRouting;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.SystemException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 万恶的@Primary注解，放在动态数据源中，会导致循环引用
 */
@Configuration
public class DBConfig {

    // 配置数据源
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier("datasource1") DataSource dataSource1, @Qualifier("datasource2") DataSource dataSource2) throws SQLException {
        MyDataSourceRouting myDataSourceRouting = new MyDataSourceRouting();

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("dataSource1", dataSource1);
        map.put("dataSource2", dataSource2);
        myDataSourceRouting.setTargetDataSources(map);
        myDataSourceRouting.setDefaultTargetDataSource(dataSource2);
        return myDataSourceRouting;
    }

    /*@Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }*/


    /*atomikos事务管理器*/
    public UserTransactionManager userTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }

    public UserTransactionImp userTransactionImp() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(5000);
        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(userTransactionManager());
        jtaTransactionManager.setUserTransaction(userTransactionImp());
        jtaTransactionManager.setAllowCustomIsolationLevels(true);
        return jtaTransactionManager;
    }

}
