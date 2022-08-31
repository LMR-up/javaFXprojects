package com.lmr.dao.Impl;

import com.lmr.dao.LoginInfoDao;
import com.lmr.pojo.LoginInfo;
import com.lmr.utils.DButil;
import com.lmr.utils.StringUtils;
import com.lmr.utils.jdbctemplate;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginInfoDaoImpl implements LoginInfoDao {
    public static void main(String[] args) {

        new LoginInfoDaoImpl().inert((new LoginInfo("admin", "123", new Date())));
    }

    @Override
    public void inert(LoginInfo loginInfo) {
        try {
            Connection getconct = DButil.getconct();
            String sql = "insert into logininfo (id,username,password,logintime) values(null,?,?,?)";
            PreparedStatement ps = getconct.prepareStatement(sql);
            ps.setString(1,loginInfo.getUsername());
            ps.setString(2,loginInfo.getPassword());
            ps.setTimestamp(3,new Timestamp(loginInfo.getLogintime().getTime()));
            ps.execute();
           // jdbctemplate.zsg(sql, loginInfo.getUsername(), loginInfo.getPassword(), new Timestamp(loginInfo.getLogintime().getTime()));//存储的是timpstape时间戳，所以要转换一下
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getPageCount(LoginInfo loginInfo) {
        int count=0;
        PreparedStatement ps=null;
        StringBuilder sql=new StringBuilder();
        sql.append("select count(*) as count from logininfo where 1=1 ");
        if(loginInfo!=null && StringUtils.isNotBlank(loginInfo.getUsername())){
            sql.append(" and username like '%"+loginInfo.getUsername()+"%'");
        }
        try {
            Connection conn = DButil.getconct();
            ps=conn.prepareStatement(sql.toString());
          //  ps.setString(1,loginInfo.getUsername());//初始条件下，logininfo为空值，应该对查询语句进行非空判断
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<LoginInfo> page(Integer currentPage, Integer pageSize, LoginInfo loginInfo) {//如果没有查询条件则logininfo为空,应该对查询条件进行非空判断
        List<LoginInfo> list=new ArrayList<>();
        PreparedStatement ps=null;
        try {
            Connection conn = DButil.getconct();
            StringBuilder sql=new StringBuilder();
             sql.append("select * from logininfo where 1=1 ");
            if (loginInfo != null && StringUtils.isNotBlank(loginInfo.getUsername())) {
                /*此处发生错误，应为拼接的时候，关键字前面没有空格，导致关键字错误，sql语句无法解析*/
                sql.append(" and username = '" + loginInfo.getUsername() + "'");
            }
            sql.append(" limit ?,? ");
            ps=conn.prepareStatement(sql.toString());
            ps.setInt(1,(currentPage-1)*pageSize);
            ps.setInt(2,pageSize);

            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                LoginInfo login=new LoginInfo();
                login.setId(rs.getInt("id"));
                login.setUsername(rs.getString("username"));
                login.setPassword(rs.getString("password"));
                login.setLogintime(rs.getTimestamp("logintime"));
                list.add(login);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) {
        boolean flag = false;
        try {
            Connection conn = DButil.getconct();

           String sql="delete from logininfo where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i != 0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
