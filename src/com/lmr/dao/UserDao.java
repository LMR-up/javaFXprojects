package com.lmr.dao;

import com.lmr.pojo.User;
import com.lmr.vo.UserVo;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.List;

public interface UserDao {
    User finByName(String name);//注册判断是否已经存在用户
    User findByNameAndPassword(String name, String pwd);
    User findById(int id);
    int insert(User user);
    boolean saveOrUpdate(User user);
/*
* 获取查询的条数，在获取页数的时候调用*/
    int count(UserVo userVo);
/*查询数据显示在表格中*/
    List<User> page(Integer currentPage, Integer pageSize, UserVo userVo);

    boolean deleteById(int id);

    int findbyname(String name);


    List<String> findByName(int id);

    //找回密码
    User findByNameAndTele(String name ,String tele);
}
