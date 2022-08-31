package com.lmr.service;


import com.lmr.pojo.LoginInfo;

import java.util.List;

public interface LoginInfoService {
    void insert(LoginInfo loginInfo);//用户登陆时记录登录信息

    int getPageCount(LoginInfo  loginInfo);//获取总页数
    List<LoginInfo> page(Integer currentPage, Integer pageSize, LoginInfo loginInfo);//
    boolean deleteById(int id);



}
