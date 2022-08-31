package com.lmr.dao;

import com.lmr.pojo.LoginInfo;

import java.util.List;

public interface LoginInfoDao {
    void inert(LoginInfo loginInfo);

    int getPageCount(LoginInfo loginInfo);

    List<LoginInfo> page(Integer currentPage, Integer pageSize, LoginInfo loginInfo);

    boolean deleteById(int id);




}