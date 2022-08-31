package com.lmr.service;

import com.lmr.pojo.User;
import com.lmr.vo.UserVo;

import java.util.List;

public interface UserService {
    User finByName(String name);//判断是否有相同的用户名
    User findById(int id);//新增页面
    User login(String name, String pwd);
    boolean saveOrUpdate(User user);
    int regist(User user);
   // List<User> quearyAll();
    //计算查询条数


   // int count(UserVo userVo);

    int getPageCount(UserVo userVo);

    List<User> page(Integer currentPage, Integer pageSize, UserVo userVo);

    boolean deleteById(int id);

    int findbyname(String name);


    List<String> findByName(int id);
//找回密码
    User findByNameAndTele(String name ,String tele);
}
