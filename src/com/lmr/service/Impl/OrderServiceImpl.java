package com.lmr.service.Impl;

import com.lmr.common.Constant;
import com.lmr.dao.Impl.OrderDaoImpl;
import com.lmr.dao.OrderDao;
import com.lmr.pojo.Order;
import com.lmr.service.OrderService;
import com.lmr.vo.OrderVo;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private  OrderDao orderDao=new OrderDaoImpl();

    @Override
    public int getPageCount(OrderVo orderVo) {
        int count= orderDao.getPageCount(orderVo);
        if(count==0){
            return 1;
        }
        return count % Constant.PAGE_SIZE ==0 ? count/Constant.PAGE_SIZE:count/Constant.PAGE_SIZE+1;
    }

    @Override
    public List<Order> page(Integer currentPage, Integer pageSize, OrderVo orderVo) {
        return orderDao.page(currentPage,pageSize,orderVo);
    }

    @Override
    public boolean deleteById(int id) {
        return orderDao.deleteById(id);
    }

    @Override
    public boolean addShopCar(int id) {
        return orderDao.addShopCar(id);
    }

    @Override
    public boolean pay(int id) {
        return orderDao.PayOrder(id);
    }

    @Override
    public String findImage(int id) {
        return orderDao.findImage(id);
    }

    @Override
    public boolean deleteWhenPay(int id) {
        return orderDao.deleteWhenPay(id);
    }
}
