package com.lmr.service.Impl;

import com.lmr.common.Constant;
import com.lmr.dao.Impl.TranDaoImpl;
import com.lmr.dao.TranDao;
import com.lmr.pojo.Tran;
import com.lmr.service.TranService;
import com.lmr.vo.TranVo;

import java.util.List;

public class TranServiceImpl implements TranService {
    private TranDao tranDao=new TranDaoImpl();
    @Override
    public int getPageCount(TranVo tranVo) {
        int count= tranDao.getPageCount(tranVo);
        if(count==0){
            return 1;
        }
        return count % Constant.PAGE_SIZE ==0 ? count/Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;/*对其取余，不等0 就整除加一*/
    }

    @Override
    public List<Tran> page(Integer currentPage, Integer pageSize, TranVo tranVo) {
        return tranDao.page(currentPage, pageSize, tranVo);
    }

    @Override
    public boolean deleteById(int id) {
        return tranDao.deleteById(id);
    }

    @Override
    public String findImagePath(int id) {
        return tranDao.findImagePath(id);
    }
}
