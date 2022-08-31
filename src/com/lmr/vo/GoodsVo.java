package com.lmr.vo;

import com.lmr.pojo.Goods;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class GoodsVo extends Goods {
    private String startTime;
    private String endTime;
}
