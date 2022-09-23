package com.lmr.utils;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbctemplate<T> {
    public static void zsg(String sql, Object... objects) {
        Connection getconct = null;
        PreparedStatement ps = null;
        try {
            getconct = DButil.getconct();
            ps = getconct.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);//通过对象赋值，objects[i]通过objects的get方法去获取参数
            }
            System.out.println(sql);
            ps.execute();
            DButil.closeAll(getconct, ps, null);

        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    T findByid(String sql,int id,Class c){
        T t=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn=DButil.getconct();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                Constructor<T> con = c.getDeclaredConstructor();
                t =  con.newInstance();
                Field[] fields = c.getFields();//获取属性的集合
                for (int i=0;i<=fields.length;i++){
                    fields[i].setAccessible(true);//可访问私有属性
                    //相当于object.setname(rs.getobject(?))
                    fields[i].set(t,rs.getObject(i+1));//rs从1开始
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            DButil.closeAll(conn,ps,rs);
        }

        return t;
    }

    List<T> queryAll(String sql, Class c) {  // "select * from t_user" , User.class
        List<T> list = new ArrayList<T>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getconct();
            // String sql = "select id, username, password from t_user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            // ps.setString(1, username);
            // ps.setString(2, password);
            System.out.println(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                //遇到的问题是创建什么类型对象通过
                //  T t = new T(); //不能通过编译
                //不知道有多少个字段，类型不知道
                //无法从rs找出来，无法封装到bean中
                //list.add(t);
            }


            DButil.closeAll(conn, ps, rs);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }



    return null;
    }
}