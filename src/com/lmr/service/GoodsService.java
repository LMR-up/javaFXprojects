package com.lmr.service;
import com.lmr.pojo.Goods;
import com.lmr.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    Goods findById(int id);

    boolean saveOrUpdate(Goods goods);//编辑
    int insert(Goods goods);//新增


    int getPageCount(GoodsVo goodsVo);

    List<Goods> page(Integer currentPage, Integer pageSize, GoodsVo goodsVo);

    boolean deleteById(int id);

    String findImagePath(int id);

    String finByname(String name);
}
