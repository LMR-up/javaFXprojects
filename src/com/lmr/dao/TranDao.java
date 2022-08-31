package com.lmr.dao;

import com.lmr.pojo.Tran;
import com.lmr.vo.TranVo;

import java.util.List;

public interface TranDao {
    int getPageCount(TranVo tranVo);

    List<Tran> page(Integer currentPage, Integer pageSize, TranVo tranVo);

    boolean deleteById(int id);

    String findImagePath(int id);
}
