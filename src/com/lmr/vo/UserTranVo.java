package com.lmr.vo;

import com.lmr.pojo.UserTran;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class UserTranVo extends UserTran {
    private String startTime;
    private String endTime;
}
