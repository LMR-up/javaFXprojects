package com.lmr.common;

import com.lmr.pojo.User;

public class UserInfo {
    public static User user;

    public static void setUser(User user) {
        UserInfo.user = user;
    }
}
