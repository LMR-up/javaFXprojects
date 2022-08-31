package com.lmr.service.Impl;

import com.lmr.common.Constant;
import com.lmr.dao.Impl.UserDaoImpl;
import com.lmr.dao.UserDao;
import com.lmr.pojo.LoginInfo;
import com.lmr.pojo.User;
import com.lmr.service.LoginInfoService;
import com.lmr.service.UserService;
import com.lmr.vo.UserVo;

import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    private LoginInfoService loginInfoService=new LoginInfoServiceImpl();

    @Override
    public User finByName(String name) {
        return userDao.finByName(name);
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User login(String name, String pwd) {
        loginInfoService.insert(new LoginInfo(name,pwd,new Date()));

        return userDao.findByNameAndPassword(name,pwd);
    }

    @Override
    /*判断是新增还是编辑状态*/
    public boolean saveOrUpdate(User user) {
        if (user != null && user.getId() != 0) {
            return userDao.saveOrUpdate(user) ;//编辑按钮，如果不为空则修改更新
        }
        return userDao.insert(user)==1;//新增按钮，如果为空的话，调用插入方法
    }



    @Override
    public int regist(User user) {
        return  userDao.insert(user);
    }

    @Override
    public int getPageCount(UserVo userVo) {
        int count = userDao.count(userVo);
        if(count==0){
            return 1;
        }
        /*如果整除pageSize就返回count/5，，如不过不能整除，就放回count/5  再加1页*/
        return count % Constant.PAGE_SIZE == 0 ? count /Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;
    }

    @Override
    public List<User> page(Integer currentPage, Integer pageSize, UserVo userVo) {
        return userDao.page(currentPage,pageSize,userVo);
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public int findbyname(String name) {

        return userDao.findbyname(name);
    }

    @Override
    public List<String> findByName(int id) {
        return userDao.findByName(id);
    }

    @Override
    public User findByNameAndTele(String name, String tele) {
        return userDao.findByNameAndTele(name,tele);
    }

}
