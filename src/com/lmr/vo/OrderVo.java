package com.lmr.vo;

import com.lmr.pojo.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
* 存放临时的订单时间，作为查询判断语句*/
@Setter@Getter@ToString
public class OrderVo extends Order {
    private String startTime;
    private String endTime;
}
