package com.lmr.dao;

import com.lmr.pojo.Order;
import com.lmr.vo.OrderVo;

import java.util.List;

public interface OrderDao {

    boolean deleteById(int id);

    int getPageCount(OrderVo orderVo);//获取页数

    List<Order> page(Integer currentPage, Integer pageSize, OrderVo orderVo);

    boolean addShopCar(int id);

    String findImage(int id);

    boolean PayOrder(int id);

    boolean deleteWhenPay(int id);


}
