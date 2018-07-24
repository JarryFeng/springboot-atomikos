package com.jarry.mapper;

import com.jarry.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * Created by jarry on 2018/6/14.
 */
@Mapper
public interface MybatisMapper {



    @Insert("insert into tbl_user(age, name, password) values(#{age}, #{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    //配置@Options(useGeneratedKeys=true, keyProperty="对象.属性") 这个的作用是设置是否使用JDBC的getGenereatedKeys()方法获取主键并赋值到keyProperty设置的对象的属性中
    public void addUser(User user);


}
