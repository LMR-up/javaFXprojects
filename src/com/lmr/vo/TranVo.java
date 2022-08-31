package com.lmr.vo;

import com.lmr.pojo.Tran;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class TranVo extends Tran {
    private String startTime;
    private String endTime;
}
