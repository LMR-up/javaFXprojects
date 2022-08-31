package com.lmr.service.Impl;

import com.lmr.common.Constant;
import com.lmr.dao.Impl.UserTranDaoImpl;
import com.lmr.dao.UserTranDao;
import com.lmr.pojo.UserTran;
import com.lmr.service.UserTranService;
import com.lmr.vo.UserTranVo;

import java.util.List;

public class UserTranServiceImpl implements UserTranService {
    private UserTranDao userTranDao=new UserTranDaoImpl();
    @Override
    public int getPageCount( UserTranVo userTranVo) {
        int count= userTranDao.getPageCount(userTranVo);
        if(count==0){
            return 1;
        }
        return count % Constant.PAGE_SIZE ==0 ? count/Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;/*对其取余，不等0 就整除加一*/
    }

    @Override
    public List<UserTran> page(Integer currentPage, Integer pageSize, UserTranVo userTranVo) {
        return userTranDao.page(currentPage, pageSize, userTranVo);
    }

    @Override
    public boolean deleteById(int id) {
        return userTranDao.deleteById(id);
    }

    @Override
    public String findImagePath(int id) {
        return userTranDao.findImagePath(id);
    }
}
