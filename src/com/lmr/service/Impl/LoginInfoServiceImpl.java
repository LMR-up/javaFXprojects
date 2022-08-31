package com.lmr.service.Impl;


import com.lmr.common.Constant;
import com.lmr.dao.Impl.LoginInfoDaoImpl;
import com.lmr.dao.LoginInfoDao;
import com.lmr.pojo.LoginInfo;
import com.lmr.service.LoginInfoService;

import java.util.List;

public class LoginInfoServiceImpl implements LoginInfoService {
  //private UserDao userDao;
    private LoginInfoDao loginInfoDao=new LoginInfoDaoImpl();
    @Override
    public void insert(LoginInfo loginInfo) {
        loginInfoDao.inert(loginInfo);
    }

  @Override
  public int getPageCount(LoginInfo loginInfo) {
      int count=loginInfoDao.getPageCount(loginInfo);
      if(count==0){
        return 1;
      }
    return count % Constant.PAGE_SIZE == 0 ? count /Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;
  }

  @Override
  public List<LoginInfo> page(Integer currentPage, Integer pageSize, LoginInfo loginInfo) {
    return loginInfoDao.page(currentPage,pageSize,loginInfo);
  }

  @Override
  public boolean deleteById(int id) {
    return loginInfoDao.deleteById(id);
  }


}
