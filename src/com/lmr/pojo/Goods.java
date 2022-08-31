package com.lmr.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter@Getter
public class Goods {
    private Integer id;
    private String goodsname;
    private Double weight;
    private Double price;
    private Date addtime;
    private Integer goodnum;
    private String imagepath;//图片路径

}
