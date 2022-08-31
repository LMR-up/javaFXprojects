package com.lmr.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter@Getter@ToString@NoArgsConstructor
public class User {
    private int id;//用户id
    private String username;//用户名
    private String password;//用户密码
    private Date createtime;//用户创建时间
    private String telenum;
}
