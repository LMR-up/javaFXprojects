package com.lmr.utils;

import java.util.Locale;
import java.util.UUID;

public class Uuid {
    public static String uuid(){
         String s= UUID.randomUUID().toString().replaceAll("-","").toUpperCase(Locale.ROOT);
        System.out.println(s);
        return s;
    }
}
