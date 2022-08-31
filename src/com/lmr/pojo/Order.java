package com.lmr.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter@Getter@ToString
public class Order {
    private int oid;
    private int userid;
    private String username;
    private String goodsname;
    private Double weight;
    private Double price;
    private Date addtime;
    private String imagepath;
    private Date ordertime;
    private Integer num;
}
