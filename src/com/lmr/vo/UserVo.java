package com.lmr.vo;

import com.lmr.pojo.User;
import lombok.Getter;
import lombok.Setter;

/*
* 临时变量类用来存放临时的数据
* */
@Setter@Getter
public class UserVo extends User {
    private String startTime;
    private String endTime;

}
