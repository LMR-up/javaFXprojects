package com.lmr.service;

import com.lmr.pojo.Order;
import com.lmr.vo.OrderVo;

import java.util.List;

public interface OrderService {

    int getPageCount(OrderVo orderVo);//获取页数
    List<Order> page (Integer currentPage, Integer pageSize, OrderVo orderVo);
    boolean deleteById(int id);
/*加入购物车操作*/
    boolean addShopCar(int id);

    boolean pay(int id);

    String findImage(int id);

    boolean deleteWhenPay(int id);
}
