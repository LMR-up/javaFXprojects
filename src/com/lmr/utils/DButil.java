package com.lmr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DButil {
    public static void main(String[] args) throws SQLException, IOException {//测试类
        Connection getconct = DButil.getconct();
        System.out.println(getconct);
    }



    static String url = "";
    static String username = "";
    static String password = "";
    static {

        //读取类路径底下的配置文件
        InputStream in = DButil.class.getClassLoader().getResourceAsStream("com/lmr/DB.properties");
        //创建一个键值对对象
        Properties properties = new Properties();
        //从（字符流）加载键值对的数据
        //自动地把原来数据：username=root =号左边为键，右边为值，加入到properties集合中
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        //1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
 /*   public static final String url="jdbc:mysql://localhost:3306/test";
    public static final String username="root";
    public static final String password="123456";*/
    public static Connection getconct() throws SQLException, IOException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void closeAll(Connection cont, PreparedStatement ps, ResultSet rs){
        if(cont!=null){
        try {
            cont.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}


    }
}
