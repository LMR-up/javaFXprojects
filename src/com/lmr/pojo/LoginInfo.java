package com.lmr.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class LoginInfo {
    private int id;
    private String username;
    private String password;
    private Date logintime;

    public LoginInfo(String username, String password, Date logintime) {
        this.username = username;
        this.password = password;
        this.logintime = logintime;
    }
}