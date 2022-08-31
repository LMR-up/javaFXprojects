package com.lmr.dao;

import com.lmr.pojo.Goods;
import com.lmr.vo.GoodsVo;

import java.util.List;

public interface GoodsDao {
    Goods findById(int id);
    String finByname(String name);

    boolean saveOrUpdate(Goods goods);
    int insert(Goods goods);
    // List<User> quearyAll();
    //计算查询条数

    // int count(UserVo userVo);

    int getPageCount(GoodsVo goodsVo);

    List<Goods> page(Integer currentPage, Integer pageSize, GoodsVo goodsVo);

    boolean deleteById(int id);

    String findImagePath(int id);
}
